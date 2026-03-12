package wordbook.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wordbook.redis.entity.EmailDTO;
import wordbook.redis.entity.EmailVerifyDTO;
import wordbook.redis.service.MailService;
import wordbook.redis.service.RedisService;

@RestController
@RequestMapping("/api/v1/mail")
public class MailController {
    private final MailService mailService;
    private final RedisService redisService;
    @Autowired
    public MailController(MailService mailService, RedisService redisService) {
        this.mailService = mailService;
        this.redisService = redisService;
    }
    @PostMapping("")
    public void sendMail(@RequestBody EmailDTO dto){
        mailService.sendMail(dto.getEmail());
    }
    @PostMapping("/verify")
    public ResponseEntity<Boolean> verifyMail(@RequestBody EmailVerifyDTO dto){
        Boolean verify = redisService.verify(dto.getEmail(), dto.getCode());
        return ResponseEntity.ok(verify);
    }

}
