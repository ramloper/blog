package rami.dev.ramiblog.dto.user;

import lombok.Getter;
import lombok.Setter;
import rami.dev.ramiblog.model.user.User;

public class UserRequest {

    @Getter @Setter
    public static class JoinInDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity(){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .role("USER")
                    .status(true)
                    .profile("person.png")
                    .build();
        }
    }
}
