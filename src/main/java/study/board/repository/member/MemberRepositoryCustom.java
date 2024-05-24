package study.board.repository.member;

import org.springframework.data.domain.Pageable;
import study.board.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findAllinList(Pageable pageable);
}
