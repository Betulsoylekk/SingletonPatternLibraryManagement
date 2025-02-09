package dev.patika.definexjavaspringbootbootcamp2025.service;

import dev.patika.definexjavaspringbootbootcamp2025.entity.Book;
import dev.patika.definexjavaspringbootbootcamp2025.entity.Member;
import dev.patika.definexjavaspringbootbootcamp2025.exception.BookDoesNotExistsException;
import dev.patika.definexjavaspringbootbootcamp2025.exception.BookIsNotAvailableException;
import dev.patika.definexjavaspringbootbootcamp2025.exception.ISBNAlreadyExistException;
import dev.patika.definexjavaspringbootbootcamp2025.exception.MemberDoesNotHaveTheBookException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*Library must be shared across all users to provide consistency. This is why I preferred Singleton Design Pattern.
This way, the client would not be concerned about ensuring that the data is kept consistent or about managing different instances of the library. Every time the client interacts with the LibraryImpl,
they are interacting with the same shared collection of books
 */

public class LibraryImpl extends Library {
    private static Library library;
    private List<Book> bookList;


    // Private constructor to prevent instantiation
    private LibraryImpl() {
        this.bookList = Collections.synchronizedList(new ArrayList<>());

    }



    // Static method to initialize the library list
    public static Library initializeLibrary() {
        if (library == null) {
            synchronized (LibraryImpl.class) {
                if (library == null) { // Double-check locking for thread safety
                    library = new LibraryImpl();
                }
            }
        }
        return library;
    }

    public void clearBookList() {
        bookList.clear();
    }


    @Override
    public boolean addBook(Book book) throws ISBNAlreadyExistException {
            if (bookList.contains(book)) {
                throw new ISBNAlreadyExistException();
            }
            bookList.add(book);
            return true;
        }



    @Override
    public boolean updateBook(Book book) throws BookDoesNotExistsException {
        Book currentBook = this.getBook(book.getIsbn());
        currentBook.setTitle(book.getTitle());
        currentBook.setAuthor(book.getAuthor());
        currentBook.setAvailable(book.isAvailable());
        return true;
    }


    @Override
    public boolean removeBook(String isbn) throws BookDoesNotExistsException {
        Book book=this.getBook(isbn);
        bookList.remove(book);
        return true;
    }


    @Override
    public boolean lendBookToMember(String isbn, Member member) throws BookIsNotAvailableException {
        Book book = this.getBook(isbn);

        synchronized (book) { // Lock the book to prevent it from being checked by multiple threads concurrently.
            if (!book.isAvailable()) {
                throw new BookIsNotAvailableException();
            }
            member.addBook(book);
            book.setAvailable(false);
        }

        return true;
    }

    @Override
    public boolean retrieveBookFromMember(String isbn, Member member) throws MemberDoesNotHaveTheBookException {
        Book book = this.getBook(isbn);

        if (!member.getRetrievedBooks().contains(book)) {
            throw new MemberDoesNotHaveTheBookException();
        }

        member.removeBook(book);
        book.setAvailable(true);
        return true;
    }
    @Override
    public Book getBook(String isbn) throws BookDoesNotExistsException {
             return bookList.stream()
                .filter(cur -> cur.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(()->new BookDoesNotExistsException());
    }
}
