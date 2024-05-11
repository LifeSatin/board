package study.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
