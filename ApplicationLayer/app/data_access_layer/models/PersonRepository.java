package data_access_layer.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("person")
public class PersonRepository {

    @Id
    private String _id = new ObjectId().toString();;
    private String name;
    private int phoneNo;
    private String email;

    public PersonRepository(){
    }

    public PersonRepository(String name, int phoneNo, String email) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
