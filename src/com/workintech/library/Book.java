package com.workintech.library;

import com.workintech.library.enums.Status;
import com.workintech.library.enums.Type;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private Long ID;
    private String author;
    private String name;

    private double price;
    private int stock;
    private Status status;
    private Type type;
    public Book(Long ID, String author, String name, double price, int stock, Status status, Type type) {
        this.ID = ID;
        this.author = author;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.type = type;
    }
    //GETTER SETTER START**********************
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        if (stock>0){
            this.status=status.IN_STOCK;
        } else {
            this.status=status.NOT_IN_STOCK;
        }
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    //GETTER SETTER END************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Double.compare(price, book.price) == 0 && stock == book.stock && Objects.equals(ID, book.ID) && Objects.equals(author, book.author) && Objects.equals(name, book.name) && status == book.status && type == book.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, author, name, price, stock, status, type);
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", author='" + author + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", status=" + status +
                ", type=" + type +
                '}';
    }

    @Override
    public int compareTo(Book book) {
        return Long.compare(this.ID, book.getID());
    }
    //Bu sayfadaki compareTo metodu, Comparable arayüzünü uygulayan Book sınıfında iki kitabı karşılaştırmak için kullanılır.
    // Ancak, bu yöntemde kitaplar, ID'lerine göre sıralanır.
    // Yani, compareTo metodunun geri dönüş değeri, bu kitabın ID'sinin karşılaştırılan kitabın ID'sine göre büyük, küçük veya eşit olmasına göre belirlenir.
    // Bu durumda, compareTo metodu, kitapların bir listesini sıralamak istediğinizde veya bir veri yapısında sıralı bir koleksiyon tutmak istediğinizde kullanılır.
}
