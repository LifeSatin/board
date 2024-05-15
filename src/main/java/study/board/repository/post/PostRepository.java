package study.board.repository.post;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.board.dto.domain.PostListDto;
import study.board.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {


}
