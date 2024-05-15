package study.board.repository.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.entity.Board;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{

    Optional<Board> findByBoardName(String boardName);
}
