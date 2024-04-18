package com.workintech.library;

import com.workintech.library.enums.Status;

import java.text.Collator;
import java.time.LocalDate;
import java.util.Locale;

public class Bill implements Comparable<Bill> {
    private LocalDate date;
    private Long ID;
    private String bookname;
    private Status status;
    private double price;

    public Bill(LocalDate date, Long ID, String bookname, Status status, double price) {
        this.date = date;
        this.ID = ID;
        this.bookname = bookname;
        this.status = status;
        this.price = price;
    }
    public void billPrinter(){
        System.out.println("*****************");
        System.out.println("Tarih:" + date);
        System.out.println("ID :" + ID);
        System.out.println("İsim:" + bookname);
        System.out.println("Durum:" + status);
        System.out.println("Fiyat:" + price);
        System.out.println("******************");
    }

    @Override
    public String toString() {
        return "Bill{" +
                "date=" + date +
                ", ID=" + ID +
                ", bookname='" + bookname + '\'' +
                ", status=" + status +
                ", price=" + price +
                '}';
    }
    @Override
    public int compareTo(Bill bill) {
        Collator collator = Collator.getInstance(new Locale("tr", "TR"));
        int nameComparison = collator.compare(this.bookname, bill.bookname);
        if (bill.date.isEqual(this.date)) {
            return nameComparison;
        }
        return bill.date.compareTo(this.date);
    }
    //compareTo metodunun kullanılmaması durumunda, Bill sınıfı Comparable arabirimini uygulamayacağı için,
    // bu sınıfın nesneleri doğrudan karşılaştırılamaz veya sıralanamaz.
    // Örneğin, Collections.sort() gibi sıralama işlemlerinde veya TreeSet gibi veri yapılarında otomatik sıralama yapılamaz.
}
