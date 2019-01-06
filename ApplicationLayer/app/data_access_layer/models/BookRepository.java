package data_access_layer.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import util.DateTime;

import java.util.List;

@Entity("book")
public class BookRepository {

    @Id
    private String isbn = new ObjectId().toString();
    private String title;
    private List<String> authors;
    private String publisher;
    private int noOfPages;
    private String sector;
    private DateTime publicationDate;
    private DateTime borrowedDate;
    private DateTime availableDate;
    private PersonRepository reader;

    public BookRepository(){}

    public BookRepository(String isbn, List<String> authors, String publisher, int noOfPages, String title, String sector, DateTime publicationDate, DateTime borrowedDate, DateTime availableDate, PersonRepository reader) {
        this.isbn = isbn;
        this.authors = authors;
        this.publisher = publisher;
        this.noOfPages = noOfPages;
        this.title = title;
        this.sector = sector;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
        this.availableDate = availableDate;
        this.reader = reader;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public DateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(DateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public DateTime getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(DateTime availableDate) {
        this.availableDate = availableDate;
    }

    public PersonRepository getReader() {
        return reader;
    }

    public void setReader(PersonRepository reader) {
        this.reader = reader;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }
}

