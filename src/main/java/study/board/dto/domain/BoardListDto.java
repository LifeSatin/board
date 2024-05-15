package study.board.dto.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class BoardListDto {
    private long boardId;
    private String boardName;

    @QueryProjection
    public BoardListDto(long boardId, String boardName) {
        this.boardId = boardId;
        this.boardName = boardName;
    }
}
