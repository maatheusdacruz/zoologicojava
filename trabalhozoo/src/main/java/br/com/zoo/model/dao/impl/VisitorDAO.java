package br.com.zoo.model.dao.impl;

import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.Visitor;
import br.com.zoo.model.Worker;
import br.com.zoo.model.dao.GenericsDAO;
import br.com.zoo.util.CriptoUtil;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class VisitorDAO extends GenericsDAO<Visitor, Integer> {

    public Visitor getByLoginPassword(String login, String password){
        String jpql = "select u from Visitor u " +
                "where u.login = :log and u.password = :pass";
        Query q = connection.createQuery(jpql);
        q.setParameter("log",login);
        q.setParameter("pass", CriptoUtil.getHash(password));
        try {
            return (Visitor) q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NonUniqueResultException e){
            return null;
        }
    }

    @Override
    public Visitor post(Visitor obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Visitor update(Visitor obj) throws Exception {
        return null;
    }

    @Override
    public Visitor delete(Visitor obj) throws Exception {
        return null;
    }

    @Override
    public Visitor deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public Visitor getById(Integer key) {
        return connection.find(Visitor.class, key);
    }

    @Override
    public List<Visitor> getAll() {
        String jpql = "SELECT a FROM Visitor a";
        TypedQuery<Visitor> q = connection.createQuery(jpql, Visitor.class);
        return q.getResultList();
    }
}
