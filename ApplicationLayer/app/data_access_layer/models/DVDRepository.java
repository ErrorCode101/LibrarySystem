package data_access_layer.models;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import util.DateTime;

import java.util.List;

@Entity("dvd")
public class DVDRepository {

    @Id
    private String isbn;
    private String title;
    private String producer;
    private List<String> actors;
    private List<String> languages;
    private List<String> subtitles;
    private String sector;
    private DateTime publicationDate;
    private DateTime borrowedDate;
    private DateTime availableDate;
    private PersonRepository reader;

    public DVDRepository(){}

    public DVDRepository(String isbn, String producer, List<String> actors, List<String> languages, List<String> subtitles, String title, String sector, DateTime publicationDate, DateTime borrowedDate, DateTime availableDate, PersonRepository reader) {
        this.isbn = isbn;
        this.producer = producer;
        this.actors = actors;
        this.languages = languages;
        this.subtitles = subtitles;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<String> subtitles) {
        this.subtitles = subtitles;
    }
}
