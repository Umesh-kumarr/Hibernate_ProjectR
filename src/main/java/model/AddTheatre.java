package model;


import javax.persistence.*;

@Entity
@Table(name = "AddTheatre")
public class AddTheatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, unique = true)
    private String theatretype;

    @Column(nullable = false)
    private String address;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String gettheatretype() {
        return theatretype;
    }

    public void settheatretype(String theatretype) {
        this.theatretype = theatretype;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
