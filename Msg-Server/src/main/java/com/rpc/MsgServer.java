package com.rpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.*;

public class MsgServer {
    private static int SERVER_PORT = 8000;
    private static List<String> argsList = new ArrayList<>();
    private static Map<String, List<String>> optsMap = new HashMap<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        // read arguments
        initialize(args);

        // Build server
        Server server = ServerBuilder.forPort(optsMap.containsKey("PORT")?Integer.parseInt(optsMap.get("PORT").get(0)):SERVER_PORT)
                .addService(new MsgServiceImpl())
                .build();

        // Start server
        System.out.println("Starting server on port " + (optsMap.containsKey("PORT")?optsMap.get("PORT").get(0):SERVER_PORT));
        server.start();

        // Keep it running
        System.out.println("Server started!");
        server.awaitTermination();
    }

    private static void initialize(String[] args) {
        for (int i = 0; i < args.length; i++) {

            if(args[i].charAt(0) == '-'){
                if(i+1 == args.length)
                    throw new IllegalArgumentException("Expected arg after: "+args[i]);
                switch (args[i]){
                    case "-":
                        throw new IllegalArgumentException("Not a valid argument: "+args[i]);
                    case "-client":
                        List<String> clients = new ArrayList<>(Arrays.asList(args[i+1].split(",")));
                        optsMap.put("CLIENT", clients);
                        break;
                    case "-port":
                        List<String> ports = new ArrayList<>(Arrays.asList(args[i+1].split(",")));
                        if(ports.size() > 1) throw new IllegalArgumentException("Not a valid argument: "+args[i]);
                        else optsMap.put("PORT", ports);
                        break;
                    default:
                        throw new IllegalArgumentException("Not a valid argument: "+args[i] + "\n   -client [client_name,...] \n    -port <port>");
                }
            } else {
                argsList.add(args[i]);
            }
        }
    }

}