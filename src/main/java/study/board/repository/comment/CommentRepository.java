package study.board.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>, CommentRepositoryCustom{
}
