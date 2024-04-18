package com.workintech.library.interfaces;

public interface ReaderInterface {
    void borrow(String book);
    void returnBook(String returnedBook,boolean isDamaged);

    boolean login(String inputName,String inputSurname,String inputEmail, String inputPassword);
}
