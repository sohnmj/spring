//package com.example.springaitest;
//
//import org.springframework.ai.chat.client.ChatClient;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ChatClientService {
//    private final ChatClient chatClient;
//    public ChatClientService(ChatClient.Builder chatclientBuilder){
//       chatClient=chatclientBuilder.build();
//    }
//    public String getApi(String prompt){
//        String test =chatClient.prompt().user(prompt).call().content();
//        return test;
//    }
//
//}
