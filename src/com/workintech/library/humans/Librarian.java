package com.workintech.library.humans;

import com.workintech.library.Book;
import com.workintech.library.Library;
import com.workintech.library.enums.Roles;
import com.workintech.library.enums.Status;
import com.workintech.library.enums.Type;
import com.workintech.library.interfaces.GeneralInterface;
import com.workintech.library.interfaces.LibrarianInterface;

import java.util.Iterator;
import java.util.TreeMap;

public class Librarian extends Person implements LibrarianInterface, GeneralInterface {
    private Library library;
    public Librarian(String name, String surname, Roles roles) {
        super(name, surname, roles);
    }
    public Librarian(String name,Library library){
        super(name);
        this.library=library;
    }


    @Override
    public boolean searchBook(String bookName) {
        for (Book book:library.getBooks().values()){
            if (book.getName().equalsIgnoreCase(bookName)){
                System.out.println("Kitap: " + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Başarısız.");
        return false;
    }
    //TreeMap nesnesi kullanılarak bir döngü başlatılır.
    //TreeMap kullanıldığı için kitaplar ID'ye göre sıralanmış olur.
    //TreeMap doğrudan Library sınıfında tanımlandı.

    @Override
    public void addBook(Book book) {
        if (book.getStock() == 0 && book.getStatus() == Status.NOT_IN_STOCK) {
            book.setStatus(Status.IN_STOCK);
        }
        if (library.getBooks().containsKey(book.getID())) {
            Book existingBook = library.getBooks().get(book.getID());
            existingBook.setStock(existingBook.getStock() + 1);
            System.out.println("Kitap başarıyla güncellenmiştir: " + existingBook.getName());

            //Eklenmek istenen kitabın ID'si zaten kütüphanede var mı kontrol edilir.
            //Eğer bu ID'ye sahip bir kitap varsa, bu kitap güncellenir.

        } else {
            TreeMap<Long, Book> sortedBooks = new TreeMap<>(library.getBooks());
            sortedBooks.put(book.getID(), book);
            library.setBooks(sortedBooks);
            System.out.println("Kitap başarıyla eklenmiştir: " + book.getName());

            // Eğer eklenmek istenen kitabın ID'si kütüphanede bulunmuyorsa,
            // bu kitap sortedBooks adında bir TreeMap nesnesine eklenir.
        }
    }

    @Override
    public void deleteBook(Long ID) {
        // Bu iterator, library.getBooks().values().iterator() ifadesi ile
        // library içindeki kitapların değerlerini içeren bir koleksiyon üzerinde çalışır.
        Iterator<Book> iterator = library.getBooks().values().iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getID() == ID) {
                iterator.remove();
                System.out.println("Kitap kaldırıldı: " + book.getID() + "-" + book.getName());
                return;
            }
        }
        //while döngüsü içinde, iteratorün bir sonraki elemanı varsa iterator.hasNext() ile,
        // bu eleman alınır ve Book türündeki book değişkenine atanır.
    }

    @Override
    public void updateBook(Book book, Long ID, String author, String name, double price, int stock, Status status, Type type) {
        if (searchBook(book.getName())) {
            // Güncellenmek istenen kitabın mevcut ID'si oldID adlı bir değişkende saklanır.
            Long oldID = book.getID();
            book.setStock(stock);
            book.setStatus(status);
            book.setName(name);
            book.setID(ID);
            book.setAuthor(author);
            book.setPrice(price);
            book.setType(type);
            // Daha sonra, kütüphanedeki books koleksiyonundan oldID kaldırılır ve güncellenmiş kitap yeni ID ile koleksiyona eklenir put yöntemi ile.
            library.getBooks().remove(oldID);
            library.getBooks().put(book.getID(), book);
        }
        System.out.println("Kitap güncellendi." + book);
    }
}
