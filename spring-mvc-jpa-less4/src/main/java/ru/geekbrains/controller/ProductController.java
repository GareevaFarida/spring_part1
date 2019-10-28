package ru.geekbrains.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.geekbrains.persistence.CategoryRepository;
import ru.geekbrains.persistence.ProductRepository;
import ru.geekbrains.persistence.entity.Category;
import ru.geekbrains.persistence.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
@RequestMapping("products")
@Component
public class ProductController {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    public ProductController(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String products(@RequestParam(name = "categoryId", required = false) Long categoryId,
                           @RequestParam(name = "priceSelected", required = false) String priceSelected,
                           Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        if (categoryId == null || categoryId == -1) {
            model.addAttribute("products", productRepository.findAll());
        } else {
            model.addAttribute("products", productRepository.getAllByCategory_Id(categoryId));
        }


        if (priceSelected == "priceNotSelected") {
            model.addAttribute("categories", categoryRepository.findAll());
            if (categoryId == null || categoryId == -1) {
                model.addAttribute("products", productRepository.findAll());
            } else {
                model.addAttribute("products", productRepository.getAllByCategory_Id(categoryId));
            }
        } else {
            String sqltext_min;
            if (priceSelected == "priceMax") {
                sqltext_min = new String("select a from Product a where a.category.id = :paramCategoryId order by price desc");

            } else if (priceSelected == "priceMin") {
                sqltext_min = new String("select a from Product a where a.category.id = :paramCategoryId order by price");
            } else if (priceSelected == "priceMaxAndMin") {
                sqltext_min = new String("select a from Product a where a.category.id = :paramCategoryId order by price desc");
            }
            TypedQuery<Product> query = em.createQuery(sqltext_min, Product.class);
            query.setParameter("paramCategoryId", categoryId);
            List<Product> allProducts = query.getResultList();
            model.addAttribute("products", allProducts.get(0));
        }


        return "products";
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createProductFrom(@RequestParam("categoryId") Long categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalStateException("Category not found"));
        Product product = new Product();
        product.setCategory(category);
        model.addAttribute("product", product);
        return "product";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute("product") Product product) {
        product.setCategory(categoryRepository.findById(product.getCategoryId())
                .orElseThrow(() -> new IllegalStateException("Category not found")));
        productRepository.save(product);
        return "redirect:/categories/edit?id=" + product.getCategory().getId();
    }
}
