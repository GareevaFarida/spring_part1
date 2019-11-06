package ru.geekbrains.controller.repr;

import java.math.BigDecimal;

public class ProductFilter {

    private Long categoryId;

    private BigDecimal priceMin;

    private BigDecimal priceMax;

    public ProductFilter() {
    }

    public ProductFilter(Long categoryId, BigDecimal priceMin, BigDecimal priceMax) {
        this.categoryId = categoryId;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }
}
