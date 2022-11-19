package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/post")
public class DefaultPostController {

    @PostMapping("/html")
    public String html() {
        return "post/index";
    }

    @PostMapping("/html/{msg}")
    public String html(@PathVariable String msg, Model m) {
        m.addAttribute("msg", msg);
        return "post/index";
    }

    @PostMapping("/txt")
    @ResponseBody
    public String html(@RequestParam(value="msg", required=false) String msg) {
        return msg;
    }

    @PostMapping("/json")
    @ResponseBody
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model","모델값 json");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @PostMapping("/dto")
    @ResponseBody
    public UserDto html(UserDto userDto) {
        return userDto;
    }

    @PostMapping("/dto-model")
    public String html(UserDto userDto, Model m) {
        m.addAttribute("dto", userDto);
        return "post/index";
    }
}