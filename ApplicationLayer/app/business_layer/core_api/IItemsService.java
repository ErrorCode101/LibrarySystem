package business_layer.core_api;


import common_layer.models.Person;
import common_layer.models.library.items.Book;
import common_layer.models.library.items.DVD;
import data_access_layer.models.BookRepository;
import data_access_layer.models.DVDRepository;
import data_access_layer.models.PersonRepository;
import data_access_layer.models.ReservationRepository;
import util.DateTime;

import java.text.ParseException;
import java.util.List;

public interface IItemsService {

    List<Book> getBooks();

    boolean addBook(BookRepository book);

    boolean addDVD(DVDRepository dvd);

    List<DVD> getDVDs();

    PersonRepository addReader(PersonRepository reader);

    void deleteBook(String id);

    void deleteDVD(String id);

    void borrowBook(String bookid, String personId, DateTime borrowedDate) throws ParseException;

    void returnBook(String bookid);

    void borrowDVD(String dvdid, String personId, DateTime borrowedDate) throws ParseException;

    void returnDVD(String dvdid);

    DateTime availableDate(String bookid);

    void makeReservation(ReservationRepository reserve) throws ParseException;

    List<Person> getPersons();
}
