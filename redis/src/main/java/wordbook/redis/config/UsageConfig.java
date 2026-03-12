package wordbook.redis.config;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UsageConfig {
    private final StringRedisTemplate stringRedisTemplate;
    public UsageConfig(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Scheduled(fixedRate = 60000)
    public void addUsage() {
        stringRedisTemplate.opsForValue().set("API:usage", "100");
    }
}
