/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Book;

import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Book {
    String bookId;
    String bookName;
    double bookPrice;
    int quantity;
    String publisherId;
    String status;
    Double subTotal;

    public Book(String bookId) {
        this.bookId = bookId;
    }

    public Book(String bookId, String bookName, double bookPrice, int quantity, String publisherId, String status) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
        this.publisherId = publisherId;
        this.status = status;
        this.subTotal = bookPrice * quantity;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        bookName = ((bookName.length() >= 5) && (bookName.length() <=30))? bookName : this.bookName;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = (bookPrice > 0)? bookPrice: this.bookPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = (quantity > 0)? quantity: this.quantity;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    @Override
    public String toString() {
        return String.format("|%-10s|%-30s|%-10s|%-10s|%-10s|%-10s|%20s|", bookId, bookName, bookPrice + "", quantity + "", bookPrice * quantity + "", publisherId, status);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Book book = (Book) obj;
        return book.bookId.equalsIgnoreCase(bookId);
    }
    
    
}
