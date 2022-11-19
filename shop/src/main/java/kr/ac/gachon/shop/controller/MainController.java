package kr.ac.gachon.shop.controller;

import ch.qos.logback.classic.Logger;
import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model){
        Logger log = null;
        log.info("main 컨트롤러");
        return "main";
    }
}