package wordbook.redis.service;

import org.springframework.stereotype.Service;
import wordbook.redis.entity.UserCreatedRequestDTO;
import wordbook.redis.entity.UserEntity;
import wordbook.redis.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RedisService redisService;
    public UserService(UserRepository userRepository, RedisService redisService) {
        this.userRepository = userRepository;
        this.redisService = redisService;
    }
    public Long addUser(UserCreatedRequestDTO userCreatedDTO){
        String email=userCreatedDTO.getEmail();
        boolean isVerify=redisService.IsVerifiedEmail(email);
        if(isVerify){
            UserEntity userEntity=UserEntity.builder()
                    .email(email)
                    .password(userCreatedDTO.getPassword())
                    .username(userCreatedDTO.getUsername())
                    .build();
            UserEntity save = userRepository.save(userEntity);
            return save.getId();
        }
        return -1l;
    }
}
