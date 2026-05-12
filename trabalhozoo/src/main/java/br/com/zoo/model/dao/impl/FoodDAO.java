package br.com.zoo.model.dao.impl;

import br.com.zoo.model.Animal;
import br.com.zoo.model.Food;
import br.com.zoo.model.dao.GenericsDAO;
import br.com.zoo.model.dto.AnimalFoodDTO;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FoodDAO extends GenericsDAO<Food, Integer> {
    @Override
    public Food post(Food obj) throws Exception {
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
    public Food update(Food obj) throws Exception {
        return null;
    }

    @Override
    public Food delete(Food obj) throws Exception {
        return null;
    }

    @Override
    public Food deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public Food getById(Integer key) {
        return null;
    }

    @Override
    public List<Food> getAll() {
        return List.of();
    }

    public List<Food> getFoodPerDate(LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);


        String jpql = "SELECT f FROM Food f WHERE f.dateTime BETWEEN :start AND :end";
        TypedQuery<Food> q = connection.createQuery(jpql, Food.class);
        q.setParameter("start", startOfDay);
        q.setParameter("end", endOfDay);

        return q.getResultList();
    }
}
