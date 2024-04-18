package com.workintech.library;

import com.workintech.library.humans.Reader;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Library {
    private Map<Long, Reader> readers;
    //Bu harita, kütüphanede bulunan okuyucuların verilerini saklar.
    // Okuyucu verilerini depolamak için hızlı bir erişim sağlayan HashMap tercih edilebilir.
    private Map<Long,Book> books;
    //Bu harita, kütüphanede bulunan kitapların verilerini saklar.
    // Kitap verileri genellikle sıralı bir şekilde listelenerek gösterilmek istenebilir.
    // Bu durumda, kitap verilerini depolamak için TreeMap kullanılabilir.
    public Library() {
        this.readers = new HashMap<>();
        this.books = new TreeMap<>();
    }
    public Map<Long, Reader> getReaders() {
        return readers;
    }

    public void setReaders(Map<Long, Reader> readers) {
        this.readers = readers;
    }

    public Map<Long, Book> getBooks() {
        return books;
    }

    public void setBooks(Map<Long, Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Library{" +
                "readers=" + readers +
                ", books=" + books +
                '}';
    }
}
