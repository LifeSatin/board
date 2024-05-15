package study.board.dto.response;

import lombok.Builder;
import lombok.Data;
import study.board.dto.domain.PostListDto;

import java.util.List;

@Data
@Builder
public class NoticeResponseDto {
    private String code;
    private String message;
    private List<PostListDto> noticeList;
}
