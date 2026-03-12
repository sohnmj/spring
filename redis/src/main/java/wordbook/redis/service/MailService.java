package wordbook.redis.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class MailService {
    private final String title="인증 번호를 입력해주세요";
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final MailSender mailSender;
    private final RedisService redisService;
    public MailService(MailSender mailSender, RedisService redisService) {
        this.mailSender = mailSender;
        this.redisService = redisService;
    }

    //이메일 인증을 위한 이메일 전송
    public void sendMail(String to){
        //4자리 난수 생성
        SecureRandom random = new SecureRandom();
        int code=random.nextInt(9000)+1000;

        //이메일 생성
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(title);
        message.setText(String.valueOf(code));
        message.setFrom(fromEmail);

        //이메일 전송
        try{
            mailSender.send(message);
            //이메일 전송시 유효시간 동안 redis에 해당 이메일 계정 저장
            redisService.set("email:"+to,String.valueOf(code),300L);
        }catch(MailException e){
            e.printStackTrace();
        }
    }
}
