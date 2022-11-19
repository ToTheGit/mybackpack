package kr.ac.gachon.shop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.ac.gachon.shop.constant.ItemSellStatus;
import kr.ac.gachon.shop.entity.Item;
import kr.ac.gachon.shop.entity.QItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;

@SpringBootTest // 테스트 실행시 모든 Bean을 IoC 컨테이너에 등록 = 스프링부트 구동과 동일한 환경 조건
@TestPropertySource(locations="classpath:application-test.properties") // 테스트 실행시 우선되는 설정파일
class ItemRepositoryTest {
    @Autowired // 필드 Bean 주입
    ItemRepository itemRepository;

    @Test // Method 테스트 대상 지정
    @DisplayName("상품 저장 확인") // 테스트명
    public void createItemTest(){
        Item item = new Item();
        item.setItemNm("좋은 상품");
        item.setPrice(10000);
        item.setItemDetail("좋은 상품 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }
    public void createItemList(){
        for(int i=1; i<=10; i++){
            Item item = new Item();
            item.setItemNm("좋은 상품 " + i);
            item.setPrice(10000 + i);
            item.setItemDetail("좋은 상품 설명 " + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100); item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 확인")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("좋은 상품 7"); //
        for(Item item : itemList){
            LOGGER.info(item.toString());
        }
    }

    @Test
    @DisplayName("상품명 or 상품 설명 확인")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("좋은 상품 1", "좋은 상품 설명 5");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("가격 LessThan 확인")
    public void findByPriceLessThanTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(10005); // price 변수 값이 10005 미만인 상품만 검색
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 내림차순(Desc) 조회 확인")
    public void findByPriceLessThanOrderByPriceDescTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10006);
        // price 변수 가격이 10006 미만인 상품만 후 내림차순 정열

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThanEqual 내림차순(Desc) 조회 확인")
    public void findByPriceLessThanEqualOrderByPriceDescTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanEqual(10006);

        for(Item item : itemList){
            System.out.println(item.toString());
        }

    }

    @Test
    @DisplayName("가격 between 확인")
    public void findByPriceIsBetween(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceIsBetween(10000,10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("가격 LessThanEqual 확인")
    public void findByPriceLessThanEqual(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanEqual(10005);
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    @Test
    @DisplayName("@Query를 이용한 상품 조회 확인")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("좋은 상품");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }


    @Test
    @DisplayName("@Query를 이용한 상품 조회 확인 NATIVE")
    public void findByItemDetailByNativeTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetailByNative("좋은 상품");
        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }
    @PersistenceContext
    EntityManager em; //entity manager factory ppt 2장 p.6

    @Test
    @DisplayName("Querydsl 조회 확인")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        JPAQuery<Item> query  = queryFactory.selectFrom(qItem) //item 테이블 or 객체
                //queryFactory는 JPA쿼리 팩토리로 쿼리를 편하게 쓸 수 있도록
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL)) //eq -> equal 쿼리
                .where(qItem.itemDetail.like("%" + "좋은 상품 설명" + "%")) //Like 쿼리
                .orderBy(qItem.price.desc());


        List<Item> itemList = query.fetch();

        for(Item item : itemList){
            System.out.println(item.toString());
        }
    }

    public void createItemListPaging(){
        for(int i=1;i<=5;i++){
            Item item = new Item();
            item.setItemNm("좋은 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("좋은 상품 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }

        for(int i=6;i<=10;i++){
            Item item = new Item();
            item.setItemNm("좋은 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("좋은 상품 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }
    @Test
    @DisplayName("상품 Querydsl 페이징 조회 확인")
    public void queryDslPagingTest(){

        this.createItemListPaging();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;

        String itemDetail = "좋은 상품 설명";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(item.price.gt(price));
//        booleanBuilder.or(item.price) 불린빌더 실습해보자
        System.out.println(ItemSellStatus.SELL);
        //테이블 지칭 x, 조건만 넣어주면 된다. booleanbuilder
        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)){
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }
//        List<Item> resultItemList = itemRepository.findAll(booleanBuilder);
        //paging은 기본중에 기본인데 은근 어렵다....
        int page = 2;
        page-=1;

        Pageable pageable = PageRequest.of(0, 5); //게시판 밑에 페이지 0~5
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        //page는 List와 같다고 보면된다.
        //pageable page를 만들어줌
        System.out.println("total elements : " + itemPagingResult. getTotalElements ());

        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem: resultItemList){
            System.out.println(resultItem.toString());
        }
    }



}

