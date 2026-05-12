package br.com.zoo.model.dao.impl;

import br.com.zoo.model.Report;
import br.com.zoo.model.Visit;
import br.com.zoo.model.dao.GenericsDAO;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class VisitDAO extends GenericsDAO<Visit, Integer> {
    @Override
    public Visit post(Visit obj) throws Exception {
        try {
            connection.getTransaction().begin();
            connection.persist(obj);
            connection.getTransaction().commit();
            return obj;
        } catch (Exception e) {
            connection.getTransaction().rollback();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Visit update(Visit obj) throws Exception {
        return null;
    }

    @Override
    public Visit delete(Visit obj) throws Exception {
        return null;
    }

    @Override
    public Visit deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public Visit getById(Integer key) {
        return connection.find(Visit.class, key);
    }

    @Override
    public List<Visit> getAll() {
        String jpql = "SELECT av FROM Visit av";

        TypedQuery<Visit> q = connection.createQuery(jpql, Visit.class);
        return q.getResultList();
    }

    public List<Visit> getVisitsperDate(LocalDate date){
        String jpql = "SELECT r FROM Visit r WHERE r.date = :date";

        TypedQuery<Visit> q = connection.createQuery(jpql, Visit.class);
        q.setParameter("date", date);
        return q.getResultList();
    }

    public List<Visit> getVisitsPerVisitor(int visitorId) {
        String jpql ="SELECT r FROM Visit r WHERE r.visitor.id = :visitorId";
        TypedQuery<Visit> q = connection.createQuery(jpql, Visit.class);
        q.setParameter("visitorId", visitorId);
        return q.getResultList();

    }
}
