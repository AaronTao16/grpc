package com.rpc;

import io.grpc.stub.StreamObserver;

import com.google.protobuf.Timestamp;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class MsgServiceImpl extends MsgServiceGrpc.MsgServiceImplBase {
    // store the message observers according to group id
    private static ConcurrentHashMap<Long, Set<StreamObserver<Msg.ChatMsgFromClient>>> observers = new ConcurrentHashMap<>();
    // store all message according to group id
    private static ConcurrentHashMap<Long, ConcurrentLinkedDeque<Msg.ChatMsgFromClient>> unreadMsg = new ConcurrentHashMap<>();

    //was used to assign group id if there is no
    @Override
    public StreamObserver<Msg.User> createUser(StreamObserver<Msg.ChatMsgFromClient> responseObserver) {
        // Timestamp timestamp = Timestamp.newBuilder()
        // .setSeconds(System.currentTimeMillis())
        // .build();
        return new StreamObserver<Msg.User>() {
            @Override
            public void onNext(Msg.User user) {
                // Msg.User initializedUser;
                // if(user.getGroupId() == -1){
                // initializedUser =
                // Msg.User.newBuilder().setName(user.getName()).setGroupId(timestamp.getSeconds()).build();
                // } else {
                // initializedUser =
                // Msg.User.newBuilder().setName(user.getName()).setGroupId(user.getGroupId()).build();
                // }
                // System.out.println(initializedUser.getGroupId());
                // initializedUser.toBuilder().setLastLogin(Timestamp.newBuilder().setSeconds(0).build()).build();
                // responseObserver.onNext(Msg.ChatMsgFromClient.newBuilder().setNewMsg(Msg.ChatMsg.newBuilder().setFrom(initializedUser).build()).build());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

    @Override
    public StreamObserver<Msg.ChatMsg> sendMsg(StreamObserver<Msg.ChatMsgFromClient> response) {

        return new StreamObserver<Msg.ChatMsg>() {
            private Msg.ChatMsgFromClient message;
            private long groupId;

            @Override
            public void onNext(Msg.ChatMsg value) {

                // System.out.println(value);
                groupId = value.getFrom().getGroupId();

                // Create a server message from the client message
                Timestamp timestamp = Timestamp.newBuilder()
                        .setSeconds(System.currentTimeMillis() / 1000)
                        .build();

                message = Msg.ChatMsgFromClient
                        .newBuilder()
                        .setNewMsg(value)
                        .setTimestamp(timestamp)
                        .build();

                // store all the observers
                if (value.getMsg().equals("initialize")) { // handle first connection
                    Set<StreamObserver<Msg.ChatMsgFromClient>> set = observers.getOrDefault(groupId,
                            ConcurrentHashMap.newKeySet());
                    set.add(response);
                    observers.put(groupId, set);
                    return;
                } else if (!observers.containsKey(groupId)) { // observer is in an existing group
                    Set<StreamObserver<Msg.ChatMsgFromClient>> set = ConcurrentHashMap.newKeySet();
                    set.add(response);
                    observers.put(groupId, set);
                } else if (!observers.get(groupId).contains(response)) { // observer in a new group
                    Set<StreamObserver<Msg.ChatMsgFromClient>> set = observers.get(groupId);
                    set.add(response);
                    observers.put(groupId, set);
                }

                // set unread message for each group
                ConcurrentLinkedDeque<Msg.ChatMsgFromClient> que = unreadMsg.getOrDefault(groupId,
                        new ConcurrentLinkedDeque<>());
                que.add(message);
                unreadMsg.put(groupId, que);

                // set group for observers
                Set<StreamObserver<Msg.ChatMsgFromClient>> set = observers.getOrDefault(groupId,
                        ConcurrentHashMap.newKeySet());
                set.add(response);
                observers.put(groupId, set);

                // Notify all observers in same group
                for (StreamObserver<Msg.ChatMsgFromClient> gUser : observers.get(groupId)) {
                    // System.out.println(gUser);
                    gUser.onNext(message);
                }
            }

            @Override
            public void onError(Throwable t) {
                // remove observer with fail connection
                observers.get(groupId).remove(response);
            }

            @Override
            public void onCompleted() {
                System.out.println("Completed!");
            }
        };
    }

    @Override
    public StreamObserver<Msg.User> getUnreadMsg(StreamObserver<Msg.UnreadMsg> responseObserver) {
        return new StreamObserver<Msg.User>() {
            private final Timestamp timestamp = Timestamp.newBuilder()
                    .setSeconds(System.currentTimeMillis())
                    .build();

            @Override
            public void onNext(Msg.User user) {
                // read messge from same group
                ConcurrentLinkedDeque<Msg.ChatMsgFromClient> msg = unreadMsg.getOrDefault(user.getGroupId(),
                        new ConcurrentLinkedDeque<>());

                Msg.UnreadMsg unread = Msg.UnreadMsg.newBuilder().build();
                // find unread message (message time > user last login time)
                for (Msg.ChatMsgFromClient m : msg) {
                    if (user.getLastLogin().getSeconds() < m.getTimestamp().getSeconds())
                        unread = unread.toBuilder().addUnreadMsg(m).build();
                }

                responseObserver.onNext(unread);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        };
    }

}
