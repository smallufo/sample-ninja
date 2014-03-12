package dao;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;


public abstract class AbstractDao<T> {

  private Logger logger = LoggerFactory.getLogger(getClass());

  protected Provider<EntityManager> emp;

  protected Class<T> entityClass;

  public AbstractDao(Class<T> clazz) {
    this.entityClass = clazz;
  }

  @Inject
  public void setEntityManager(Provider<EntityManager> emp) {
    this.emp = emp;
  }

  public void flush() {
    emp.get().flush();
  }

  @Transactional
  public T save(T entity) {
    emp.get().persist(entity);
    return entity;
  }

  public T get(Serializable id) {
    if (id == null)
      throw new PersistenceException("id may not be null");
    T obj = emp.get().find(entityClass, id);
    logger.trace("get id={} of object class {}", id, obj.getClass().getSimpleName());
    return obj;
  }

  public List<T> list(long page, long count) {
    EntityManager em = emp.get();
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> cQuery = builder.createQuery(entityClass);
    Root<T> from = cQuery.from(entityClass);
    CriteriaQuery select = cQuery.select(from);

    TypedQuery typedQuery = em.createQuery(select);
    typedQuery.setFirstResult((int) ((page - 1) * count));
    typedQuery.setMaxResults((int) count);

    return typedQuery.getResultList();
  }



  public Long count() {
    EntityManager em = emp.get();
    CriteriaBuilder qb = em.getCriteriaBuilder();
    CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    cq.select(qb.count(cq.from(entityClass)));
    return em.createQuery(cq).getSingleResult();
  }

  @Transactional
  public void update(T entity) {
    emp.get().merge(entity);
  }

  @Transactional
  public void delete(T entity) {
    emp.get().remove(entity);
  }



}
