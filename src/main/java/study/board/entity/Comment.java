package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.board.dto.request.CommentRequestDto;
import study.board.dto.request.CommentUpdateRequestDto;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    public Comment(CommentRequestDto dto, Post post, Member member) {
        this.content = dto.getContent();
        this.post = post;
        this.member = member;
    }

    public void update(CommentUpdateRequestDto dto) {
        this.content = dto.getContent();
    }
}
