package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.board.dto.domain.BoardListDto;
import study.board.repository.board.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<BoardListDto> getBoardList() {
        return boardRepository.getBoardListDto();
    }
}
