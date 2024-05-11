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
public class Board extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "board_id")
    private long id;

    private String board_name;

    private int post_count;
}
