package study.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "board_id")
    private long id;

    private String board_name;

    private int post_count;

    @OneToMany(mappedBy = "board")
    private List<Post> posts;
}
