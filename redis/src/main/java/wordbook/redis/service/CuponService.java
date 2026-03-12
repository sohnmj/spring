package wordbook.redis.service;

import org.springframework.stereotype.Service;
import wordbook.redis.entity.GetCuponDTO;

@Service
public class CuponService {

    private final RedisService redisService;

    public CuponService(RedisService redisService) {
        this.redisService = redisService;
    }

    public boolean getCupon(GetCuponDTO getCuponDTO) {
        if(redisService.isDuplicate(getCuponDTO.getUsername())){
            return false;
        }
        Long count=redisService.decreaseCupon();
        if(count>=0){
            //redisService.set("user:"+getCuponDTO.getUsername(),"true");
            return true;
        }
        else {
            return false;
        }
    }
}
