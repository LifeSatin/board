package study.board.repository.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import study.board.entity.Member;

import java.util.List;

import static study.board.entity.QMember.*;

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
