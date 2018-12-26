package data_access_layer.models;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import util.DateTime;

import java.util.Date;

@Entity("reservation")
public class ReservationRepository {
    @Id
    public String id;
    public String bookId;
    public String personId;
    public DateTime reservationDate;
}
