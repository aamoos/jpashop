package jpabook.jpashop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
* @package : jpabook.jpashop.controller
* @name : HelloController.java
* @date : 2022-07-19 오전 10:20
* @author : 김재성
* @Description: Hello 컨트롤러
**/
@Controller
public class HelloController {

    /**
    * @methodName : hello
    * @date : 2022-07-19 오전 10:21
    * @author : 김재성
    * @Description: test 화면
    **/
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }

}
