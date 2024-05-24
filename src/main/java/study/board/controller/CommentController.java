package study.board.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import study.board.Service.CommentService;
import study.board.Service.PostService;
import study.board.dto.request.CommentRequestDto;
import study.board.dto.request.CommentUpdateRequestDto;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.response.CommentResponseDto;
import study.board.entity.Member;

import java.net.URI;

//관리자 관련 설정 완료
@RestController
@RequestMapping("/post/{postId}/comments")
@Tag(name = "Comment")
@RequiredArgsConstructor
public class CommentController {

    private final MessageSource ms;
    private final CommentService commentService;
    private final PostService postService;

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
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping
    public ResponseEntity<BasicResponseDto> createComments(@RequestBody @Validated CommentRequestDto commentRequestDto, @PathVariable long postId, @AuthenticationPrincipal User user) {
        long commentId = commentService.createComment(user.getUsername(), commentRequestDto, postId);
        return ResponseEntity.created(URI.create("/post/" + postId + "/comments" + commentId))
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("suc.code", null, null))
                                .message(ms.getMessage("suc.message", null, null))
                                .build()
                );
    }

    @Operation(summary = "댓글 수정", description = "댓글 수정하기")
    @PreAuthorize("hasAnyRole('USER')")
    @PatchMapping("/{commentId}")
    public ResponseEntity<BasicResponseDto> updateComment(CommentUpdateRequestDto dto, @PathVariable long postId, @PathVariable long commentId, @AuthenticationPrincipal User user) {
        commentService.updateComment(dto, commentId);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }

    @Operation(summary = "댓글 삭제", description = "댓글 삭제하기")
    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{commentId}")
    public ResponseEntity<BasicResponseDto> deleteComment(@PathVariable long postId, @PathVariable long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }
}
