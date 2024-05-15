package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.domain.BoardListDto;
import study.board.dto.request.BoardNameRequestDto;
import study.board.dto.request.BoardUpdateRequestDto;
import study.board.entity.Board;
import study.board.exception.DuplicateBoardNameException;
import study.board.exception.NoBoardException;
import study.board.repository.board.BoardRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<BoardListDto> getBoardList() {
        return boardRepository.getBoardListDto();
    }

    @Transactional(readOnly = true)
    public Optional<Board> findBoard(long boardId) {
        return boardRepository.findById(boardId);
    }

    @Transactional
    public long createBoard(BoardNameRequestDto dto) {
        Board board = new Board(dto);
        if (boardRepository.findByBoardName(dto.getBoardName()).orElse(null) == null) {
            boardRepository.save(board);
        } else {
            throw new DuplicateBoardNameException();
        }
        return board.getId();
    }

    @Transactional
    public void updateBoard(long boardId, BoardNameRequestDto dto) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(NoBoardException::new);
        if (boardRepository.findByBoardName(dto.getBoardName()).orElse(null) == null) {
            findBoard.update(dto);
        } else {
            throw new DuplicateBoardNameException();
        }
    }

    @Transactional
    public void deleteBoard(long boardId) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(NoBoardException::new);
        boardRepository.delete(findBoard);
    }
}
