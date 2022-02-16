package com.rpc;

import com.google.protobuf.Timestamp;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MsgClient extends MsgServiceGrpc.MsgServiceImplBase {
    private static int PORT = 8000;
    private static String SERVER_IP = "localhost";
    // store arguments
    private static List<String> argsList = new ArrayList<>();
    private static Map<String, String> optsMap = new HashMap<>();

    private static ManagedChannel channel;
    private static MsgServiceGrpc.MsgServiceStub msgService;

    private static Msg.User curUser;

    public static void main(String[] args) {
        initialize(args);

        creatUser();

        getUnreadMsg();

        sendMsg();
    }

    private static void initialize(String[] args) {
        for (int i = 0; i < args.length; i++) {

            if (args[i].charAt(0) == '-') {
                if (i + 1 == args.length)
                    throw new IllegalArgumentException("Expected arg after: " + args[i]);
                switch (args[i]) {
                    case "-":
                        throw new IllegalArgumentException("Not a valid argument: " + args[i]);
                    case "-client_id":
                        optsMap.put("CLIENT", args[i + 1]);
                        break;
                    case "-server_ip":
                        optsMap.put("SERVER_IP", args[i + 1]);
                        break;
                    case "-port":
                        optsMap.put("PORT", args[i + 1]);
                        break;
                    case "-group_id":
                        optsMap.put("GROUP", args[i + 1]);
                        break;
                    default:
                        throw new IllegalArgumentException("Not a valid argument: " + args[i]
                                + "\n   -client_id <client_name> \n   -server_ip <ipaddress>\n    -port <port>\n    -group_id <group_id>");
                }
            } else {
                argsList.add(args[i]);
            }
        }

        channel = ManagedChannelBuilder
                .forAddress(optsMap.containsKey("SERVER_IP") ? optsMap.get("SERVER_IP") : SERVER_IP,
                        optsMap.containsKey("PORT") ? Integer.parseInt(optsMap.get("PORT")) : PORT)
                .usePlaintext().build();
        msgService = MsgServiceGrpc.newStub(channel);

        // assign a group id if there is no re-assign one
        Timestamp timestamp = Timestamp.newBuilder()
                .setSeconds(System.currentTimeMillis() / 1000)
                .build();
        // create current user
        curUser = Msg.User.newBuilder().setName(optsMap.get("CLIENT"))
                .setGroupId(
                        optsMap.containsKey("GROUP") ? Long.parseLong(optsMap.get("GROUP")) : timestamp.getSeconds())
                .build();
    }

    //[abortion] was used to send server to assign group id.
    private static void creatUser() {
        StreamObserver<Msg.User> initStreamObserver = msgService
                .createUser(new StreamObserver<Msg.ChatMsgFromServer>() {
                    @Override
                    public void onNext(Msg.ChatMsgFromServer user) {
                        // curUser =
                        // Msg.User.newBuilder().setGroupId(user.getNewMsg().getFrom().getGroupId()).setName(user.getNewMsg().getFrom().getName()).build();
                        // System.out.println(curUser.getGroupId());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }

                    @Override
                    public void onCompleted() {

                    }
                });

        initStreamObserver.onNext(curUser);
    }

    // get unread messages
    private static void getUnreadMsg() {
        StreamObserver<Msg.User> msgStreamObserver = msgService.getUnreadMsg(new StreamObserver<Msg.UnreadMsg>() {
            @Override
            public void onNext(Msg.UnreadMsg unreadMsg) {
                for (int i = 0; i < unreadMsg.getUnreadMsgCount(); ++i) {
                    Msg.ChatMsgFromServer chatMsgFromServer = unreadMsg.getUnreadMsg(i);
                    System.out.print(curUser.getName() + "'s screen: ");
                    if (chatMsgFromServer.getNewMsg().getFrom().getName().equals(curUser.getName()))
                        System.out.print("You: " + chatMsgFromServer.getNewMsg().getMsg());
                    else
                        System.out.print(chatMsgFromServer.getNewMsg().getFrom().getName() + ": " + chatMsgFromServer.getNewMsg().getMsg());
                    secd2Date(chatMsgFromServer);
                }
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {

            }
        });

        msgStreamObserver.onNext(curUser);
    }

    // send message and receive message from other clients
    private static void sendMsg() {
        StreamObserver<Msg.ChatMsg> streamObserver = msgService.sendMsg(new StreamObserver<Msg.ChatMsgFromServer>() {
            @Override
            public void onNext(Msg.ChatMsgFromServer chatMsgFromServer) {
                System.out.print(curUser.getName() + "'s screen: ");
                // handle message send from other than current client
                if (!chatMsgFromServer.getNewMsg().getFrom().getName().equals(optsMap.get("CLIENT"))) {
                    System.out.print(chatMsgFromServer.getNewMsg().getFrom().getName() + ": "
                            + chatMsgFromServer.getNewMsg().getMsg());
                } else {  // handle message send from current client
                    // for demo
                    System.out.print(chatMsgFromServer.getNewMsg().getFrom().getName() + ": " + chatMsgFromServer.getNewMsg().getMsg());
                    System.out.print("    ---sended---  "); // server received
                }
                secd2Date(chatMsgFromServer);
            }

            @Override
            public void onError(Throwable throwable) { // server is crashed
                if (throwable.getMessage().equals("UNAVAILABLE: io exception")) {
                    System.out.println("Server connection failure");
                }
            }

            @Override
            public void onCompleted() {

            }
        });

        streamObserver.onNext(Msg.ChatMsg.newBuilder().setFrom(curUser).setMsg("initialize").build());

        System.out.printf("Welcome %s, let's start a group Msg! -- GroupID: %d", optsMap.get("CLIENT"), curUser.getGroupId());
        System.out.println();
        Scanner sc = new Scanner(System.in);
        String next = "";
        // chat will open till user type "-quit"
        while (!(next = sc.nextLine()).equals("-quit")) {
            if (!next.isEmpty())
                streamObserver.onNext(Msg.ChatMsg.newBuilder().setFrom(curUser).setMsg(next).build());
        }
    }

    // convert timestamp to readable date
    private static void secd2Date(Msg.ChatMsgFromServer chatMsgFromServer) {
        Date dateTime = new Date(chatMsgFromServer.getTimestamp().getSeconds() * 1000);
        String pattern = "hh:mm:ss yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String formattedDate = simpleDateFormat.format(dateTime);
        System.out.println("   " + formattedDate);
    }
}
