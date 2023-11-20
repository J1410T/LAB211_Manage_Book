/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Book;

import Publisher.Publisher;
import Publisher.PublisherList;
import Service.InputService;
import Service.MenuService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Asus
 */
public class BookList extends ArrayList<Book> {

    String fName = "src\\Data\\Books.dat";

    private final PublisherList pubList;
    private ArrayList<Book> tmpList = new ArrayList<Book>();

    public BookList(PublisherList pubList) {
        this.pubList = pubList;
        this.readFile();
    }

    public void addBook() {
        String bookId;
        do {
            bookId = InputService.inputPattern("Enter Book's ID (format Bxxxxx): ", "[Bb][0-9]{5}", "Bxxxxx").toUpperCase();
            if (searchBook(bookId) != null) {
                System.out.println("Book " + bookId + " existed!!");
            }
        } while (searchBook(bookId) != null);
        String bookName = InputService.inputPattern("Enter Book's name: ", "[a-z A-Z0-9]{5,30}", "5 - 30 characters").toUpperCase();
        double bookPrice = InputService.inputDouble("Enter Book's price: ", 0, Double.MAX_VALUE);
        int quantity = InputService.inputInt("Enter Book's quantity: ", 0, Integer.MAX_VALUE);
        String status = quantity > 0 ? "AVAILABLE" : "UNAVAILABLE";
        String publisherId = InputService.inputPattern("Enter Publisher's ID (format Pxxxxx): ", "[Pp][0-9]{5}", "Pxxxxx").toUpperCase();
        if (pubList.searchPublisher(publisherId) == null) {
            System.out.println("Publisher " + publisherId + " does not existed!!");
            return;
        }

        this.add(new Book(bookId, bookName, bookPrice, quantity, publisherId, status));
        System.out.println(" ADD BOOK SUCCESSFUL!!!");
        System.out.println("");
    }

    private Book searchBook(String bookId) {
        for (Book b : this) {
            if (b.bookId.equalsIgnoreCase(bookId)) {
                return b;
            }
        }
        return null;
    }

    public void searchBook() {
        tmpList.clear();
        MenuService menuChoice = new MenuService("What type do you want to search?", "Enter your choice: ");
        menuChoice.addOptions("Part of Book name", "Publisher ID");
        menuChoice.display();
        int choice = InputService.inputInt("Enter your choice: ", 1, 2);
        switch (choice) {
            case 1:
                System.out.print("Enter the Book you want to search: ");
                String partBook = InputService.SC.nextLine().trim().toUpperCase();
                for (Book b : this) {
                    if (b.getBookName().contains(partBook)) {
                        tmpList.add(b);
                    }
                }
                break;
            case 2:
                System.out.print("Enter Publisher'ID you want to search: ");
                String publisherId = InputService.SC.nextLine().trim().toUpperCase();
                for (Book b : this) {
                    if (b.getPublisherId().equalsIgnoreCase(publisherId)) {
                        tmpList.add(b);
                    }
                }
                break;
        }
        if (tmpList.isEmpty()) {
            System.out.println("The Book does not exist!!");
        } else {
            for (Book b : tmpList) {
                System.out.println(b.toString());
            }
        }
    }

    public void updateBook() {
        String bookId = InputService.inputPattern("Enter Book ID (format Bxxxxx): ", "[Bb][0-9]{5}", "Bxxxxx").toUpperCase();
        Book book = searchBook(bookId);
        String tmp;
        double current;
        if (book == null) {
            System.out.println("BookID does not existed!!");
        } else {
            MenuService updateChoice = new MenuService("Element you want to change: ", "Enter your choice: ");
            updateChoice.addOptions("Name", "Price", "Publisher ID", "Quantity", "Quit");
            int choice;
            do {
                updateChoice.display();
                choice = updateChoice.getUserChoice();

                switch (choice) {
                    case 1:
                        System.out.println("Old name: " + book.getBookName());
                        System.out.print("Enter new name: ");
                        tmp = InputService.SC.nextLine().toUpperCase();
                        if (tmp != "") {
                            book.setBookName(tmp);
                            if (book.getBookName().equals(tmp)){
                                System.out.println("UPDATE SUCCESSFUL!!");
                                System.out.println("");
                            }
                            else {
                                System.out.println("UPDATE FAILED!!");
                                System.out.println("");
                            }
                        } else {
                            System.out.println("UPDATE FAILED!!");
                            System.out.println("");
                        }
                        break;
                    case 2:
                        while (true) {
                            try {
                                System.out.println("Old price: " + book.getBookPrice());
                                System.out.print("Enter new price: ");
                                tmp = InputService.SC.nextLine();
                                if (tmp != "") {
                                    book.setBookPrice(Double.parseDouble(tmp));
                                    if (book.getBookPrice() == Double.parseDouble(tmp)) {
                                        System.out.println("UPDATE SUCCESSFUL!");
                                        System.out.println("");
                                    } else {
                                        System.out.println("UPDATE FAILED!!");
                                        System.out.println("");
                                    }

                                } else {
                                    System.out.println("UPDATE FAILED!!");
                                    System.out.println("");
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Old Publisher ID (Pxxxxx): " + book.getPublisherId());
                        System.out.print("Enter new Publisher ID: ");
                        String publisherId = InputService.SC.nextLine().toUpperCase();
                        if (!publisherId.equals("")) {
                            if (pubList.searchPublisher(publisherId) == null) {
                                System.out.println("Publisher " + publisherId + " does not existed!!");
                                System.out.println("UPDATE FAILED!!");
                                System.out.println("");
                                continue;
                            }

                            book.setPublisherId(publisherId);
                            System.out.println("UPDATE SUCCESSFUL!");
                            System.out.println("");
                        } else {
                            System.out.println("UPDATE FAILED!!");
                            System.out.println("");
                        }
                        break;
                    case 4:
                        while (true) {
                            try {
                                System.out.println("Old quantity: " + book.getQuantity());
                                System.out.print("Enter new quantity: ");
                                tmp = InputService.SC.nextLine();
                                if (tmp != "") {
                                    book.setQuantity(Integer.parseInt(tmp));
                                    String status = book.getQuantity() > 0 ? "AVAILABLE" : "UNAIVAILABLE";
                                    book.setStatus(status);
                                    if (book.getQuantity() == Integer.parseInt(tmp)) {
                                        System.out.println("UPDATE SUCCESSFUL!");
                                        System.out.println("");
                                    } else {
                                        System.out.println("UPDATE FAILED!!");
                                        System.out.println("");
                                    }
                                } else {
                                    System.out.println("UPDATE FAILED!!");
                                    System.out.println("");
                                }
                                break;
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                        break;
                }
            } while (choice > 0 && choice < 5);

        }
    }

    public void readFile() {
        File f = new File(fName);
        if (!f.exists()) {
            System.out.println("File is not existed!!");
            System.out.println("Failed!!");
            System.out.println("");
            System.exit(0);
        }
        try {
            FileReader fr = new FileReader("src\\Data\\Books.dat");
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String element[] = line.split("[|]");
                String bookId = element[1].trim().toUpperCase();
                String bookName = element[2].trim().toUpperCase();
                double bookPrice = Double.parseDouble(element[3].trim());
                int quantity = Integer.parseInt(element[4].trim());
                String publisherId = element[6].trim().toUpperCase();
                if (pubList.searchPublisher(publisherId) == null) {
                    System.out.println("Publisher " + publisherId + " does not existed!!");
                    continue;
                }
                String status = element[7].trim().toUpperCase();
                this.add(new Book(bookId, bookName, bookPrice, quantity, publisherId, status));
            }
            System.out.println("Read Book File success!!");
            System.out.println("");
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println("Not found file BOOK");
            System.out.println("");
        }
    }

    public void removeBook() {
        //B1: Tim duoc cai cho xoa
        //B2: Xoa
        System.out.print("Enter book ID: ");
        String bookId = InputService.SC.nextLine().trim().toUpperCase();
        if (searchBook(bookId) == null) {
            System.out.println("Book " + bookId + " is not existed!!");
            System.out.println("REMOVE FAILED!!!");
            System.out.println("");
        } else {
            this.remove(searchBook(bookId));
            System.out.println("REMOVE SUCCESS!!!");
            System.out.println("");
        }
    }

    public void printOut() {
        if (this.size() == 0) {
            System.out.println("Empty!!!");
            return;
        }
        Collections.sort(this, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                if (o1.quantity == o2.quantity) {
                    return (int) ((int) o1.bookPrice - o2.bookPrice);
                }
                return o1.quantity - o2.quantity;
            }

        });
        for (Book b : this) {
            System.out.println(b.toString());
        }
    }

    public void writeFile() {
        try {
            FileWriter fw = new FileWriter(fName);
            BufferedWriter bw = new BufferedWriter(fw);
            Collections.sort(this, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getBookId().compareTo(o2.getBookId());
                }

            });

            for (Book b : this) {
                bw.write(b.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("SAVE SUCCESSFULLY!!");
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Not found file");
            System.out.println("");
        }
    }
}
