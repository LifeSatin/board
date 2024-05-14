package study.board.Service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.LoginRequestDto;
import study.board.dto.SignupRequestDto;
import study.board.entity.Member;
import study.board.exception.DuplicateIdException;
import study.board.exception.DuplicateUsernameException;
import study.board.repository.MemberRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final EntityManager em;

    public Member login(LoginRequestDto dto) {
        return memberRepository.findByLoginId(dto.getId())
                .filter(m -> m.getPassword().equals(dto.getPassword()))
                .orElse(null);
    }

    public long signup(SignupRequestDto dto) {
        Member newMember = new Member(dto);
        Member findDuplicateId = memberRepository.findByLoginId(newMember.getLoginId()).orElse(null);
        Member findDuplicateMember = memberRepository.findByUsername(newMember.getUsername()).orElse(null);
        if (findDuplicateId != null) {
            throw new DuplicateIdException();
        } else if (findDuplicateMember != null) {
            throw new DuplicateUsernameException();
        } else {
            em.persist(newMember);
        }
        return newMember.getId();
    }
}
