package models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    protected Data() {

    }

    public Data(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
