package study.board.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;
import study.board.dto.response.UserPageResponseDto;
import study.board.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<Member> findAllinList(Pageable pageable);
}
