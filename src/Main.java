import com.workintech.library.Book;
import com.workintech.library.Library;
import com.workintech.library.enums.Status;
import com.workintech.library.enums.Type;
import com.workintech.library.humans.Librarian;
import com.workintech.library.humans.Reader;

public class Main {
    public static void main(String[] args) {
        System.out.println("Kütüphane");
        Library library=new Library();
        //Kitaplar
        Book book1=new Book(1L,"Gege Akutami","Jujutsu Kaisen",150,2,Status.IN_STOCK, Type.MANGA);
        Book book2=new Book(2L,"Hajime Isayama","Attack on Titan",30, 4, Status.IN_STOCK, Type.MANGA);
        Book book3=new Book(3L,"Masashi Kishimoto","Naruto",40, 14, Status.IN_STOCK, Type.MANGA);
        Book book4=new Book(4L,"Geoff Johns","Justice Leauge",20, 4, Status.IN_STOCK, Type.COMIC);
        Book book5=new Book(5L,"Robert Kiyosaki","Zengin Baba Yoksul Baba",85, 4, Status.IN_STOCK, Type.HORROR);
        Book book6=new Book(6L,"Khaled Hosseini","Uçurtma Avcısı",85, 0, Status.NOT_IN_STOCK, Type.CLASSICS);

        //Eklenen kitaplar.
        library.getBooks().put(book1.getID(), book1);
        library.getBooks().put(book2.getID(), book2);
        library.getBooks().put(book3.getID(), book3);
        library.getBooks().put(book4.getID(), book4);
        System.out.println("***Kitaplar***");

        for (Book book : library.getBooks().values()) {
            System.out.println(book);
        }
        System.out.println("*******************************************");

        System.out.println("Kitap Ekleme Bölümü");
        Librarian librarian = new Librarian("Hamza", library);
        librarian.addBook(book1);
        librarian.addBook(book2);
        librarian.addBook(book3);
        librarian.addBook(book4);
        librarian.addBook(book5);
        librarian.addBook(book6);

        System.out.println("*******************************************");
        System.out.println("Kitap Arama Bölümü");
        librarian.searchBook("Jujutsu Kaisen");
        librarian.searchBook("JUJUTSU KAİSEN");
        librarian.searchBook("Kürk Mantolu Madonna");

        System.out.println("*******************************************");
        System.out.println("Kitap Silme Bölümü");
        librarian.deleteBook(4L);

        System.out.println("*******************************************");
        System.out.println("Kitap Güncelleme Bölümü");
        librarian.updateBook(book5,8L,"Yusuke Murata","One-Punch Man",50, 5,Status.IN_STOCK,Type.MANGA);
        System.out.println("Güncellenmiş Kitaplar");
        for (Book book : library.getBooks().values()) {
            System.out.println(book);
        }

        System.out.println("*******************************************");

        System.out.println("Üye Giriş Bölümü");
        Reader reader1=new Reader("Muhammed Hamza","Toptancı","toptanci@hamza.com","hamzhamz",200,library);

        Reader reader2=new Reader("Ali","Toptancı","ali@toptanci.com","aliali.",50,library);

        Reader reader3=new Reader("Veli","Toptancı","veli@toptanci.com","veliveli",45,library);

        //Doğru Giriş
        reader1.login("Muhammed Hamza","Toptancı","toptanci@hamza.com","hamzhamz");
        reader2.login("Ali","Toptancı","ali@toptanci.com","aliali.");
        reader3.login("Veli","Toptancı","veli@toptanci.com","veliveli");

        //Hatalı Giriş
        reader1.login("Muhammed Hamza","Toptancı","toptanci@hamza.com","hamzınk");
        reader1.login("Muhammed Hamza","Toptancı","tsafsnci@hamza.com","hamzhamz");

        System.out.println("*******************************************");

        System.out.println("Okuyucunun Kitap Araması");
        reader1.searchBook("İki Keklik");

        System.out.println("*******************************************");

        System.out.println("Okuyucunun Kitap Ödünç Alması");
        //Kitap Alım İşlemleri
        reader1.borrow("Jujutsu Kaisen");
        reader1.borrow("Attack on Titan");
        reader1.borrow("Naruto");
        reader1.borrow("test");
        reader1.borrow("Naruto");
        reader1.displayBorrowedBooks();
        System.out.println("Bakiye: " +reader1.getMoney());

        System.out.println("*******************************************");
        System.out.println("Okuyucunun İade Etmesi");

        reader1.returnBook("Jujutsu Kaisen",true);
        reader1.returnBook("Attack on Titan",false);
        System.out.println("Bakiye: " +reader1.getMoney());
    }
}