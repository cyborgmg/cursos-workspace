package br.com.lambda.dto;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Product {

    private String name;
    private Path file;
    private BigDecimal price;

    public Product(String name, Path file, BigDecimal price) {
        this.name = name;
        this.file = file;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }
    public Path getFile() {
        return this.file;
    }
    public BigDecimal getPrice() {
        return this.price;
    }
    /*public String toString() {
        return this.name;
    }*/

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", file=" + file +
                ", price=" + price +
                '}';
    }
}
