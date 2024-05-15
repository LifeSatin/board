package study.board.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import study.board.dto.domain.PostListDto;

import java.util.List;

@Data
@Builder
public class UserPageResponseDto {
    private String code;
    private String message;
    private String username;
    private List<PostListDto> userPostList;

    @QueryProjection
    public UserPageResponseDto(String code, String message, String username, List<PostListDto> userPostList) {
        this.code = code;
        this.message = message;
        this.username = username;
        this.userPostList = userPostList;
    }
}
