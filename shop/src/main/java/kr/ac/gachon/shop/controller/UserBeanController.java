package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.component.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserBeanController {
    @Autowired // 필드 주입 방법
    @Qualifier("superShotGun")
    private Weapon swp; //super shotgun

    private final Weapon bwp; // 생성자 주입시 final 선언

    @Autowired // 생성자 주입 방법
    public UserBeanController(Weapon bwp) {
        // @Qualifier("basicShotGun")가 Weapon 타입의 객체의 @Primary 선언으로 생략 가능
        this.bwp = bwp;
    }
    /*
    @Autowired // 생성자 주입 방법
    public UserBeanController(@Qualifier("basicShotGun") Weapon bwp) {
        this.bwp = bwp;
    }
    */

    @GetMapping("/b")
    public String main(Model m){
        m.addAttribute("name","홍길동");
        m.addAttribute("weapon", bwp.getModel());
        return "user_bean";
    }

    @GetMapping("/s")
    public @ResponseBody ResponseEntity<Model> get(Model m) {
        m.addAttribute("name","홍길동");
        m.addAttribute("weapon", swp.getModel());
        return new ResponseEntity<Model>(m, HttpStatus.OK);
    }
}

