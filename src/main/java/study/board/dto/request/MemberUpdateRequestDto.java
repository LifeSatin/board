package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberUpdateRequestDto {
    @NotBlank
    private String password;
    @NotBlank
    private String username;
}
