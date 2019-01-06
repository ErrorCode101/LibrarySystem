package business_layer.core;

import business_layer.core_api.IItemsService;
import common_layer.mapper.ModelMap;
import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import data_access_layer.connection.MongoConfig;
import data_access_layer.models.BookRepository;
import data_access_layer.models.DVDRepository;
import data_access_layer.models.PersonRepository;
import data_access_layer.models.ReservationRepository;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import util.DateTime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements IItemsService {

    private ModelMap modelMapper = new ModelMap();

    public List<Book> getBooks(){
        final Query<BookRepository> query = MongoConfig.datastore().createQuery(BookRepository.class);
        return modelMapper.toBookList(query.asList());
    }


    public boolean addBook(BookRepository book){
        if(this.getBooks().size() <100) {
            //BookRepository bookRepo = modelMapper.toBookRepo(book);
            MongoConfig.datastore().save(book);
            return true;
        }else{
            return false;
        }
    }

    public boolean addDVD(DVDRepository dvd){
        if(this.getDVDs().size() < 50) {
            //DVDRepository dvdRepo = modelMapper.toDVDRepo(dvd);
            MongoConfig.datastore().save(dvd);
            return  true;
        }else{
            return false;
        }
    }

    public List<DVD> getDVDs(){
        final Query<DVDRepository> query = MongoConfig.datastore().createQuery(DVDRepository.class);
        return modelMapper.toDVDList(query.asList());
    }

    public PersonRepository addReader(PersonRepository reader){
        //PersonRepository readerRepo = modelMapper.toPersonRepo(reader);
        MongoConfig.datastore().save(reader);
        return reader;
    }

    public List<Person> getPersons(){
        final Query<PersonRepository> query = MongoConfig.datastore().createQuery(PersonRepository.class);
        return modelMapper.toPersonList(query.asList());
    }

    public void deleteBook(String id){
        final Query<BookRepository> query = MongoConfig.datastore().createQuery(BookRepository.class).field("isbn").equal(id);
        MongoConfig.datastore().delete(query);
    }

    public void deleteDVD(String id){
        final Query<DVDRepository> query = MongoConfig.datastore().createQuery(DVDRepository.class).field("isbn").equal(id);
        MongoConfig.datastore().delete(query);
    }

    public void borrowBook(String bookid, String personId, DateTime borrowedDate) throws ParseException {
        PersonRepository personQuery = this.findPerson(personId);
        BookRepository bookQuery = this.findBook(bookid);
        bookQuery.setReader(personQuery);
        bookQuery.setBorrowedDate(borrowedDate);
        bookQuery.setAvailableDate(DateTime.addDays(borrowedDate.getDate(),5));
        MongoConfig.datastore().save(bookQuery);
    }

    public void returnBook(String bookid){
        BookRepository bookQuery = this.findBook(bookid);
        bookQuery.setReader(null);
        bookQuery.setBorrowedDate(null);
        bookQuery.setAvailableDate(null);
        MongoConfig.datastore().save(bookQuery);
    }

    public void borrowDVD(String dvdid, String personId, DateTime borrowedDate) throws ParseException {
        PersonRepository personQuery = this.findPerson(personId);
        DVDRepository dvdQuery = this.findDVD(dvdid);
        dvdQuery.setReader(personQuery);
        dvdQuery.setBorrowedDate(borrowedDate);
        dvdQuery.setAvailableDate(DateTime.addDays(borrowedDate.getDate(),5));
        MongoConfig.datastore().save(dvdQuery);
    }

    public void returnDVD(String dvdid){
        DVDRepository dvdQuery = this.findDVD(dvdid);
        dvdQuery.setReader(null);
        dvdQuery.setBorrowedDate(null);
        dvdQuery.setAvailableDate(null);
        MongoConfig.datastore().save(dvdQuery);
    }

    public DateTime availableDate(String bookid){
        final Query<BookRepository> query = MongoConfig.datastore().createQuery(BookRepository.class).field("isbn").equal(bookid);
        BookRepository book = query.asList().get(0);
        return book.getBorrowedDate();
    }

    public void makeReservation(ReservationRepository reserve) throws ParseException {
        final Query<BookRepository> bookQuery = MongoConfig.datastore().createQuery(BookRepository.class).field("isbn").equal(reserve.bookId);
        final UpdateOperations<BookRepository> updateBook = MongoConfig.datastore().createUpdateOperations(BookRepository.class).
                set("availableDate",DateTime.addDays(reserve.reservationDate.getDate(),7));
        final UpdateResults result = MongoConfig.datastore().update(bookQuery,updateBook);
        MongoConfig.datastore().save(reserve);
    }

    private PersonRepository findPerson(String id){
        final Query<PersonRepository> query = MongoConfig.datastore().createQuery(PersonRepository.class);
        for (PersonRepository person: query.asList()) {
            if(person.get_id().equals(id)){
                return person;
            }
        }
        return null;
    }

    private BookRepository findBook(String id){
        final Query<BookRepository> query = MongoConfig.datastore().createQuery(BookRepository.class);
        for (BookRepository book: query.asList()) {
            if(book.getIsbn().equals(id)){
                return book;
            }
        }
        return null;
    }

    private DVDRepository findDVD(String id){
        final Query<DVDRepository> query = MongoConfig.datastore().createQuery(DVDRepository.class);
        for (DVDRepository dvd: query.asList()) {
            if(dvd.getIsbn().equals(id)){
                return dvd;
            }
        }
        return null;
    }
}