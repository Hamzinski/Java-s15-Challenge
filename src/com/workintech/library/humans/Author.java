package com.workintech.library.humans;

import com.workintech.library.Book;
import com.workintech.library.enums.Roles;

import java.util.Objects;
import java.util.Set;

public class Author extends Person {
    private Set<Book> books;
    private int id;
    public Author(String name, String surname,Set<Book> books,Integer id) {
        super(name, surname, Roles.AUTHOR);
        this.books=books;
        this.id=id;
    }
    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Author{" +
                "books=" + books +
                ", id=" + id +
                '}';
    }
}
