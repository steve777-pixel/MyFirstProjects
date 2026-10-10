package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Library{
    private Map<String, Book> books;
    private Map<String, Member> members;

    public Library(){
        books = new HashMap<String, Book>(); // ???
        members = new HashMap<String, Member>();
    }

    public void addBook(Book book){
        if(book == null){
            throw new IllegalArgumentException("The book cannot be null!");
        }
        if(books.containsKey(book.getISBN())){
            throw new IllegalStateException("The book with such ISBN is already available!");
        }
        books.put(book.getISBN(), book);
    }

    public void removeBook(String isbn){
        if(!books.containsKey(isbn)){
            return;
        }
        if(!books.get(isbn).isAvailable()){
            throw new IllegalStateException("Book must be available to be removed!");
        }
        books.remove(isbn);
    }

    public void registerMember(Member member){
        if(member == null){
            throw new IllegalArgumentException("The member cannot be null!");
        }
        if(members.containsKey(member.getMemberID())){
            throw new IllegalStateException("Member with such ID is already in the system!");
        }
        members.put(member.getMemberID(), member);
    }

    public void borrowBook(String memberID, String isbn){
        if(!members.containsKey(memberID)){
            throw new IllegalStateException("Member with such ID is not in the system!");
        }
        if(!books.containsKey(isbn)){
            throw new IllegalStateException("There is no book with such ISBN in the library!");
        }
        if(!books.get(isbn).isAvailable()){
            throw new IllegalStateException("The book is not available!");
        }
        members.get(memberID).addBook(books.get(isbn));
        books.get(isbn).borrowBook();
    }

    public void returnBook(String memberID, String isbn){
        if(!members.containsKey(memberID)){
            throw new IllegalStateException("Member with such ID is not in the system!");
        }
        if(!books.containsKey(isbn)){
            throw new IllegalStateException("There is no book with such ISBN in the library!");
        }
        members.get(memberID).removeBook(books.get(isbn));
        books.get(isbn).returnBook();
    }

    public Book findBook(String isbn){
        if(books.containsKey(isbn)){
            return books.get(isbn);
        }
        throw new IllegalStateException("The book with such ISBN was not found!");
    }

    public void displayAllBooks(){
        if(books.isEmpty()){
            System.out.println("There are no books to display...");
            return;
        }
        for(Book book : books.values()){
            System.out.println(book + "\n");
        }
    }

    public void displayAllTitles(){
        if(books.isEmpty()){
            System.out.println("There are no books to display...");
            return;
        }
        for(Book book : books.values()){
            System.out.println(book.getTitle());
        }
    }

    public List<String> findBooksByAuthor(String name){
        List<String> bookList = new ArrayList<>();
        if(books.isEmpty()){
            return bookList;
        }
        for(Book book : books.values()){
            if(book.getAuthor().equals(name)){
                bookList.add(book.getTitle());
            }
        }
        return bookList;
    }

    public long countAvailableBooks(){
        return this.books.values().stream().filter(Book::isAvailable).count();
    }
}