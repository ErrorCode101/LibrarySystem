package common_layer.mapper;

import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import data_access_layer.models.BookRepository;
import data_access_layer.models.DVDRepository;
import data_access_layer.models.PersonRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ModelMap {

    private ModelMapper modelMapper = new ModelMapper();

    public BookRepository toBookRepo(Book book){
        return modelMapper.map(book, BookRepository.class);
    }

    public List<Book> toBookList(List<BookRepository> bookRepoList){
        List<Book> books = new ArrayList<>();

        for(BookRepository book:bookRepoList){
            books.add(this.toBook(book));
        }

        return books;
    }

    public Book toBook(BookRepository bookRepo){
        return modelMapper.map(bookRepo, Book.class);
    }

    public Person toPerson(PersonRepository personRepo){
        return modelMapper.map(personRepo, Person.class);
    }

    public DVDRepository toDVDRepo(DVD dvd){
        return modelMapper.map(dvd, DVDRepository.class);
    }

    public PersonRepository toPersonRepo(Person reader){ return  modelMapper.map(reader, PersonRepository.class);}

    public DVD toDVD(DVDRepository dvdRepo){
        return modelMapper.map(dvdRepo, DVD.class);
    }

    public List<DVD> toDVDList(List<DVDRepository> dvdRepoList){
        List<DVD> dvds = new ArrayList<DVD>();

        for(DVDRepository dvd:dvdRepoList){
            dvds.add(modelMapper.map(dvd,DVD.class));
        }

        return dvds;
    }

    public List<Person> toPersonList(List<PersonRepository> dvdRepoList){
        List<Person> dvds = new ArrayList<>();
        for(PersonRepository dvd:dvdRepoList){
            dvds.add(modelMapper.map(dvd,Person.class));
        }
        return dvds;
    }

}
