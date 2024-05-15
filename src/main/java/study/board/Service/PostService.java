package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import study.board.dto.domain.PostListDto;
import study.board.repository.post.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostListDto> getMemberPost(long memberId, Pageable pageable) {
        return postRepository.getPostListbyMemberId(memberId, pageable);
    }

    public List<PostListDto> getBoardPost(long boardId, Pageable pageable) {
        return postRepository.getPostListbyBoard(boardId, pageable);
    }
}
