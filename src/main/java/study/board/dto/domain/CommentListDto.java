package study.board.dto.domain;

import com.querydsl.core.annotations.QueryProjection;

import java.time.LocalDateTime;

public class CommentListDto {
    private long id;
    private String writer;
    private String content;
    private LocalDateTime createdDate;

    @QueryProjection
    public CommentListDto(long id, String writer, String content, LocalDateTime createdDate) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
    }
}
