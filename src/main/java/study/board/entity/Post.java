package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "post_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member writer;

    private String title;

    private String content;

    @Column(name = "view_count")
    private long viewCount;

    //공지사항 여부
    @ColumnDefault("false")
    private Boolean isNotice = false;

    //첨부파일

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
