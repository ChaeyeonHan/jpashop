package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")  // 상품 등록폼
    public String createForm(Model model){
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")  // 상품 등록하기
    public String create(BookForm form){
        // set으로 해도 되긴하지만, createBook 이라는 메소드 만들어서 파라미터로 넘기는게 더 나은 설계이다!
        // setter를 제거해주는 것이 좋은 설계이다. Book에 static 생성자 메소드로 만들어준다.

//        Book book = new Book();
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());

        Book book = Book.createBook(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    @GetMapping("/items")  // 상품 목록 조회하기
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")  // 상품 수정 폼
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

    @PostMapping("/items/{itemId}/edit")
    public String updateItem(@PathVariable long itemId, @ModelAttribute("form") BookForm form){

//        Book book = new Book();
//        book.setId(form.getId());  // 준영속 엔티티! -> 이미 식별자가 존재하지만 임의로 만들어냈기에
//        // 준영속 엔티티는 JPA가 관리를 안한다 -> db에 업데이트가 일어나지 않는다!
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//
//        itemService.updateItem(book.getId(), book);
//        itemService.saveItem(book);

        itemService.updateItem(itemId, form.getName(), form.getPrice(), form.getStockQuantity());
        // 필요한 데이터만 파라미터로 받아주기

        return "redirect:/items";
    }

}
