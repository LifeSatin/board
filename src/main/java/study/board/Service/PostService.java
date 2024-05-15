package study.board.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.board.dto.domain.PostListDto;
import study.board.dto.request.PostCreateRequestDto;
import study.board.dto.request.PostUpdateRequestDto;
import study.board.dto.request.SearchRequestDto;
import study.board.entity.Board;
import study.board.entity.Member;
import study.board.entity.Post;
import study.board.exception.NoBoardException;
import study.board.exception.NoPostException;
import study.board.exception.NoUserException;
import study.board.repository.board.BoardRepository;
import study.board.repository.member.MemberRepository;
import study.board.repository.post.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<PostListDto> getMemberPost(long memberId, Pageable pageable) {
        return postRepository.getPostListbyMemberId(memberId, pageable);
    }

    @Transactional(readOnly = true)
    public List<PostListDto> getBoardPost(long boardId, Pageable pageable) {
        return postRepository.getPostListbyBoard(boardId, pageable);
    }

    @Transactional
    public Post viewPost(long postId) {
        return postRepository.findById(postId).orElseThrow(NoPostException::new).view();
    }

    @Transactional
    public long createPost(Member member, PostCreateRequestDto dto) {
        Member validCheck = memberRepository.findById(member.getId()).orElseThrow(NoUserException::new);

        Post post = new Post(member, dto);

        Board board = boardRepository.findByBoardName(dto.getBoardName()).orElseThrow(NoBoardException::new);
        post.boardMapping(board);
        postRepository.save(post);
        return post.getId();
    }

    @Transactional
    public void updatePost(long postId, PostUpdateRequestDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(NoPostException::new);
        post.update(dto);
    }

    @Transactional
    public void deletePost(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(NoPostException::new);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public List<PostListDto> getNotice() {
        return postRepository.getNoticeList();
    }

    @Transactional(readOnly = true)
    public List<PostListDto> search(SearchRequestDto dto) {
        return postRepository.search(dto);
    }
}
