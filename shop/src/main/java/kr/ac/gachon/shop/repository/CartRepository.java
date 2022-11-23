package kr.ac.gachon.shop.repository;

import kr.ac.gachon.shop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}