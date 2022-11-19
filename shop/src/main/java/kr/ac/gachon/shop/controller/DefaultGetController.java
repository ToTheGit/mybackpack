package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/get")
public class DefaultGetController {
    //http://localhost:8080/get/html
    @GetMapping("/html")
    public String html() {
        return "get/index";
    }

    //http://localhost:8080/get/html/안녕하세요
    @GetMapping("/html/{msg}")
    public String html(@PathVariable String msg, Model m) {
        m.addAttribute("msg", msg);
        return "get/index";
    }

    //http://localhost:8080/get/txt?msg=안녕하세요
    @GetMapping("/txt")
    @ResponseBody
    public String html(@RequestParam(value="msg", required=false) String msg) {
        return msg;
    }

    //http://localhost:8080/get/json
    @GetMapping("/json")
    @ResponseBody
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model","모델값 json");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    //http://localhost:8080/get/dto?name=홍길동&phone=01011111111
    @GetMapping("/dto")
    @ResponseBody
    public UserDto html(UserDto userDto) {
        return userDto;
    }

    //http://localhost:8080/get/dto-model?name=홍길동&phone=01011111111
    @GetMapping("/dto-model")
    public String html(UserDto userDto, Model m) {
        m.addAttribute("dto", userDto);
        return "get/index";
    }
}


