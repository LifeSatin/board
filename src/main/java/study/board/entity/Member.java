package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.dto.SignupRequestDto;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "member_id")
    private long id;

    private String username;

    @Column(name = "login_id")
    private String loginId;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    @OneToMany(mappedBy = "member")
    private List<Post> posts;

    public Member(SignupRequestDto dto) {
        this.username = dto.getUsername();
        this.loginId = dto.getId();
        this.password = dto.getPassword();
    }
}
