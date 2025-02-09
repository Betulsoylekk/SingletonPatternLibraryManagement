package dev.patika.definexjavaspringbootbootcamp2025.entity;

import java.util.ArrayList;
import java.util.List;

public class Member {

    private String id;
    private String name;
    private String surname;

    private List<Book> retrievedBooks=new ArrayList<>();


    public List<Book> getRetrievedBooks() {
        return retrievedBooks;
    }

   public boolean addBook(Book book){
        retrievedBooks.add(book);
        return true;
   }

    public boolean removeBook(Book book){
        retrievedBooks.remove(book);
        return true;
    }

    public Member() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
