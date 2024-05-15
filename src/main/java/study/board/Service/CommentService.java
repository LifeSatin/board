package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.request.CommentRequestDto;
import study.board.entity.Comment;
import study.board.entity.Member;
import study.board.entity.Post;
import study.board.repository.CommentRepository;
import study.board.repository.post.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public long createComment(Member member, CommentRequestDto dto, long postId) {
        Comment comment = new Comment(dto);
        Post post = postRepository.findById(postId).orElse(null);

        comment.relationMapping(post, member);
        commentRepository.save(comment);
        return comment.getId();
    }
}
