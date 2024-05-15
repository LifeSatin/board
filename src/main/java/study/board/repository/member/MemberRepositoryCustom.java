package study.board.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;
import study.board.dto.response.UserPageResponseDto;

public interface MemberRepositoryCustom {

    Page<PostListDto> searchMemberPost(Pageable pageable);
}
