package br.com.zoo.model.dao.impl;

import br.com.zoo.model.ETypeUser;
import br.com.zoo.model.User;
import br.com.zoo.model.dao.GenericsDAO;
import br.com.zoo.util.CriptoUtil;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import java.util.List;

public class UserDAO extends GenericsDAO<User, Integer> {
    public User getByLoginPassword(String login, String password){
        String jpql = "select u from User u " +
                "where u.login = :log and u.password = :pass";
        Query q = connection.createQuery(jpql);
        q.setParameter("log",login);
        q.setParameter("pass", CriptoUtil.getHash(password));
        try {
            return (User) q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NonUniqueResultException e){
            return null;
        }
    }

    @Override
    public User post(User obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public User update(User obj) throws Exception {
        return null;
    }

    @Override
    public User delete(User obj) throws Exception {
        return null;
    }

    @Override
    public User deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public User getById(Integer key) {
        return null;
    }

    @Override
    public List getAll() {
        return null;
    }
}
