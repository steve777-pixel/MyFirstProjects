package models;

import utils.ValidationUtils;

public class Book{
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn){
        this.title = ValidationUtils.validateNotBlank(title, "Book title");
        this.author = ValidationUtils.validateNotBlank(author, "Author name");
        this.isbn = ValidationUtils.validateNotBlank(isbn, "ISBN");
        this.available = true;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getISBN(){
        return isbn;
    }

    public boolean isAvailable(){
        return available;
    }

    public void borrowBook(){
        // Library handles the rest
        // if(available){
        //     System.out.println("Book is borrowed!");
        //     this.available = false;
        // }else{
        //     System.out.println("Book is unavailable!");
        // }
        if(!available){
            throw new IllegalStateException("The book is already borrowed.");
        }
        this.available = false;
    }

    public void returnBook(){
        if(available){
            throw new IllegalStateException("The book is already available.");
        }
        this.available = true;
    }

    @Override
    public String toString(){
        return "Title: " + title + "\nAuthor: " + author + "\nISBN: " + isbn + "\nAvailable: " + available;
        // return String.format(
        //     "Title: %s%nAuthor: %s%nISBN: %s%nAvailable: %b",
        //     title,
        //     author,
        //     isbn,
        //     available
        // );
    }
}