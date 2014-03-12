package models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class DataNote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Data data;

    @Lob
    private String note;

    protected DataNote() {
    }

    public DataNote(Data data, String note) {
        this.data = data;
        this.note = note;
    }

    public long getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public String getNote() {
        return note;
    }
}
