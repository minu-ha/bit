package hello.bit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Controller020 {
	
    @GetMapping("/")
    public String index(){
        return "index";
    }
    
    @GetMapping("/chat")
    public String websocket(){
        return "websocket";
    }

}
