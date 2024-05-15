package study.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import study.board.dto.domain.BoardListDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BoardListResponseDto {
    private String code;
    private String message;
    private List<BoardListDto> boardList;
}
