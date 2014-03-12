package dao;

import models.Data;
import models.DataNote;

import java.util.List;

public class DataNoteDao extends AbstractDao<DataNote> {

    public DataNoteDao() {
        super(DataNote.class);
    }

    public List<DataNote> getDataNote(Data data) {
        return emp.get().createQuery("from DataNote where data = :data")
            .setParameter("data" , data).getResultList();
    }
}
