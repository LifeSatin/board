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
public class Post {

    @Id @GeneratedValue
    @Column(name = "post_id")
    private long id;

    private long board_id;

    private long member_id;

    private String title;

    private String content;

    private int view_count;

    private int comment_count;

    //첨부파일
    
}
