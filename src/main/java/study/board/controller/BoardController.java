package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.Service.BoardService;
import study.board.Service.PostService;
import study.board.dto.domain.BoardListDto;
import study.board.dto.domain.PostListDto;
import study.board.dto.request.BoardUpdateRequestDto;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.request.BoardNameRequestDto;
import study.board.dto.response.BoardListResponseDto;
import study.board.dto.response.BoardResponseDto;
import study.board.entity.Board;
import study.board.entity.Member;
import study.board.exception.NoBoardException;

import java.net.URI;
import java.util.List;

//기능 구현 미완료
//관리자 관련 설정 완료
@Slf4j
@RestController
@RequestMapping("/board")
@Tag(name = "Board")
@RequiredArgsConstructor
public class BoardController {

    private final MessageSource ms;
    private final BoardService boardService;
    private final PostService postService;

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

    //테스트 못함
    @Operation(summary = "게시판 조회", description = "게시판 내의 게시글 조회")
    @GetMapping("/{boardId}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable long boardId, Pageable pageable) {
        Board findBoard = boardService.findBoard(boardId).orElseThrow(NoBoardException::new);

        List<PostListDto> boardPost = postService.getBoardPost(boardId, pageable);
        return ResponseEntity.ok(
                BoardResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .boardName(findBoard.getBoardName())
                        .postCount(boardPost.size())
                        .postList(boardPost)
                        .build()
        );
    }

    @Operation(summary = "게시판 생성", description = "새 게시판 생성")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody @Validated BoardNameRequestDto boardNameRequestDto) {
        long boardId = boardService.createBoard(boardNameRequestDto);

        return ResponseEntity.created(URI.create("/board/" + boardId))
                .body(
                        BoardResponseDto.builder()
                                .code(ms.getMessage("suc.code", null, null))
                                .message(ms.getMessage("suc.message", null, null))
                                .build()
                );
    }

    @Operation(summary = "게시판 수정", description = "게시판 제목 수정")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("/{boardId}")
    public ResponseEntity<BasicResponseDto> updateBoard(@PathVariable long boardId, @RequestBody @Validated BoardNameRequestDto dto) {
        boardService.updateBoard(boardId, dto);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }

    @Operation(summary = "게시판 삭제", description = "게시판 삭제하기")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{boardId}")
    public ResponseEntity<BasicResponseDto> deleteBoard(@PathVariable long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }
}
