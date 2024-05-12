package study.board.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.board.dto.BasicResponseDto;
import study.board.dto.LoginRequestDto;
import study.board.dto.SignupRequestDto;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth")
public class AuthController {

    //기능 구현 미완료
    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    public ResponseEntity<BasicResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }

    //기능 구현 미완료
    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<BasicResponseDto> signup(@RequestBody SignupRequestDto signupRequestDto) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }
}
