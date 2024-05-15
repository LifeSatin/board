package study.board.dto.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchRequestDto {
    private String writer;
    private String title;
    private String content;
    private String boardName;
}
