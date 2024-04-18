package com.workintech.library.interfaces;
import com.workintech.library.Book;
import com.workintech.library.enums.Status;
import com.workintech.library.enums.Type;
public interface LibrarianInterface {

    void addBook(Book book );
    void deleteBook(Long ID);
    void updateBook(Book book, Long ID, String author, String name, double price, int stock, Status status, Type type);
}
