package app;

import models.*;

public class Index {
    public static void main(String[] args){
        Library library1 = new Library();
        Book WarAndPeace = new Book("War and Peace", "Leo Tolstoy", "978-0-306-40615-7");
        Member Steve = new Member("Steve", "ISA7889012");
        library1.addBook(WarAndPeace);
        library1.registerMember(Steve);

        // System.out.println(WarAndPeace);
        // WarAndPeace.borrowBook();
        // System.out.println(WarAndPeace.isAvailable());
        // WarAndPeace.returnBook();
        // System.out.println(WarAndPeace.isAvailable());

        // System.out.println(Steve.getName());
        // System.out.println(Steve.getMemberID());
        // System.out.println(Steve);
        // Steve.displayBorrowedBooks();

        // System.out.println(WarAndPeace.isAvailable());
        // library1.borrowBook(Steve.getMemberID(), WarAndPeace.getISBN());
        // Steve.displayBorrowedBooks();
        // System.out.println(WarAndPeace.isAvailable());
        // library1.returnBook(Steve.getMemberID(), WarAndPeace.getISBN());
        // Steve.displayBorrowedBooks();
        // System.out.println(WarAndPeace.isAvailable());

        // library1.findBook("9832-232-43");
        // library1.displayAllBooks();
        // library1.removeBook(WarAndPeace.getISBN());
        // library1.displayAllBooks();
        // library1.displayAllTitles();
    }
}
