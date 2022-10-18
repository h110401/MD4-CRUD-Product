package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rikkei.academy.model.Product;
import rikkei.academy.service.product.IProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("productList", products);
        return "product/list";
    }

    @GetMapping("/create")
    public String formCreate(Model model) {
        model.addAttribute("product", new Product());
        return "product/create";
    }

    @PostMapping("/save")
    public String create(Product product) {
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit/{id}")
    public String formEdit(Model model, @PathVariable int id) {
        Product product = productService.findById(id);
        model.addAttribute("productEdit", product);
        return "product/edit";
    }

    @PostMapping("/update")
    public String update(Product product) {
        productService.update(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String formDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "product/delete";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam int id, @RequestParam String submit) {
        if (submit.equals("Yes")) {
            productService.remove(id);
        }
        return "redirect:/product";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam String search) {
        List<Product> products = productService.search(search);
        model.addAttribute("productList", products);
        model.addAttribute("search", search);
        return "product/list";
    }
}
