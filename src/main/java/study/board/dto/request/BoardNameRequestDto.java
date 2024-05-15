package study.board.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
public class BoardNameRequestDto {
    @NotBlank
    private String boardName;

    @JsonCreator
    public BoardNameRequestDto(String boardName) {
        this.boardName = boardName;
    }
}
