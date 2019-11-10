package ru.geekbrains.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.persistence.entity.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RequestMapping("/api/product")
@RestController
public class ProductRestController {

    public ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping(value = "/count")
    public int calculateCountOfProductEntity(){
        return productService.findAll().size();
    }

    @GetMapping(value = "/id={id}")
    public ProductRepr getProductById(@PathVariable("id") Long id){
        return productService.getProductReprById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateProduct(@RequestBody ProductRepr productRepr){
        productService.save(productRepr);
    }

    @DeleteMapping(value = "/id={id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
