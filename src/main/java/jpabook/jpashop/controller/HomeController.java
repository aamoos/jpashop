package jpabook.jpashop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @package : jpabook.jpashop.controller
* @name : HomeController.java
* @date : 2022-07-19 오전 10:10
* @author : 김재성
* @Description: home 컨트롤러
**/
@Controller
@Slf4j
public class HomeController {

    /**
    * @methodName : home
    * @date : 2022-07-19 오전 10:24
    * @author : 김재성
    * @Description: home 화면
    **/
    @RequestMapping("/")
    public String home(){
        log.info("home controller");
        return "home";
    }
}
