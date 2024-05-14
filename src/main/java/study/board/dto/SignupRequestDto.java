package study.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
}
