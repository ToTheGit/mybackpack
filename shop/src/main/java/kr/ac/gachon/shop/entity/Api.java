package kr.ac.gachon.shop.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="api")
@Getter
@Setter
//entity는 setter를 쓰면 안된다는 얘기가있다.
//db필드에 직접 업데이트를 친다.

@ToString
public class Api {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String AREA_CONGEST_LVL;
    private String AREA_CONGEST_MSG;
}
