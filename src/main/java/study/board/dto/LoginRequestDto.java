package study.board.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class LoginRequestDto {

    @NotBlank
    private String id;
    @NotBlank
    private String password;
}
