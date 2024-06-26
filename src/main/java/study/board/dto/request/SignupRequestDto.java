package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequestDto {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
}
