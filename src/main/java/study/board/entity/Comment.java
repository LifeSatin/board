package study.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private long id;

    private long post_id;

    private long member_id;

    private String content;
}
