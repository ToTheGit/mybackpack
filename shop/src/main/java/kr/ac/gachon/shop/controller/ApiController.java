package kr.ac.gachon.shop.controller;

import groovy.util.logging.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j

@RequestMapping("/contents")
@Controller
public class ApiController {

    @GetMapping(value="/Itaewon")
    public String Itaewon(Model model){
        return "content/itaewon";
    }
    @GetMapping(value="/Gangnam")
    public String Gangnam(Model model){
        return "content/gangnam";
    }
    @GetMapping(value="/Yeouido")
    public String Yeouido(Model model){
        return "content/yeouido";
    }
    @GetMapping(value="/Jamsil")
    public String Jamsil(Model model){
        return "content/jamsil";
    }

    @GetMapping(value="/Banpo")
    public String Banpo(Model model){
        return "content/banpo";
    }
    @GetMapping(value="/Ttukseom")
    public String Ttukseom(Model model){
        return "content/ttukseom";
    }
    @GetMapping(value="/Mangwon")
    public String Mangwon(Model model){
        return "content/mangwon";
    }
    @GetMapping(value="/Seoulforest")
    public String Seoulforest(Model model){
        return "content/seoulforest";
    }



}

