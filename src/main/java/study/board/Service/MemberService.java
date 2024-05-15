package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.domain.UserListDto;
import study.board.dto.request.MemberUpdateRequestDto;
import study.board.entity.Member;
import study.board.exception.NoUserException;
import study.board.repository.member.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //DTO 서식 좀 더 고쳐야됨
    @Transactional(readOnly = true)
    public Page<UserListDto> getUserList(Pageable pageable) {
        return memberRepository.findAll(pageable).map(UserListDto::new);
    }

    @Transactional(readOnly = true)
    public Optional<Member> searchMember(long id) {
        return memberRepository.findById(id);
    }

    @Transactional
    public void updateMember(long id, MemberUpdateRequestDto dto) {
        Member findMember = memberRepository.findById(id).orElseThrow(NoUserException::new);
        findMember.update(dto);
    }

    @Transactional(readOnly = true)
    public Optional<Member> searchMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public void deleteMember(long id) {
        Member findMember = memberRepository.findById(id).orElseThrow(NoUserException::new);
        memberRepository.delete(findMember);
    }
}
