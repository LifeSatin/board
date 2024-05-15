package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.Service.BoardService;
import study.board.dto.domain.BoardListDto;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.request.BoardNameRequestDto;
import study.board.dto.response.BoardListResponseDto;

import java.util.List;

//기능 구현 미완료
@RestController
@RequestMapping("/board")
@Tag(name = "Board")
@RequiredArgsConstructor
public class BoardController {

    private final MessageSource ms;
    private final BoardService boardService;

    @Operation(summary = "게시판 목록", description = "게시판 목록 조회")
    @GetMapping
    public ResponseEntity<BoardListResponseDto> getBoardList() {
        List<BoardListDto> boardList = boardService.getBoardList();
        return ResponseEntity.ok(
                BoardListResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .boardList(boardList)
                        .build()
        );
    }

    @Operation(summary = "게시판 조회", description = "게시판 내의 게시글 조회")
    @GetMapping("/{boardId}")
    public void getBoard(@PathVariable long boardId) {

    }

    @Operation(summary = "게시판 생성", description = "새 게시판 생성")
    @PostMapping
    public void createBoard(@RequestBody BoardNameRequestDto boardNameRequestDto) {

    }

    @Operation(summary = "게시판 수정", description = "게시판 제목 수정")
    @PatchMapping("/{boardId}")
    public ResponseEntity<BasicResponseDto> updateBoard(@RequestBody BoardNameRequestDto boardNameRequestDto) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }

    @Operation(summary = "게시판 삭제", description = "게시판 삭제하기")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<BasicResponseDto> deleteBoard() {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }
}
