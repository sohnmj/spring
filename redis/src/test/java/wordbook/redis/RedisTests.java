//package wordbook.redis;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.transaction.annotation.Transactional;
//import wordbook.redis.entity.RedisEntity;
//import wordbook.redis.entity.UserRedis;
//import wordbook.redis.repository.RedisRepository;
//import wordbook.redis.repository.UserRedisRepository;
//
//@SpringBootTest
//public class RedisTests {
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    RedisRepository redisRepository;
//
//    @Test
//    void redisTemplateString() {
//        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
//
//        String key = "name";
//        valueOperations.set(key, "giraffe");
//        String value = valueOperations.get(key);
//        Assertions.assertEquals(value, "giraffe");
//    }
//    @Autowired
//    private UserRedisRepository userRedisRepository;
//
//    @Test
//    void saveAndFind() {
//
//        UserRedis user = new UserRedis(1l, "lion","Strong");
//
//        userRedisRepository.save(user);
//
//        UserRedis found = userRedisRepository.findById(1l).orElseThrow();
//
//        Assertions.assertEquals("lion", found.getName());
//    }
//    @Test
//
//    void find() {
//        RedisEntity redisEntity=new RedisEntity();
//        redisEntity.setName("lion");
//        redisEntity.setContent("Strong");
//
//        redisRepository.save(redisEntity);
//    }
//}
