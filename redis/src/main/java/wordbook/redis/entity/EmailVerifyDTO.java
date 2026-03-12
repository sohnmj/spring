package wordbook.redis.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailVerifyDTO {
    private String email;
    private String code;
}
