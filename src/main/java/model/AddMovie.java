package model;


import javax.persistence.*;

@Entity
@Table(name = "AddMovie")
public class AddMovie {
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @JoinColumn(name = "theatrenameid", nullable = false)
    private AddTheatre theatrename;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private int screenno;

    @Column(nullable = false)
    private int price;


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

    public AddTheatre getTheatrename() {
        return theatrename;
    }

    public void setTheatrename(AddTheatre theatrename) {
        this.theatrename = theatrename;
    }

    public int getScreenno() {
        return screenno;
    }

    public void setScreenno(int screenno) {
        this.screenno = screenno;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


}
