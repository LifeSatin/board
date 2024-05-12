package study.board.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.dto.BasicResponseDto;
import study.board.dto.BoardNameRequestDto;

import java.net.URI;
//기능 구현 미완료
@RestController
@RequestMapping("/board")
@Tag(name = "Board")
public class BoardController {

    @Operation(summary = "게시판 목록", description = "게시판 목록 조회")
    @GetMapping
    public void getBoardList() {

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
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }

    @Operation(summary = "게시판 삭제", description = "게시판 삭제하기")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<BasicResponseDto> deleteBoard() {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }
}
