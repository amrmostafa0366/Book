package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Book extends BaseEntity<Long> {
//    @Min(value = 50)
//    @Max(value = 5000)
    private double price;
    @Transient
    private double discount;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;

    public Book() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @PostLoad
    private void initializeDiscount() {
        this.setDiscount(price * .25);
    }

}
