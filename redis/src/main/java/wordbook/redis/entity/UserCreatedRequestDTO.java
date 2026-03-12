package wordbook.redis.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserCreatedRequestDTO {
    private String email;
    private String username;
    private String password;
}
