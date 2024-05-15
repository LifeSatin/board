package study.board.repository.board;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.board.dto.domain.BoardListDto;
import study.board.dto.domain.QBoardListDto;
import study.board.entity.QBoard;

import java.util.List;

import static study.board.entity.QBoard.*;

@RequiredArgsConstructor
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<BoardListDto> getBoardListDto() {
        return queryFactory
                .select(new QBoardListDto(
                        board.id,
                        board.boardName
                )).from(board)
                .fetch();
    }
}
