package kr.ac.gachon.shop.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j

@RequestMapping("/categories")
@Controller
public class CategoryController {

    @GetMapping(value = "Downtown")
    public String Downtown(Model model) {
        return "category/downtown";
    }
    @GetMapping(value = "Hanriver")
    public String Hanriver(Model model) {
        return "category/hanriver";
    }
}
