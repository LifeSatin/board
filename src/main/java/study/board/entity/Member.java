package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Column(unique = true)
    private String username;

    @Column(name = "login_id", unique = true)
    private String loginId;

    private String password;

    @Enumerated(value = EnumType.STRING)
    @ColumnDefault("USER")
    private Role role = Role.USER;

    /*
    @OneToMany(mappedBy = "member")
    private List<Comment> comments;
     */

    public Member(SignupRequestDto dto, PasswordEncoder passwordEncoder) {
        this.username = dto.getUsername();
        this.loginId = dto.getId();
        this.password = passwordEncoder.encode(dto.getPassword());
    }

    public void update(MemberUpdateRequestDto dto) {
        this.username = dto.getUsername();
        this.password = dto.getPassword();
    }

    public void initMember(PasswordEncoder passwordEncoder) {
        this.username = "test";
        this.loginId = "test";
        this.password = passwordEncoder.encode("0000");
        this.role = Role.ADMIN;
    }
}
