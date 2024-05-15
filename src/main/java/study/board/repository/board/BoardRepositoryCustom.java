package study.board.repository.board;

import study.board.dto.domain.BoardListDto;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardListDto> getBoardListDto();
}
