package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

/**
* @package : jpabook.jpashop.controller
* @name : ItemController.java
* @date : 2022-07-19 오전 10:50
* @author : 김재성
* @Description: 주문기능 컨트롤러
**/
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    /**
    * @methodName : createForm
    * @date : 2022-07-19 오전 10:50
    * @author : 김재성
    * @Description: 상품등록 화면
    **/
    @GetMapping(value = "/items/new")
    public String createForm(Model model){
        model.addAttribute("bookForm", new BookForm());
        return "items/createItemForm";
    }

    /**
    * @methodName : create
    * @date : 2022-07-19 오전 11:16
    * @author : 김재성
    * @Description: 상품등록
    **/
    @PostMapping(value = "/items/new")
    public String create(@Valid BookForm form, BindingResult result){

        //회원가입 유효성 error시 회원가입화면으로 return
        if(result.hasErrors()){
            return "items/createItemForm";
        }

        Book book = new Book();
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        //상품 등록
        itemService.saveItem(book);
        return "redirect:/items";
    }
    /**
    * @methodName : list
    * @date : 2022-07-19 오전 11:18
    * @author : 김재성
    * @Description: 상품 목록
    **/
    @GetMapping(value = "/items")
    public String list(Model model){

        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    /**
    * @methodName : updateItemForm
    * @date : 2022-07-19 오전 11:18
    * @author : 김재성
    * @Description: 상품 수정 화면
    **/
    @GetMapping(value = "/items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){

        Book item = (Book) itemService.findOne(itemId);

        BookForm form = new BookForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);

        return "items/updateItemForm";
    }

    /**
    * @methodName : updateItem
    * @date : 2022-07-19 오전 11:21
    * @author : 김재성
    * @Description: 상품 수정
    **/
    @PostMapping(value = "/items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") BookForm form){

        /*
        Book book = new Book();
        book.setId(form.getId());
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);

         */
        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());

        return "redirect:/items";
    }
}
