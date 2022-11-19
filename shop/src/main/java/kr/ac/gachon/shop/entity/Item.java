package kr.ac.gachon.shop.entity;

import kr.ac.gachon.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item{
// 실행 시 item 테이블 쫙 만들겠지
// 그리고 아이템 레포지토리에서는 이제 뭘 할지
    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //GenerationType.AUTO ppt 2장 p.23 AUTO는 JPA에서 알아서 관리
    //알아서 증가해라
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명 item_nm 이렇게 변경돼서 들어간다.

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob //아주 긴 문장을 넣을 수 있다는 얘기
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING) //enum 때문에 이렇게 잡아줌 enum은 스트링을 표현
    private ItemSellStatus itemSellStatus; //상품 판매 상태

    private LocalDateTime regTime; // 동록 시간
    //멤버들
    private LocalDateTime updateTime; // 수정 시간

}