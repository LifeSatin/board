package study.board.repository.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;
import study.board.dto.domain.QPostListDto;
import study.board.dto.request.SearchRequestDto;

import java.util.List;

import static study.board.entity.QComment.*;
import static study.board.entity.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<PostListDto> getPostListbyMemberId(long memberId, Pageable pageable) {
        return queryFactory.select(new QPostListDto(
                        post.id,
                        post.title,
                        post.writer.username,
                        post.viewCount,
                        JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.post.id.eq(post.id)),
                        post.createdDate
                ))
                .from(post)
                .where(post.writer.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<PostListDto> getPostListbyBoard(long boardId, Pageable pageable) {
        return queryFactory
                .select(new QPostListDto(
                        post.id,
                        post.title,
                        post.writer.username,
                        post.viewCount,
                        JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.post.id.eq(post.id)),
                        post.createdDate
                ))
                .from(post)
                .where(post.board.id.eq(boardId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public List<PostListDto> getNoticeList() {
        return queryFactory
                .select(new QPostListDto(
                        post.id,
                        post.title,
                        post.writer.username,
                        post.viewCount,
                        JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.post.id.eq(post.id)),
                        post.createdDate
                ))
                .from(post)
                .where(post.isNotice.eq(true))
                .fetch();
    }

    @Override
    public List<PostListDto> search(SearchRequestDto dto) {
        return queryFactory
                .select(new QPostListDto(
                        post.id,
                        post.title,
                        post.writer.username,
                        post.viewCount,
                        JPAExpressions
                                .select(comment.count())
                                .from(comment)
                                .where(comment.post.id.eq(post.id)),
                        post.createdDate
                ))
                .from(post)
                .where(writerContains(dto.getWriter()), titleContains(dto.getTitle()), contentContains(dto.getContent()), boardContains(dto.getBoardName()))
                .fetch();
    }

    private BooleanExpression writerContains(String writer) {
        return writer != null ? post.writer.username.contains(writer) : null;
    }

    private BooleanExpression titleContains(String title) {
        return title != null ? post.writer.username.contains(title) : null;
    }

    private BooleanExpression contentContains(String content) {
        return content != null ? post.writer.username.contains(content) : null;
    }

    private BooleanExpression boardContains(String board) {
        return board != null ? post.writer.username.eq(board) : null;
    }
}
