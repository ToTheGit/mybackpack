package kr.ac.gachon.shop.controller;

import ch.qos.logback.classic.Logger;
import kr.ac.gachon.shop.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class DefaultRestController {
    private Logger LOGGER;

    //http://localhost:8080/rest/json
    @GetMapping("/json")
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model","모델값 json");
        //return new ResponseEntity<>(m, HttpStatus.OK);
        return ResponseEntity.ok(m);
    }

    //http://localhost:8080/rest/dto?name=홍길동&phone=01011111111
    @GetMapping("/dto")
    public UserDto html(UserDto userDto) {
        LOGGER.info("dto > json");
        return userDto;
    }

    //http://localhost:8080/rest/map
    @GetMapping("/map")
    public HashMap<String,String> html() {
        HashMap<String,String> map = new HashMap<>(){{
            put("이름","홍길동");
            put("나이","30");
            put("국적","서울");
        }};
        return map;
    }

    //http://localhost:8080/rest/parm?name=홍길동&phone=01011111111
    @GetMapping(value = "/parm")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        /*
        for (String key : param.keySet()) {
            String value = param.get(key);
            sb.append(key + " : " + value + "\n");
            System.out.println("[key]:" + key + ", [value]:" + value);
        }
        */

        return sb.toString();
    }
}

