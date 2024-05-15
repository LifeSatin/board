package study.board.dto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.board.entity.Member;
import study.board.entity.Role;

import java.time.LocalDateTime;

@Data
public class UserListDto {
    private long userId;
    private String username;
    private Role auth;
    private LocalDateTime signupDate;

    public UserListDto(Member member) {
        this.userId = member.getId();
        this.username = member.getUsername();
        this.auth = member.getRole();
        this.signupDate = member.getCreatedDate();
    }
}
