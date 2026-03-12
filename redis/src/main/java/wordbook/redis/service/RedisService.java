package wordbook.redis.service;


import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final String cuponKey = "API:usage";
    private final StringRedisTemplate stringRedisTemplate;
    public RedisService(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //redis에 key-value값으로 저장
    public void set(String key, String value,Long time){
        stringRedisTemplate.opsForValue().set(key, value,time, TimeUnit.SECONDS);
    }

    //redis에 저장된 이메일 인증코드와 입력 된 code가 동일한지 검증
    public boolean verify(String email,String code){
        //email에 해당하는 인증코드 가져오기
        String savedCode = stringRedisTemplate.opsForValue().get("email:"+email);

        //redis에 해당 이메일이 없으면 false
        if(savedCode == null){
            return false;
        }

        //해당 이메일 인증코드와 code가 같다면 true
        if(savedCode.equals(code)){
            stringRedisTemplate.delete("email:"+email);
            //인증된 이메일 정보는 300초까지 인증이 적용되고 그전에 회원가입을 끝내야함
            set("email:"+email,"verify",300L);
            return true;
        }
        //인증코드와 code가 다르다면 false
        return false;
    }

    //쿠폰 소진
    public Long decreaseCupon(){
        if(stringRedisTemplate.opsForValue().get(cuponKey)==null){
            stringRedisTemplate.opsForValue().set(cuponKey,"100");
        }
        return stringRedisTemplate.opsForValue().decrement(cuponKey);
    }

    public boolean isDuplicate(String username){
        if(stringRedisTemplate.opsForValue().get("user:"+username)==null){
            return false;
        }
        return true;
    }
    public boolean IsVerifiedEmail(String email){
        String verify = stringRedisTemplate.opsForValue().get("email:" + email);
        boolean equals = "verify".equals(verify);
        if(equals){
            stringRedisTemplate.delete("email:" + email);
        }
        return equals;
    }
}
