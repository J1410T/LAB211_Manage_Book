/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Asus
 */

import Book.Book;
import Book.BookList;
import Publisher.Publisher;
import Publisher.PublisherList;
import Service.MenuService;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * 
 */
public class Main {

    public static void main(String[] args) {
        MenuService menuObject = new MenuService("BOOK MANAGEMENT STORE", "Enter your choice: ");
        menuObject.addOptions("Publisher", "Book", "Quit");

        MenuService menuPublisher = new MenuService("Menu Publisher", "Enter your choice: ");
        menuPublisher.addOptions(
                "Add a Publisher",
                "Delete the Publisher",
                "Save the Publishers list to file",
                "Print the Publisher list from the file",
                "Go back main menu");

        MenuService menuBook = new MenuService("Menu Book", "Enter your choice: ");
        menuBook.addOptions(
                "Add a Book",
                "Search the Book",
                "Update a Book",
                "Delete the Book",
                "Save the Books list to file",
                "Print the Books list from the file",
                "Go back main menu");

        int check1, check2, check3;
        PublisherList pubList = new PublisherList();
        BookList bookList = new BookList(pubList);
        
        do {
            menuObject.display();
            check1 = menuObject.getUserChoice();
            switch (check1) {
                case 1:
                    do {
                        menuPublisher.display();
                        check2 = menuPublisher.getUserChoice();
                        switch (check2) {
                            case 1:
                                pubList.addPublisher();
                                break;
                            case 2:
                                pubList.deletePublisher();
                                break;
                            case 3:
                                pubList.writeFile();
                                break;
                            case 4:
                                pubList.printOut();
                                break;
                        }
                        
                    } while (check2 > 0 && check2 < 5);
                    break;
                case 2:
                    do{
                        menuBook.display();
                        check3 = menuBook.getUserChoice();
                         switch (check3){
                            case 1:
                                bookList.addBook();
                                break;
                            case 2:
                                bookList.searchBook();
                                break;
                            case 3:
                                bookList.updateBook();
                                break;
                            case 4:
                                bookList.removeBook();
                                break;
                            case 5:
                                bookList.writeFile();
                                break;
                            case 6:
                                bookList.printOut();
                                break;
                            
                        }
                    } while (check3 > 0 && check3 < 7);
            }
        } while (check1 > 0 && check1 < 3);

    }
}
