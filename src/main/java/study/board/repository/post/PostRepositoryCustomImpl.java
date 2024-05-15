package study.board.repository.post;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import study.board.dto.domain.PostListDto;
import study.board.dto.domain.QPostListDto;
import study.board.entity.QComment;
import study.board.entity.QPost;

import java.util.List;

import static study.board.entity.QComment.*;
import static study.board.entity.QPost.*;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostListDto> getPostListbyMemberId(long memberId, Pageable pageable) {
        List<PostListDto> result = queryFactory
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
                .where(post.writer.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long count = queryFactory
                .select(post.count())
                .from(post)
                .where(post.writer.id.eq(memberId))
                .fetchOne();

        return new PageImpl<>(result, pageable, count);
    }
}
