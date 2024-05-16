package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.Service.CommentService;
import study.board.Service.PostService;
import study.board.dto.request.CommentRequestDto;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.response.CommentResponseDto;
import study.board.entity.Member;

import java.net.URI;

@RestController
@RequestMapping("/post/{postId}/comments")
@Tag(name = "Comment")
@RequiredArgsConstructor
public class CommentController {

    private final MessageSource ms;
    private final CommentService commentService;

    @Operation(summary = "댓글 목록", description = "댓글 목록 조회하기")
    @GetMapping
    public ResponseEntity<CommentResponseDto> getComments(@PathVariable long postId) {
        return ResponseEntity.ok(
                CommentResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .commentList(commentService.viewComments(postId))
                        .build()
        );
    }

    @Operation(summary = "댓글 작성", description = "새 댓글 작성하기")
    @PostMapping
    public ResponseEntity<BasicResponseDto> createComments(@RequestBody @Validated CommentRequestDto commentRequestDto, @SessionAttribute(name = "loginMember") Member loginMember, @PathVariable long postId) {
        long commentId = commentService.createComment(loginMember, commentRequestDto, postId);
        return ResponseEntity.created(URI.create("/post/" + postId + "/comments" + commentId))
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("suc.code", null, null))
                                .message(ms.getMessage("suc.message", null, null))
                                .build()
                );
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정하기")
    @PatchMapping("/{commentId}")
    public void updateComment(@PathVariable long postId, @PathVariable long commentId) {

    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제하기")
    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable long postId, @PathVariable long commentId) {

    }
}
