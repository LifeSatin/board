package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class BoardNameRequestDto {
    @NotBlank
    private String boardName;
}
