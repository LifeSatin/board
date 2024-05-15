package study.board.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostViewResponseDto {
    private String code;
    private String message;
    private long postId;
    private String title;
    private String content;
    private String writer;
    private String boardName;
    private long viewCount;
    private LocalDateTime createdDate;
}
