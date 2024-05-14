package study.board.dto.domain;

import lombok.Data;
import lombok.Getter;
import study.board.entity.Role;

import java.time.LocalDateTime;

@Data
public class userListDto {
    private String userId;
    private String username;
    private Role auth;
    private LocalDateTime signupDate;
}
