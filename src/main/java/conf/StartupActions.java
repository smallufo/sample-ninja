package conf;

import com.google.inject.Inject;
import dao.*;
import models.Category;
import models.Data;
import models.DataCategory;
import models.DataNote;
import ninja.lifecycle.Start;
import ninja.utils.NinjaProperties;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class StartupActions {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    CategoryDao categoryDao;

    @Inject
    DataDao dataDao;

    @Inject
    DataCategoryDao dataCategoryDao;

    @Inject
    DataNoteDao dataNoteDao;

    private NinjaProperties ninjaProperties;

    @Inject
    public StartupActions(NinjaProperties ninjaProperties) {
        this.ninjaProperties = ninjaProperties;
    }

    @Start(order = 100)
    public void generateDummyDataWhenInTest() {

        if (!ninjaProperties.isProd()) {
            fillData();
        }

    }

    private void fillData() {

        if (categoryDao.count() == 0) {
            // add 10 categories
            for (int i = 1; i <= 10; i++) {
                String catName = i + "_" + RandomStringUtils.randomAlphabetic(4);
                Category category = new Category(catName);
                categoryDao.save(category);

                // each category contains 50 data.
                for (int j = 1; j <= 50; j++) {
                    String dataName = i + "_" + j + "_" + RandomStringUtils.randomAlphabetic(4);
                    Data data = new Data(dataName);
                    dataDao.save(data);

                    dataCategoryDao.save(new DataCategory(data , category));

                    // each data contains 3 DataNote(s)
                    for(int k = 0 ; k < 3 ; k++) {
                        dataNoteDao.save(new DataNote(data, RandomStringUtils.randomAlphabetic(100)));
                    }

                }

                logger.info("[{}/10] saving category : {}", i , category.getName());
            }


        }
    }


}
