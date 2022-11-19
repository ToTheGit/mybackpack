package kr.ac.gachon.shop.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // @Bean 메소드 사용을 위해 선언
public class BasicConfiguration {
    @Bean // 사용자 정의 Bean 등록
    @Qualifier("superShotGun") // @Bean(name="superShotGun") 대체 가능
    public Weapon superShotGun(){
        ShotGun sg = new ShotGun();
        sg.setModel("Super ShotGun");
        return sg; // @Bean 메소드는 반드시 객체를 반납
    }
}

