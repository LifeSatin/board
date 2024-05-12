package study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BasicResponseDto {
    private String code;
    private String message;
}
