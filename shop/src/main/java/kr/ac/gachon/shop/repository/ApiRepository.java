package kr.ac.gachon.shop.repository;

import kr.ac.gachon.shop.entity.Api;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApiRepository extends JpaRepository<Api, Long> {
}
