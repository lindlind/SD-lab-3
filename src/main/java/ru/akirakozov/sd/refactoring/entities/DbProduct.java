package ru.akirakozov.sd.refactoring.entities;

public class DbProduct {

    private String name;
    private Long price;

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

}
