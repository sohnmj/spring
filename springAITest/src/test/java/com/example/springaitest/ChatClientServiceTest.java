package com.example.springaitest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ChatClientServiceTest {
    @Autowired
    ChatClientService chatClientService;
    @Test
    void getApi() {
        //given
        String prompt="세상에서 제일 높은 산";
        //when
        String text= chatClientService.getApi(prompt);
        //then
        System.out.println("text = " + text);
    }
}