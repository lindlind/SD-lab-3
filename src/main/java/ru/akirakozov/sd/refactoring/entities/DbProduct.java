package ru.akirakozov.sd.refactoring.entities;

import java.util.Objects;

public class DbProduct {

    private final String name;
    private final Long price;

    public DbProduct(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DbProduct dbProduct = (DbProduct) o;
        return Objects.equals(name, dbProduct.name) && Objects.equals(price, dbProduct.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
