package study.board.repository.member;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;
import study.board.dto.domain.QPostListDto;
import study.board.dto.response.UserPageResponseDto;
import study.board.entity.Member;
import study.board.entity.QMember;
import study.board.entity.QPost;

import java.util.List;

import static study.board.entity.QMember.*;
import static study.board.entity.QPost.*;

@RequiredArgsConstructor
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Member> findAllinList(Pageable pageable) {
        return queryFactory
                .selectFrom(member)
                .fetch();
    }
}
