package study.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import study.board.dto.domain.PostListDto;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class BoardResponseDto {
    private String code;
    private String message;
    private String boardName;
    private long postCount;
    private List<PostListDto> postList;
}
