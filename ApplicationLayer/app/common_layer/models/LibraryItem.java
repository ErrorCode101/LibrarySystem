package common_layer.models;

import util.DateTime;

public abstract class LibraryItem{

    private String isbn;
    private String title;
    private String sector;
    private DateTime  publicationDate;
    private DateTime borrowedDate;
    private DateTime availableDate;
    private Person reader;

    public LibraryItem(String ISBN, String title, DateTime publicationDate){
        this.isbn = ISBN;
        this.title = title;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public LibraryItem(){}

    public DateTime getAvailableDate() {
        return availableDate;
    }

    public void setAvailableDate(DateTime availableDate) {
        this.availableDate = availableDate;
    }

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
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

    public DateTime getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(DateTime borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

}