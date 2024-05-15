package study.board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import study.board.dto.domain.UserListDto;
import study.board.entity.Role;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class UserListResponseDto {
    private String code;
    private String message;
    private Page<UserListDto> userList;
}
