package dev.patika.definexjavaspringbootbootcamp2025.service;

import dev.patika.definexjavaspringbootbootcamp2025.entity.Book;
import dev.patika.definexjavaspringbootbootcamp2025.entity.Member;
import dev.patika.definexjavaspringbootbootcamp2025.exception.BookDoesNotExistsException;
import dev.patika.definexjavaspringbootbootcamp2025.exception.BookIsNotAvailableException;
import dev.patika.definexjavaspringbootbootcamp2025.exception.ISBNAlreadyExistException;
import dev.patika.definexjavaspringbootbootcamp2025.exception.MemberDoesNotHaveTheBookException;

public abstract class Library {

    public abstract boolean addBook(Book book) throws ISBNAlreadyExistException;
    public abstract boolean updateBook(Book book) throws BookDoesNotExistsException;
    public abstract boolean removeBook(String isbn) throws BookDoesNotExistsException;
    public abstract boolean lendBookToMember(String isbn, Member member) throws BookIsNotAvailableException;
    public abstract boolean retrieveBookFromMember(String isbn, Member member) throws MemberDoesNotHaveTheBookException;
    public abstract Book getBook(String isbn) throws BookDoesNotExistsException;

    public abstract void clearBookList();
}
