package wordbook.redis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wordbook.redis.entity.UserCreatedRequestDTO;
import wordbook.redis.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("")
    public ResponseEntity<Long> addUser(@RequestBody UserCreatedRequestDTO userCreatedDTO){
        Long l = userService.addUser(userCreatedDTO);
        return ResponseEntity.ok(l);
    }

}
