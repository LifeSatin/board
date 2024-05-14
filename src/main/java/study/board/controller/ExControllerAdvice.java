package study.board.controller;

import jakarta.persistence.Basic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import study.board.dto.BasicResponseDto;
import study.board.exception.DuplicateIdException;
import study.board.exception.DuplicateUsernameException;
import study.board.exception.LoginFailException;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> loginFailure(LoginFailException e) {
        log.error("login fail", e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        BasicResponseDto.builder()
                                .code("LMF")
                                .message("Login information Mismatch Failure")
                                .build()
                );
    }

    //유효성 검사 실패
    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> validationFailure(HttpMessageNotReadableException e) {
        log.error("validation fail", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BasicResponseDto.builder()
                                .code("VDF")
                                .message("Validation Failure")
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> dbFailure(Exception e) {
        log.error("server fail", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        BasicResponseDto.builder()
                                .code("DBE")
                                .message("Database Error")
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> duplicateIdFailure(DuplicateIdException e) {
        log.error("dup id", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BasicResponseDto.builder()
                                .code("DIF")
                                .message("Duplicate ID Failure")
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> duplicateUsernameFailure(DuplicateUsernameException e) {
        log.error("dup username", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BasicResponseDto.builder()
                                .code("DUF")
                                .message("Duplicate Username Failure")
                                .build()
                );
    }
}
