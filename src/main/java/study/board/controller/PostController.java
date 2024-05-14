package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.board.dto.BasicResponseDto;
import study.board.dto.CommentRequestDto;

@RestController
@RequestMapping("/post")
@Tag(name = "Post")
public class PostController {

    @Operation(summary = "게시글 검색", description = "게시글 검색하기")
    @GetMapping("/search")
    public void search() {

    }

    @Operation(summary = "게시글 조회", description = "게시글 조회하기")
    @GetMapping("/{postId}")
    public void viewPost(@PathVariable long postId) {

    }

    @Operation(summary = "댓글 목록", description = "댓글 목록 조회하기")
    @GetMapping("/{postId}/comments")
    public void getComments(@PathVariable long postId) {

    }

    @Operation(summary = "공지사항 목록", description = "공지사항 목록 조회하기")
    @GetMapping("/notice")
    public void getNotices() {

    }

    @Operation(summary = "게시글 작성", description = "새 게시글 작성하기")
    @PostMapping
    public void createPost() {

    }

    @Operation(summary = "댓글 작성", description = "새 댓글 작성하기")
    @PostMapping("/{postId}/comments")
    public void createComments(@RequestBody CommentRequestDto commentRequestDto) {

    }

    @Operation(summary = "게시글 수정", description = "게시글 수정하기")
    @PatchMapping("/{postId}")
    public ResponseEntity<BasicResponseDto> updatePost(@PathVariable long postId) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }


    @Operation(summary = "게시글 삭제", description = "게시글 삭제하기")
    @DeleteMapping("/{postId}")
    public ResponseEntity<BasicResponseDto> deletePost(@PathVariable long postId) {
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code("SUC")
                        .message("Success")
                        .build()
        );
    }
}
