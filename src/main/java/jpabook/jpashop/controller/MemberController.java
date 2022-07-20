package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;
import java.util.List;

/**
* @package : jpabook.jpashop.controller
* @name : MemberController.java
* @date : 2022-07-19 오전 10:25
* @author : 김재성
* @Description: 회원기능 컨트롤러
**/
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
    * @methodName : createForm
    * @date : 2022-07-19 오전 10:25
    * @author : 김재성
    * @Description: 회원가입 화면
    **/
    @GetMapping(value = "/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    /**
    * @methodName : create
    * @date : 2022-07-19 오전 10:26
    * @author : 김재성
    * @Description: 회원가입
    **/
    @PostMapping(value = "/members/new")
    public String create(@Valid MemberForm form, BindingResult result){

        //회원가입 유효성 error시 회원가입화면으로 return
        if(result.hasErrors()){
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());
        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        //회원가입
        memberService.join(member);

        return "redirect:/";
    }

    /**
    * @methodName : list
    * @date : 2022-07-19 오전 10:49
    * @author : 김재성
    * @Description: 회원 목록
    **/
    @GetMapping(value = "/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
