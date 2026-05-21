package com.example.springaitest;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.ChatMemoryRepository;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;

import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAiService {
    private final OpenAiChatModel  openAiChatModel;
    private final ChatMemoryRepository chatMemoryRepository;
    public  List<String> generate(String text){
        List<String>strings=new ArrayList<>();
        SystemMessage systemMessage = new SystemMessage("");
        UserMessage userMessage = new UserMessage(text);
        AssistantMessage assistantMessage = new AssistantMessage("");
       OpenAiChatOptions options =
                OpenAiChatOptions.builder()
                        .temperature(0.7)
                        .build();
        Prompt prompt = new Prompt(List.of(systemMessage, userMessage, assistantMessage), options);
        ChatResponse response=openAiChatModel.call(prompt);
        ChatResponseMetadata chatResponseMetadata=response.getMetadata();
        String meta=chatResponseMetadata.toString();
        String content=response.getResult().getOutput().getText();
        strings.add(content);
        strings.add(meta);
        return strings;
    }
    public Flux<String> generateStream(String text) {

        // 유저&페이지별 ChatMemory를 관리하기 위한 key (우선은 명시적으로)
        String userId = "xxxjjhhh" + "_" + "3";

        ChatMemory chatMemory = MessageWindowChatMemory.builder()
                .maxMessages(10)
                .chatMemoryRepository(chatMemoryRepository)
                .build();
        chatMemory.add(userId, new UserMessage(text)); // 신규 메시지도 추가

        // 옵션
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("gpt-4o-mini")
                .temperature(0.7)
                .build();

        // 프롬프트
        Prompt prompt = new Prompt(chatMemory.get(userId), options);

        // 응답 메시지를 저장할 임시 버퍼
        StringBuilder responseBuffer = new StringBuilder();

        // 요청 및 응답
        return openAiChatModel.stream(prompt)
                .mapNotNull(response -> {
                    String token = response.getResult().getOutput().getText();
                    responseBuffer.append(token);
                    return token;
                })
                .doOnComplete(() -> {

                    chatMemory.add(userId, new AssistantMessage(responseBuffer.toString()));
                    chatMemoryRepository.saveAll(userId, chatMemory.get(userId));
                });
    }

}
