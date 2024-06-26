package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
}
