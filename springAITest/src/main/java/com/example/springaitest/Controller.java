package com.example.springaitest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Flux;


import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
@RequiredArgsConstructor
public class Controller {
    private final OpenAiService chatClientService;
    @GetMapping("/chat")
    public List<String> ai(){
        System.out.println(" = ");
        return chatClientService.generate("세상에서 가장 높은산 ");
    }
    @ResponseBody
    @PostMapping("/chat/stream")
    public Flux<String> streamChat(@RequestBody Map<String, String> body) {
        return chatClientService.generateStream(body.get("text"));
    }
    @GetMapping("/")
    public String chatPage() {
        return "home";
    }
}
