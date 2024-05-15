package study.board.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Data
public class PostUpdateRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String content;
}
