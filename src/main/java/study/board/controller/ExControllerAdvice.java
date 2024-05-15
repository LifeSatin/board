package study.board.controller;

import jakarta.persistence.Basic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import study.board.dto.response.BasicResponseDto;
import study.board.exception.*;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExControllerAdvice {

    private final MessageSource ms;

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> loginFailure(LoginFailException e) {
        log.error("login fail", e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("lmf.code", null, null))
                                .message(ms.getMessage("lmf.message", null, null))
                                .build()
                );
    }

    //유효성 검사 실패
    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
    public ResponseEntity<BasicResponseDto> validationFailure(Exception e) {
        log.error("validation fail", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("vdf.code", null, null))
                                .message(ms.getMessage("vdf.message", null, null))
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
                                .code(ms.getMessage("dbe.code", null, null))
                                .message(ms.getMessage("dbe.message", null, null))
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
                                .code(ms.getMessage("dif.code", null, null))
                                .message(ms.getMessage("dif.message", null, null))
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
                                .code(ms.getMessage("duf.code", null, null))
                                .message(ms.getMessage("duf.message", null, null))
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> unsupportedMethodFailure(HttpRequestMethodNotSupportedException e) {
        log.error("method not supported", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("nmf.code", null, null))
                                .message(ms.getMessage("nmf.message", null, null))
                                .build()
                );
    }


    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> pageNotFoundFailure(NoResourceFoundException e) {
        log.error("no page", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("pnf.code", null, null))
                                .message(ms.getMessage("pnf.message", null, null))
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> noUserFoundFailure(NoUserException e) {
        log.error("no user", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("nue.code", null, null))
                                .message(ms.getMessage("nue.message", null, null))
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> duplicateBoardnameFailure(DuplicateBoardNameException e) {
        log.error("dup board", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("dbf.code", null, null))
                                .message(ms.getMessage("dbf.message", null, null))
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> noBoardFoundFailure(NoBoardException e) {
        log.error("no board", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("nbf.code", null, null))
                                .message(ms.getMessage("nbf.message", null, null))
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> noPostFoundFailure(NoPostException e) {
        log.error("no post", e);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("npf.code", null, null))
                                .message(ms.getMessage("npf.message", null, null))
                                .build()
                );
    }

    @ExceptionHandler
    public ResponseEntity<BasicResponseDto> asd(ServletRequestBindingException e) {
        log.error("request without a login", e);
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("auf.code", null, null))
                                .message(ms.getMessage("auf.message", null, null))
                                .build()
                );
    }
}
