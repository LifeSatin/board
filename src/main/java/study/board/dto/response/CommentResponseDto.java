package study.board.dto.response;

import lombok.Builder;
import lombok.Data;
import study.board.dto.domain.CommentListDto;

import java.util.List;

@Data
@Builder
public class CommentResponseDto {
    private String code;
    private String message;
    private List<CommentListDto> commentList;
}
