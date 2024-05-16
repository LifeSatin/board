package study.board.repository.comment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.board.dto.domain.CommentListDto;
import study.board.dto.domain.PostListDto;
import study.board.dto.domain.QCommentListDto;
import study.board.dto.domain.QPostListDto;
import study.board.entity.Comment;
import study.board.entity.QComment;

import java.util.List;

import static study.board.entity.QComment.*;

@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CommentListDto> getCommentList(long postId) {
        return queryFactory
                .select(new QCommentListDto(
                        comment.id,
                        comment.member.username,
                        comment.content,
                        comment.createdDate
                ))
                .from(comment)
                .where(comment.post.id.eq(postId))
                .fetch();
    }
}
