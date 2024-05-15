package study.board.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import study.board.dto.domain.PostListDto;
import study.board.dto.request.SearchRequestDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostListDto> getPostListbyMemberId(long memberId, Pageable pageable);

    List<PostListDto> getPostListbyBoard(long boardId, Pageable pageable);

    List<PostListDto> getNoticeList();

    List<PostListDto> search(SearchRequestDto dto);
}
