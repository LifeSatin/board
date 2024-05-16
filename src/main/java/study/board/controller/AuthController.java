package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.Service.AuthService;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.request.LoginRequestDto;
import study.board.dto.request.SignupRequestDto;
import study.board.entity.Member;
import study.board.exception.LoginFailException;

import static study.board.SessionConst.*;

//기능 구현 완료
//이미 로그인한 사용자의 접근?
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Auth")
public class AuthController {

    private final AuthService authService;
    private final MessageSource ms;

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<BasicResponseDto> login(@RequestBody @Validated LoginRequestDto loginRequestDto, HttpServletRequest request, @SessionAttribute(name = "loginMember") Member loginMember) {
        Member member = authService.login(loginRequestDto);

        if (member == null) {
            throw new LoginFailException();
        }

        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_MEMBER, member);

        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<BasicResponseDto> signup(@RequestBody @Validated SignupRequestDto signupRequestDto, @SessionAttribute(name = "loginMember") Member loginMember) {
        long newMemberId = authService.signup(signupRequestDto);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }
}
