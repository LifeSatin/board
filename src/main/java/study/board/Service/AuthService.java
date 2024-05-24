package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.request.LoginRequestDto;
import study.board.dto.request.SignupRequestDto;
import study.board.entity.Member;
import study.board.exception.DuplicateIdException;
import study.board.exception.DuplicateUsernameException;
import study.board.repository.member.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member login(LoginRequestDto dto) {
        return memberRepository.findByLoginId(dto.getId())
                .filter(m -> m.getPassword().equals(dto.getPassword()))
                .orElse(null);
    }

    @Transactional
    public long signup(SignupRequestDto dto) {
        Member newMember = new Member(dto, passwordEncoder);
        Member findDuplicateId = memberRepository.findByLoginId(newMember.getLoginId()).orElse(null);
        Member findDuplicateMember = memberRepository.findByUsername(newMember.getUsername()).orElse(null);
        if (findDuplicateId != null) {
            throw new DuplicateIdException();
        } else if (findDuplicateMember != null) {
            throw new DuplicateUsernameException();
        } else {
            memberRepository.save(newMember);
        }
        return newMember.getId();
    }

    @Transactional
    public void logout() {

    }
}
