package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardUpdateRequestDto {
    @NotBlank
    private long boardId;
    @NotBlank
    private String boardName;
}
