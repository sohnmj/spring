package wordbook.redis.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CuponServiceTest {
    @Autowired
    CuponService cuponService;
    @Autowired
    RedisService redisService;
    @Test
    public void test() {
        redisService.decreaseCupon();
    }


}