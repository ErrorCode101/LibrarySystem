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
import java.util.List;

public class ItemService implements IItemsService {

    private ModelMap modelMapper = new ModelMap();

    public List<Book> getBooks(){
        final Query<BookRepository> query = MongoConfig.datastore().createQuery(BookRepository.class);
        return modelMapper.toBookList(query.asList());
    }


    public void addBook(Book book){
        BookRepository bookRepo = modelMapper.toBookRepo(book);
        MongoConfig.datastore().save(bookRepo);
    }

    public void addDVD(DVD dvd){
        DVDRepository dvdRepo = modelMapper.toDVDRepo(dvd);
        MongoConfig.datastore().save(dvdRepo);
    }

    public List<DVD> getDVDs(){
        final Query<DVDRepository> query = MongoConfig.datastore().createQuery(DVDRepository.class);
        return modelMapper.toDVDList(query.asList());
    }

    public PersonRepository addReader(Person reader){
        PersonRepository readerRepo = modelMapper.toPersonRepo(reader);
        MongoConfig.datastore().save(readerRepo);
        return readerRepo;
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

    public void borrowBook(String bookid, String personId, DateTime borrowedDate){
        final Query<PersonRepository> personQuery = MongoConfig.datastore().createQuery(PersonRepository.class).field("id").equal(personId);
        PersonRepository person = personQuery.asList().get(0);
        final Query<BookRepository> bookQuery = MongoConfig.datastore().createQuery(BookRepository.class).field("id").equal(bookid);
        final UpdateOperations<BookRepository> updateBook = MongoConfig.datastore().createUpdateOperations(BookRepository.class).
                set("borrowedDate",borrowedDate).set("reader", person);
        final UpdateResults result = MongoConfig.datastore().update(bookQuery,updateBook);
    }

    public void returnBook(String bookid){
        final Query<BookRepository> bookQuery = MongoConfig.datastore().createQuery(BookRepository.class).field("id").equal(bookid);
        final UpdateOperations<BookRepository> updateBook = MongoConfig.datastore().createUpdateOperations(BookRepository.class).
                unset("borrowedDate").unset("reader");
        final UpdateResults result = MongoConfig.datastore().update(bookQuery,updateBook);
    }

    public void borrowDVD(String dvdid, String personId, DateTime borrowedDate){
        final Query<PersonRepository> personQuery = MongoConfig.datastore().createQuery(PersonRepository.class).field("id").equal(personId);
        PersonRepository person = personQuery.asList().get(0);
        final Query<DVDRepository> dvdQuery = MongoConfig.datastore().createQuery(DVDRepository.class).field("id").equal(dvdid);
        final UpdateOperations<DVDRepository> updateDVD = MongoConfig.datastore().createUpdateOperations(DVDRepository.class).
                set("boorwedDate",borrowedDate).set("reader", person);
        final UpdateResults result = MongoConfig.datastore().update(dvdQuery,updateDVD);
    }

    public void returnDVD(String dvdid){
        final Query<DVDRepository> dvdQuery = MongoConfig.datastore().createQuery(DVDRepository.class).field("id").equal(dvdid);
        final UpdateOperations<DVDRepository> updateDVD = MongoConfig.datastore().createUpdateOperations(DVDRepository.class).
                unset("boorwedDate").unset("reader");
        final UpdateResults result = MongoConfig.datastore().update(dvdQuery,updateDVD);
    }

    public DateTime availableDate(String bookid){
        final Query<BookRepository> query = MongoConfig.datastore().createQuery(BookRepository.class).field("id").equal(bookid);
        BookRepository book = query.asList().get(0);
        return book.getBorrowedDate();
    }

    public void makeReservation(ReservationRepository reserve) throws ParseException {
        final Query<BookRepository> bookQuery = MongoConfig.datastore().createQuery(BookRepository.class).field("id").equal(reserve.bookId);
        final UpdateOperations<BookRepository> updateBook = MongoConfig.datastore().createUpdateOperations(BookRepository.class).
                set("availableDate",DateTime.addDays(reserve.reservationDate.getDate(),7));
        final UpdateResults result = MongoConfig.datastore().update(bookQuery,updateBook);
        MongoConfig.datastore().save(reserve);
    }
}