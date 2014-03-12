package services;

import models.Data;
import models.DataNote;

import java.io.Serializable;
import java.util.List;

public class DataDto implements Serializable {

    private final Data data;

    private final List<DataNote> dataNotes;

    public DataDto(Data data , List<DataNote> dataNotes) {
        this.data = data;
        this.dataNotes = dataNotes;
    }

    public Data getData() {
        return data;
    }

    public List<DataNote> getDataNote() {
        return dataNotes;
    }
}
