package study.board.repository.member;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;
import study.board.dto.domain.QPostListDto;
import study.board.dto.response.UserPageResponseDto;
import study.board.entity.QPost;

import static study.board.entity.QPost.*;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<PostListDto> searchMemberPost(Pageable pageable) {
        return null;
    }
}
