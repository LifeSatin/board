package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.domain.UserListDto;
import study.board.dto.request.MemberUpdateRequestDto;
import study.board.entity.Member;
import study.board.entity.Role;
import study.board.exception.AuthorizationFailException;
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
    public void updateMember(long id, MemberUpdateRequestDto dto, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(NoUserException::new);
        if (findMember == member || member.getRole() == ADMIN) {
            findMember.update(dto);
        } else {
            throw new AuthorizationFailException();
        }
    }

    @Transactional(readOnly = true)
    public Optional<Member> searchMemberByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public void deleteMember(long id, Member member) {
        Member findMember = memberRepository.findById(id).orElseThrow(NoUserException::new);
        if (findMember == member || member.getRole() == ADMIN) {
            memberRepository.delete(findMember);
        } else {
            throw new AuthorizationFailException();
        }
    }
}
