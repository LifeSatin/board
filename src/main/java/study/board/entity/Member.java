package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String username;

    private String login_id;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}
