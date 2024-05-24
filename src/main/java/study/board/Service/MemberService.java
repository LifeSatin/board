package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.domain.UserListDto;
import study.board.dto.request.MemberUpdateRequestDto;
import study.board.entity.Member;
import study.board.exception.NoUserException;
import study.board.repository.member.MemberRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static study.board.entity.Role.*;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //DTO 서식 좀 더 고쳐야됨
    @Transactional(readOnly = true)
    public List<UserListDto> getUserList(Pageable pageable) {
        return memberRepository.findAllinList(pageable).stream()
                .map(m -> new UserListDto(m))
                .collect(toList());
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
