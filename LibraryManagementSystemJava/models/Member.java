package models;

import java.util.ArrayList;
import utils.ValidationUtils;

public class Member{
    private String name;
    private String memberId;
    private final ArrayList<Book> borrowedBooks;

    public Member(String name, String memberId){
        this.name = ValidationUtils.validateNotBlank(name, "Member name");
        this.memberId = ValidationUtils.validateNotBlank(memberId, "Member ID");
        borrowedBooks = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public String getMemberID(){
        return memberId;
    }

    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("Book cannot be null!");
        }
        borrowedBooks.add(book);
    }

    public void removeBook(Book book){
        if(borrowedBooks.isEmpty()){
            throw new IllegalStateException("The member has not borrowed any books!");
        }
        if(!borrowedBooks.contains(book)){
            throw new IllegalArgumentException("The member has not borrowed such a book!");
        }
        borrowedBooks.remove(book);
    }

    public void displayBorrowedBooks(){
        if(borrowedBooks.isEmpty()){
            System.out.println( "The member with ID " + memberId + " has not borrowed any books!");
            return;
        }
        for(Book book : borrowedBooks){
            System.out.println(book.getTitle());
        }
    }

    @Override
    public String toString(){
        return "Name: " + name + "\nMember ID: " + memberId;
    }
}