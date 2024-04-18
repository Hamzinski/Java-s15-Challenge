package com.workintech.library.humans;

import com.workintech.library.Bill;
import com.workintech.library.Book;
import com.workintech.library.Library;
import com.workintech.library.enums.Status;
import com.workintech.library.interfaces.GeneralInterface;
import com.workintech.library.interfaces.ReaderInterface;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Reader extends Person implements ReaderInterface, GeneralInterface {
    private String id;
    private String password;
    private double money;
    private Library library;
    private List<Bill> bills;
    private List<Book> borrowedBooks;

    //LinkedList kullanımı, listenin elemanlarının sık sık eklendiği veya çıkarıldığı,
    // ancak belirli bir sıra veya indeks gerektirmeyen durumlarda yaygın olarak tercih edilir.

    public Reader(String name, String surname, String email, String password, double money, Library library) {
        super(name, surname, email);
        this.password = password;
        this.money = money;
        this.library = library;
        this.borrowedBooks = new LinkedList<>();
        this.bills = new LinkedList<>();
    }
    //GETTER-SETTER START******************
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    //GETTER-SETTER END******************

    @Override
    public boolean searchBook(String bookName) {
        for (Book book : library.getBooks().values()) {
            if (book.getName().equalsIgnoreCase(bookName)) {
                System.out.println("Kitap: "  + book.getID() + "- " + book.getName() + "  (" + book.getAuthor() + ")" + "  Stock:" + book.getStock());
                return true;
            }
        }
        System.out.println("Başarısız.");
        return false;
    }

    //library.getBooks().values(), kütüphanedeki kitapların bir Map'ini döndürür ve
    //kitapları içeren bir Collection'ı temsil eder.
    // Büyük-küçük harf farkına bakılmaksızın gerçekleşir (equalsIgnoreCase).

    @Override
    public void borrow(String requestBook) {
        //5ten fazla alamama durumu.

        if (borrowedBooks.size() >= 5) {
            System.out.println("Başarısız.");
            return;
        }

        //Bu döngü, okuyucunun daha önce ödünç aldığı kitapların listesini kontrol eder.

        for (Book borrowedBook : borrowedBooks) {
            if (requestBook.equalsIgnoreCase(borrowedBook.getName())) {
                System.out.println(requestBook + " kitabını zaten aldınız.");
                return;
            }
        }

        // Kitabın var olma durumu
        for (Book book : library.getBooks().values()) {
            if (requestBook.equalsIgnoreCase(book.getName()) && book.getStock() > 0) {

                // Stokta yoksa kitabı vermeme durumu
                if (book.getStatus() == Status.NOT_IN_STOCK) {
                    System.out.println(requestBook + " kitabı stokta yok.");
                    return;
                }

                double bookPrice = book.getPrice();

                //Bakiye durumu
                if (this.getMoney() >= bookPrice) {

                    borrowedBooks.add(book);
                    book.setStock(book.getStock() - 1);
                    this.setMoney(this.getMoney() - bookPrice);

                    //Okuyucunun borrowedBooks listesine ödünç alınan kitap eklenir.
                    //Kitabın stoku bir azaltılır.
                    //Okuyucunun bakiyesinden kitabın fiyatı düşülür.

                    Bill bill = new Bill(LocalDate.now(), book.getID(), book.getName(), book.getStatus(), book.getPrice());
                    bills.add(bill);

                    //Bir fatura oluşturulur ve faturalar listesine eklenir.

                    if (book.getStatus() == Status.IN_STOCK) {
                        System.out.println(requestBook + " kitabi alindi. Ödeme: " + bookPrice + " TL");
                        bill.billPrinter();
                    }
                    return;
                } else {
                    System.out.println("Para yoksa kitapta yok. İyi günler dileriz.");
                    return;
                }
            }
        }
        System.out.println(requestBook + " kitabi stokta yok.");
    }

    @Override
    public void returnBook(String returnedBook, boolean isDamaged) {
        for (Book book : borrowedBooks) {
            if (book.getName().equalsIgnoreCase(returnedBook)) {
                borrowedBooks.remove(book);
                // İade
                book.setStock(book.getStock() + 1);
                if (isDamaged) {
                    System.out.println(returnedBook + " kitabı kusurlu. İade reddedildi.");
                    return;
                } else {
                    //Eğer kitap hasarlı değilse, kitabın fiyatının yüzde 1.50'si oranında bir geri ödeme yapılır.
                    double refundedAmount = book.getPrice() / 1.50;
                    this.setMoney(this.getMoney() + refundedAmount);
                    System.out.println(returnedBook + " iade edildi. Geri ödeme: " + refundedAmount + " TL");
                    return;
                }
            }
        }
        //Eğer döngü tamamlandığında iade edilmek istenen kitap borrowedBooks listesinde bulunamazsa, "Hata." mesajı yazdırılır.
        System.out.println("Hata.");
    }
    @Override
    public boolean login(String inputName, String inputSurname, String inputEmail, String inputPassword) {
        if(password.equals(inputPassword) && inputEmail == getEmail()  ){
            System.out.println("Üye Girişi: " + inputName + " " + inputSurname);
            return true;
        }
        System.out.println("Başarısız giriş denemesi.");
        return false;
    }
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Alınan kitabınız yoktur.");
            return;
        }

        System.out.println("Alınan kitaplar:");
        for (Book book : borrowedBooks) {
            System.out.println("- " + book.getName());
        }
    }
}



