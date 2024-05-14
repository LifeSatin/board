package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.dto.BasicResponseDto;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    @Operation(summary = "회원 목록", description = "회원 목록 조회 (관리자용)")
    @GetMapping
    public void getMembers() {

    }

    @Operation(summary = "회원 페이지", description = "회원 페이지 조회 (회원 본인에게는 마이페이지로 기능)")
    @GetMapping("/{userId}")
    public void getMemberPage(@PathVariable long userId) {

    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보 수정 (비밀번호, 닉네임 등)")
    @PatchMapping("/{userId}")
    public ResponseEntity<BasicResponseDto> updateMember(@PathVariable long userId) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴")
    @DeleteMapping("/{userId}")
    public ResponseEntity<BasicResponseDto> deleteMember(@PathVariable long userId) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }
}
