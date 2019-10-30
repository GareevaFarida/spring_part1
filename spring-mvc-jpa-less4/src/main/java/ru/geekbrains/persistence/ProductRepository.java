package ru.geekbrains.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.geekbrains.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //List<Product> getAllByCategory_Id(Long categoryId);

    //этот метод сделан через JPQL просто ради эксперимента, чтобы убедиться, что так тоже работает
    @Query("select a from Product a where (a.category.id =:paramCategoryId or :paramCategoryId = -1)"
            +" and (:paramPriceType = 'priceNotSelected' "
            +"or (:paramPriceType = 'priceMin' and a.price >= :paramMinPrice)"
            +"or (:paramPriceType = 'priceMax' and a.price <= :paramMaxPrice)"
            +"or (:paramPriceType = 'priceMaxAndMin' and a.price between :paramMinPrice and :paramMaxPrice)"
            +") order by price")
    List<Product> getProductByCategoryAndPrice(@Param("paramCategoryId") Long categoryId,
                                               @Param("paramPriceType")String priceType,
                                               @Param("paramMinPrice")BigDecimal minPrice,
                                               @Param("paramMaxPrice")BigDecimal maxPrice
                                                               );

}
