package study.board.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;

public interface PostRepositoryCustom {
    Page<PostListDto> getPostListbyMemberId(long memberId, Pageable pageable);
}
