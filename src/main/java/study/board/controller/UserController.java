package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.Service.MemberService;
import study.board.Service.PostService;
import study.board.dto.request.MemberUpdateRequestDto;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.response.UserListResponseDto;
import study.board.dto.response.UserPageResponseDto;
import study.board.entity.Member;
import study.board.exception.DuplicateUsernameException;
import study.board.exception.NoUserException;

//관리자 접근 제외 구현 완료
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final MessageSource ms;
    private final MemberService memberService;
    private final PostService postService;

    @Operation(summary = "회원 목록", description = "회원 목록 조회 (관리자용)")
    @GetMapping
    public ResponseEntity<UserListResponseDto> getMembers(Pageable pageable) {
        return ResponseEntity.ok(
                UserListResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .userList(memberService.getUserList(pageable))
                        .build()
        );
    }

    //구현 완료
    @Operation(summary = "회원 페이지", description = "회원 페이지 조회 (회원 본인에게는 마이페이지로 기능)")
    @GetMapping("/{userId}")
    public ResponseEntity<UserPageResponseDto> getMemberPage(@PathVariable long userId, Pageable pageable) {
        Member member = memberService.searchMember(userId).orElseThrow(NoUserException::new);
        return ResponseEntity.ok(
                UserPageResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .username(member.getUsername())
                        .userPostList(postService.getMemberPost(userId, pageable))
                        .build()
        );
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정 (비밀번호, 닉네임 등)")
    @PatchMapping("/{userId}")
    public ResponseEntity<BasicResponseDto> updateMember(@RequestBody @Validated MemberUpdateRequestDto dto, @PathVariable long userId) {
        Member updateCandidate = memberService.searchMemberByUsername(dto.getUsername()).orElse(null);

        if (updateCandidate == null) {
            memberService.updateMember(userId, dto);
        } else {
            throw new DuplicateUsernameException();
        }

        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    @DeleteMapping("/{userId}")
    public ResponseEntity<BasicResponseDto> deleteMember(@PathVariable long userId) {
        memberService.deleteMember(userId);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }
}
