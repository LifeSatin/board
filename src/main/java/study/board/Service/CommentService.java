package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.domain.CommentListDto;
import study.board.dto.request.CommentRequestDto;
import study.board.dto.request.CommentUpdateRequestDto;
import study.board.entity.Comment;
import study.board.entity.Member;
import study.board.entity.Post;
import study.board.entity.Role;
import study.board.exception.AuthorizationFailException;
import study.board.exception.NoCommentException;
import study.board.exception.NoPostException;
import study.board.exception.NoUserException;
import study.board.repository.comment.CommentRepository;
import study.board.repository.member.MemberRepository;
import study.board.repository.post.PostRepository;

import java.util.List;

import static study.board.entity.Role.*;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public long createComment(Member member, CommentRequestDto dto, long postId) {
        memberRepository.findById(member.getId()).orElseThrow(NoUserException::new);
        Post post = postRepository.findById(postId).orElseThrow(NoPostException::new);

        Comment comment = new Comment(dto, post, member);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional(readOnly = true)
    public List<CommentListDto> viewComments(long postId) {
        return commentRepository.getCommentList(postId);
    }

    @Transactional
    public void updateComment(CommentUpdateRequestDto dto, long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NoCommentException::new);
        if (comment.getMember() == member || member.getRole() == ADMIN) {
            comment.update(dto);
        } else {
            throw new AuthorizationFailException();
        }
    }

    @Transactional
    public void deleteComment(long commentId, Member member) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(NoCommentException::new);
        if (comment.getMember() == member || member.getRole() == ADMIN) {
            commentRepository.delete(comment);
        } else {
            throw new AuthorizationFailException();
        }
    }
}
