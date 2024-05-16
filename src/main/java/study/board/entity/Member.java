package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import study.board.dto.request.MemberUpdateRequestDto;
import study.board.dto.request.SignupRequestDto;

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
    @ColumnDefault("USER")
    private Role role = Role.USER;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    public Member(SignupRequestDto dto) {
        this.username = dto.getUsername();
        this.loginId = dto.getId();
        this.password = dto.getPassword();
    }

    public void update(MemberUpdateRequestDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
    }
}
