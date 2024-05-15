package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class SignupRequestDto {
    @NotBlank
    private String id;
    @NotBlank
    private String password;
    @NotBlank
    private String username;

    public SignupRequestDto(String password, String username) {
        this.password = password;
        this.username = username;
    }
}
