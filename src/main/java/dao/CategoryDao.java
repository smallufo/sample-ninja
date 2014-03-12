package dao;

import models.Category;

public class CategoryDao extends AbstractDao<Category> {
  public CategoryDao() {
    super(Category.class);
  }
}
