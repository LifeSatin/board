package study.board.dto.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
public class PostListDto {

    private long postId;
    private String title;
    private String writer;
    private long viewCount;
    private long commentCount;
    private LocalDateTime createdDate;

    @QueryProjection
    public PostListDto(long postId, String title, String writer, long viewCount, long commentCount, LocalDateTime createdDate) {
        this.postId = postId;
        this.title = title;
        this.writer = writer;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
    }
}
