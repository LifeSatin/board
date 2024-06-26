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
import study.board.dto.domain.PostListDto;
import study.board.dto.request.PostCreateRequestDto;
import study.board.dto.request.PostUpdateRequestDto;
import study.board.dto.request.SearchRequestDto;
import study.board.dto.response.BasicResponseDto;
import study.board.dto.request.CommentRequestDto;
import study.board.dto.response.NoticeResponseDto;
import study.board.dto.response.PostViewResponseDto;
import study.board.dto.response.SearchResponseDto;
import study.board.entity.Member;
import study.board.entity.Post;

import java.net.URI;
import java.util.List;

//관리자 설정 완료
//파일 업로드는 후추
@RestController
@RequestMapping("/post")
@Tag(name = "Post")
@RequiredArgsConstructor
public class PostController {

    private final MessageSource ms;
    private final PostService postService;

    @Operation(summary = "게시글 검색", description = "게시글 검색하기")
    @GetMapping("/search")
    public ResponseEntity<SearchResponseDto> search(SearchRequestDto dto) {
        List<PostListDto> search = postService.search(dto);
        return ResponseEntity.ok(
                SearchResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .searchList(search)
                        .build()
        );
    }

    @Operation(summary = "게시글 조회", description = "게시글 조회하기")
    @GetMapping("/{postId}")
    public ResponseEntity<PostViewResponseDto> viewPost(@PathVariable long postId) {
        Post post = postService.viewPost(postId);
        return ResponseEntity.ok(
                PostViewResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .postId(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .writer(post.getWriter().getUsername())
                        .boardName(post.getBoard().getBoardName())
                        .viewCount(post.getViewCount())
                        .createdDate(post.getCreatedDate())
                        .build()
        );
    }

    @Operation(summary = "공지사항 목록", description = "공지사항 목록 조회하기")
    @GetMapping("/notice")
    public ResponseEntity<?> getNotices() {
        List<PostListDto> notice = postService.getNotice();
        return ResponseEntity.ok(
                NoticeResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .noticeList(notice)
                        .build()
        );
    }

    @Operation(summary = "게시글 작성", description = "새 게시글 작성하기")
    @PreAuthorize("hasAnyRole('USER')")
    @PostMapping
    public ResponseEntity<BasicResponseDto> createPost(@SessionAttribute(name = "loginMember") Member loginMember, @RequestBody @Validated PostCreateRequestDto dto) {
        long postId = postService.createPost(loginMember, dto);
        return ResponseEntity.created(URI.create("/post/" + postId))
                .body(
                        BasicResponseDto.builder()
                                .code(ms.getMessage("suc.code", null, null))
                                .message(ms.getMessage("suc.message", null, null))
                                .build()
                );
    }

    @Operation(summary = "게시글 수정", description = "게시글 수정하기")
    @PreAuthorize("hasAnyRole('USER')")
    @PatchMapping("/{postId}")
    public ResponseEntity<BasicResponseDto> updatePost(@RequestBody @Validated PostUpdateRequestDto dto, @PathVariable long postId, @AuthenticationPrincipal User user) {
        postService.updatePost(postId, dto);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }


    @Operation(summary = "게시글 삭제", description = "게시글 삭제하기")
    @PreAuthorize("hasAnyRole('USER')")
    @DeleteMapping("/{postId}")
    public ResponseEntity<BasicResponseDto> deletePost(@PathVariable long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok(
                BasicResponseDto.builder()
                        .code(ms.getMessage("suc.code", null, null))
                        .message(ms.getMessage("suc.message", null, null))
                        .build()
        );
    }
}
