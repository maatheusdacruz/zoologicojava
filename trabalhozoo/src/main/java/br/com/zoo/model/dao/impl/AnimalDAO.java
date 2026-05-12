package br.com.zoo.model.dao.impl;

import br.com.zoo.model.Animal;
import br.com.zoo.model.dao.GenericsDAO;
import br.com.zoo.model.dto.AnimalFoodDTO;
import br.com.zoo.model.dto.AnimalViewDTO;

import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO extends GenericsDAO<Animal, Integer> {
    @Override
    public Animal post(Animal obj) throws Exception {
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
    public Animal update(Animal obj) throws Exception {
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
    public Animal delete(Animal obj) {
        try {
            connection.getTransaction().begin();
            connection.createQuery("DELETE FROM Food f WHERE f.animal.id = :animalId")
                    .setParameter("animalId", obj.getId())
                    .executeUpdate();

            Animal a = getById(obj.getId());
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
    public Animal deleteByKey(Integer key) throws Exception {
        return null;
    }

    @Override
    public Animal getById(Integer key) {
        return connection.find(Animal.class, key);
    }

    public List<AnimalViewDTO> getByNameView(String name){
        String jpql = "select new br.com.zoo.model.dto.AnimalViewDTO( " +
                "a.id, a.name, a.scientificName, a.specie, a.arrivalDate, a.place, a.animalhealth) " +
                "from Animal a where a.name like :name";

        TypedQuery<AnimalViewDTO> q = connection.createQuery(jpql, AnimalViewDTO.class);
        q.setParameter("name", "%"+name+"%");
        return q.getResultList();
    }

    @Override
    public List<Animal> getAll() {
        String jpql = "SELECT a FROM Animal a";
        TypedQuery<Animal> q = connection.createQuery(jpql, Animal.class);
        return q.getResultList();
    }


    public List<AnimalViewDTO> getAllView() {
        String jpql = "select new br.com.zoo.model.dto.AnimalViewDTO("+
                "a.id, a.name, a.scientificName, a.specie , a.arrivalDate, a.place, a.animalhealth) " +
                "from Animal a";

        TypedQuery<AnimalViewDTO> q = connection.createQuery(jpql, AnimalViewDTO.class);
        return q.getResultList();
    }

    public List<AnimalFoodDTO> getAnimalFed(LocalDate date) {
        List<Animal> animals = connection.createQuery("SELECT a FROM Animal a", Animal.class).getResultList();
        List<AnimalFoodDTO> result = new ArrayList<>();

        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        for (Animal animal : animals) {
            String jpql = "SELECT COUNT(al) FROM Food al WHERE al.animal.id = :id AND al.dateTime BETWEEN :start AND :end";
            Long count = connection.createQuery(jpql, Long.class)
                    .setParameter("id", animal.getId())
                    .setParameter("start", startOfDay)
                    .setParameter("end", endOfDay)
                    .getSingleResult();
            boolean wasFed = count > 0;
            result.add(new AnimalFoodDTO(animal, wasFed));
        }

        return result;
    }
}
