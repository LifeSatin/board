package study.board.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostListDto {

    private long postId;
    private String title;
    private String writer;
    private int viewCount;
    private int commentCount;
    private LocalDateTime createdDate;
}
