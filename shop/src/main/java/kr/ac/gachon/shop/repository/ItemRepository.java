package kr.ac.gachon.shop.repository;

import kr.ac.gachon.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    List<Item> findByItemNm(String itemNm); // find+(Entity명)+By+(변수명)으로 메소드 생성. Entity명 제거할 수 있음
    // List<Item> findItemByItemNm(String itemNm);
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
    List<Item> findByPriceLessThan(Integer price); // 입력된 price 미만 값 확인
    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price); // 입력된 price 미만 값 확인 및 price 값 기준 정열
    List <Item> findByPriceIsBetween(Integer priceMin, Integer priceMax);
    <item> List <item> findByPriceLessThanEqual(Integer price);
    // 그냥 int(값만 들어감)로 하지말고 integer(help method 함게 들어감)로 하는 것이 아주 좋다.

    @Query("select i from Item i where i.itemDetail like " +
            "%:itemDetail% order by i.price desc") // 여기서 Item은 클래스관점
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail); // 직접 Query를 입력하여 값 확인 JPQL(made by JAVA)
    // itemDetail이 매개변수 인자값 매핑하면서 %:안까지 넣어준다.
    // 당연히 불문율로 매개변수도 이름 똑같이
    // JPQL을 쓰면 어떠한 DB든 교체돼도 쿼리를 수정할 필요가 없다.
    // 쿼리를 직접 날릴거라면 JPQL을 사용하도록하자.

    @Query(value="select * from item/*테이블관점*/ i where i.item_detail like " +
            "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail); // 직접 Query를 입력하여 값 확인 마리아DB SQL

}

