package com.jopss.topics.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.MappedSuperclass;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jopss.topics.exception.ValidatingException;

/**
 * Classe mae de todas os modelos. Auxilia na estrategia de banco de dados,
 * escondendo objetos tecnicos dos modelos, ou acoes simples e repetitivas.
 * 
 * A criacao do EntityManager e das Transacoes estao gerenciados manualmente 
 * (ao inves de delegar ao container), pois os modelos nao sao gerenciados pelo Spring.
 */
@MappedSuperclass
@EntityListeners(value = AuditListener.class)
public abstract class Repository implements Serializable {

    private static final long serialVersionUID = -1340481266616282366L;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateUpdated;

    public abstract Long getId();

    @SuppressWarnings("unchecked")
    public <E> E save() throws ValidatingException {
        try {

            EntityManager em = ConnectionFactory.create();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Repository r = em.merge(this);
            tx.commit();
            em.close();
            return (E) r;

        } catch (javax.validation.ConstraintViolationException ex) {
            throw new ValidatingException(ex);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove() throws ValidatingException {
        try {

            EntityManager em = ConnectionFactory.create();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Repository obj = em.merge(this);
            em.remove(obj);
            tx.commit();
            em.close();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void remove(Repository repo) {
        try {

            EntityManager em = ConnectionFactory.create();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            repo = findById();
            Repository obj = em.merge(repo);
            em.remove(obj);
            tx.commit();
            em.close();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    protected static <T extends Repository> T findById(Class clazz, Long id) {
        Query q = getConnection().createQuery("FROM " + clazz.getSimpleName() + " WHERE id = ?");
        q.setParameter(1, id);

        try {
            return (T) q.getSingleResult();
        } catch (javax.persistence.PersistenceException ex) {
            return null;
        }

    }
    
    @SuppressWarnings("unchecked")
    public <T extends Repository> T findById() {
        Query q = getConnection().createQuery("FROM " + this.getClass().getSimpleName() + " WHERE id = ?");
        q.setParameter(1, this.getId());

        try {
            return (T) q.getSingleResult();
        } catch (javax.persistence.PersistenceException ex) {
            return null;
        }

    }

    @SuppressWarnings("rawtypes")
    protected static List findAll(Class clazz, String... fieldsOrder) {
        StringBuilder str = new StringBuilder();
        str.append("FROM " + clazz.getSimpleName());

        if (fieldsOrder != null && fieldsOrder.length > 0) {
            str.append(" ORDER BY ");

            for (String field : fieldsOrder) {
                str.append(field + ",");
            }

            str = new StringBuilder(str.toString().substring(0, str.toString().length() - 1));
        }

        Query q = getConnection().createQuery(str.toString());

        return q.getResultList();
    }

    @SuppressWarnings("rawtypes")
    protected static List findAllWithPaginator(Class clazz, Paginator paginator, String... fieldsOrder) {

        Long pageCount = (Long) getConnection().createQuery("SELECT count(id) FROM " + clazz.getSimpleName()).getSingleResult();
        paginator.setCountResults(pageCount.intValue());

        StringBuilder str = new StringBuilder();
        str.append("FROM " + clazz.getSimpleName());

        if (fieldsOrder != null && fieldsOrder.length > 0) {
            str.append(" ORDER BY ");

            for (String field : fieldsOrder) {
                str.append(field + ",");
            }

            str = new StringBuilder(str.toString().substring(0, str.toString().length() - 1));
        }
        
        Query q = getConnection().createQuery(str.toString());
        q.setFirstResult(paginator.getFirstResult());
        q.setMaxResults(paginator.getMaxResult());

        return q.getResultList();
    }

    protected static EntityManager getConnection() {
        try {
            return ConnectionFactory.create();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }
}
