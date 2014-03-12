package services;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.DataCategoryDao;
import dao.DataDao;
import dao.DataNoteDao;
import models.Category;
import models.Data;
import models.DataNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class DataService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private DataDao dataDao;

    @Inject
    private DataCategoryDao dataCategoryDao;

    @Inject
    private DataNoteDao dataNoteDao;

    public List<DataDto> getDataList(Category category , int page , int count) {
        return Lists.transform(dataDao.list(category , page , count) , new DataTransformer());
    }

    private class DataTransformer implements Function<Data , DataDto> {
        @Override
        public DataDto apply(Data data) {
            List<DataNote> dataNotes = dataNoteDao.getDataNote(data);
            return new DataDto(data , dataNotes);
        }
    }

}
