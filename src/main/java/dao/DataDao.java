package dao;

import models.Category;
import models.Data;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by smallufo on 2014-03-12.
 */
public class DataDao extends AbstractDao<Data> {

  public DataDao() {
    super(Data.class);
  }

  public List<Data> list(Category category, Integer page, Integer count) {
    EntityManager em = emp.get();
      return em.createQuery("select distinct d from DataCategory dc" +
          " join dc.data as d" +
          " join dc.category as category where category = :category")
          .setParameter("category", category)
          .setFirstResult((int) ((page - 1) * count))
          .setMaxResults(count)
          .getResultList();

  }
}
