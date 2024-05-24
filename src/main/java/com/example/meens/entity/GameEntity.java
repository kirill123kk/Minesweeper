package com.example.meens.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@Table(name = "game")
public class GameEntity {

    @Id
    @Column(name = "id", nullable = false)

    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String gameId;

    @Column(name = "width")
    private Integer width;

    @Column(name = "height")
    private Integer height;

    @Column(name = "mines_count")
    private Integer minesCount;

    @Column(name = "field")
    private String gameField;

    @Column(name = "field_view")
    private String gameFieldView;

    @Column(name = "completed")
    private Boolean completed;
}
