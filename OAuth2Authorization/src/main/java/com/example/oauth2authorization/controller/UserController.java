package com.example.oauth2authorization.controller;

import com.example.oauth2authorization.dto.UserDTO;
import com.example.oauth2authorization.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String joinPage() {

        return "joinPage";
    }

    @PostMapping("/join")
    @ResponseBody
    public String join(UserDTO dto) {

        userService.join(dto);

        return "ok";
    }
}
