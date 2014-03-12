package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DataCategory implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Data data;

    @ManyToOne
    private Category category;

    protected DataCategory() {
    }

    public DataCategory(Data data, Category category) {
        this.data = data;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public Category getCategory() {
        return category;
    }

}
