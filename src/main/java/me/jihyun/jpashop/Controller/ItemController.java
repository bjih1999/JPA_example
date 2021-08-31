package me.jihyun.jpashop.Controller;

import lombok.RequiredArgsConstructor;
import me.jihyun.jpashop.domain.Item.Book;
import me.jihyun.jpashop.service.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("items/new")
    public String create(BookForm form) {
        Book book = new Book();
        /*
        setter를 제거하고 Book.createBook 메소드를 만들어 리턴 받는 것이 더 좋은 설계
         */
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setISBN(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";


    }
}
