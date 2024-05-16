package study.board.repository.comment;

import study.board.dto.domain.CommentListDto;
import study.board.dto.domain.PostListDto;
import study.board.entity.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
    List<CommentListDto> getCommentList(long postId);
}
