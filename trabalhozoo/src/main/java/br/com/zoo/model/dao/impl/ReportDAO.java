package br.com.zoo.model.dao.impl;

import br.com.zoo.model.Report;
import br.com.zoo.model.dao.GenericsDAO;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ReportDAO extends GenericsDAO<Report, Integer> {
    @Override
    public Report post(Report obj) throws Exception {
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
    public Report update(Report obj) throws Exception {
        return null;
    }

    @Override
    public Report delete(Report obj) throws Exception {
        return null;
    }

    @Override
    public Report deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public Report getById(Integer key) {
        return connection.find(Report.class, key);
    }

    @Override
    public List<Report> getAll() {
        return List.of();
    }

    public List<Report> getReportsperDate(LocalDate date){
        String jpql = "SELECT r FROM Report r WHERE r.date = :date";

        TypedQuery<Report> q = connection.createQuery(jpql, Report.class);
        q.setParameter("date", date);
        return q.getResultList();
    }

    public List<Report> getReportsperWorker(int workerId) {
        String jpql ="SELECT r FROM Report r WHERE r.worker.id = :workerId";
        TypedQuery<Report> q = connection.createQuery(jpql, Report.class);
        q.setParameter("workerId", workerId);
        return q.getResultList();

    }
}
