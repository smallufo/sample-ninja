package controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dao.CategoryDao;
import dao.DataDao;
import models.Category;
import ninja.Result;
import ninja.Results;
import ninja.jpa.UnitOfWork;
import ninja.params.Param;
import services.DataDto;
import services.DataService;

import java.util.List;

@Singleton
public class App {

    @Inject
    private CategoryDao categoryDao;

    @Inject
    private DataDao dataDao;

    @Inject
    private DataService dataService;

    @UnitOfWork
    public Result category(@Param("id") Long id, @Param("page") Integer page, @Param("count") Integer count) {
        if (id == null)
            id = 1L;
        if (page == null)
            page = 1;
        if (count == null)
            count = 10;

        Category category = categoryDao.get(id);

        List<DataDto> dataList = dataService.getDataList(category, page, count);

        return Results.html()
            .render("category", category)
            .render("dataList", dataList);
    }
}
