package br.com.zoo.model.dao.impl;

import br.com.zoo.model.Animal;
import br.com.zoo.model.User;
import br.com.zoo.model.Worker;
import br.com.zoo.model.dao.GenericsDAO;
import br.com.zoo.util.CriptoUtil;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class WorkerDAO extends GenericsDAO<Worker, Integer> {
    public Worker getByLoginPassword(String login, String password){
        String jpql = "select u from Worker u " +
                "where u.login = :log and u.password = :pass";
        Query q = connection.createQuery(jpql);
        q.setParameter("log",login);
        q.setParameter("pass", CriptoUtil.getHash(password));
        try {
            return (Worker) q.getSingleResult();
        }catch (NoResultException e){
            return null;
        }catch (NonUniqueResultException e){
            return null;
        }
    }

    @Override
    public Worker post(Worker obj) throws Exception {
        connection.getTransaction().begin();
        connection.persist(obj);
        connection.getTransaction().commit();
        return obj;
    }

    @Override
    public Worker update(Worker obj) throws Exception {
        try {
            connection.getTransaction().begin();
            connection.merge(obj);
            connection.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            connection.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Worker delete(Worker obj) throws Exception {
        try {
            connection.getTransaction().begin();
            connection.createQuery("DELETE FROM Food f WHERE f.worker.id = :workerId")
                    .setParameter("workerId", obj.getId())
                    .executeUpdate();
            connection.createQuery("DELETE FROM Report f WHERE f.worker.id = :workerId")
                    .setParameter("workerId", obj.getId())
                    .executeUpdate();

            Worker a = getById(obj.getId());
            if (a != null) {
                connection.remove(a);
            }

            connection.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            connection.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Worker deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public Worker getById(Integer key) {
        return connection.find(Worker.class, key);
    }

    @Override
    public List<Worker> getAll() {
        String jpql = "SELECT a FROM Worker a";
        TypedQuery<Worker> q = connection.createQuery(jpql, Worker.class);
        return q.getResultList();
    }
}
