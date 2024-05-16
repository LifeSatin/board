package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.dto.request.BoardNameRequestDto;
import study.board.dto.request.BoardUpdateRequestDto;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "board_id")
    private long id;

    @Column(name = "board_name")
    private String boardName;

    public Board(BoardNameRequestDto dto) {
        this.boardName = dto.getBoardName();
    }

    public void update(BoardNameRequestDto dto) {
        this.boardName = dto.getBoardName();
    }
}
