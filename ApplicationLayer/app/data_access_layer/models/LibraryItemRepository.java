package data_access_layer.models;

import util.DateTime;


public class LibraryItemRepository {

    private String title;
    private String sector;
    private DateTime publicationDate;
    private DateTime borrowedDate;
    private DateTime availableDate;
    private PersonRepository reader;

    public LibraryItemRepository(){}

    public LibraryItemRepository(String title, String sector, DateTime publicationDate, DateTime borrowedDate, PersonRepository reader) {
        this.title = title;
        this.sector = sector;
        this.publicationDate = publicationDate;
        this.borrowedDate = borrowedDate;
        this.reader = reader;
    }

    public PersonRepository getPerson() {
        return reader;
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

    public void setPerson(PersonRepository reader) {
        this.reader = reader;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
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
