package common_layer.models.library.items;
import common_layer.models.LibraryItem;
import common_layer.models.Person;
import common_layer.models.persons.Author;
import common_layer.models.persons.Publisher;
import util.DateTime;

import java.util.List;

public class Book extends LibraryItem {

    private List<String> authors;
    private String publisher;
    private int noOfPages;

    Book(){}

    Book(String ISBN, String title, DateTime publicationDate){
        super(ISBN, title, publicationDate);
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