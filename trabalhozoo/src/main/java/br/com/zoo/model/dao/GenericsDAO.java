package br.com.zoo.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public abstract class GenericsDAO<C, K>{
    protected EntityManager connection;

    public GenericsDAO() {
        open();
    }

    public void open(){
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("persist_zoo");
        connection = emf.createEntityManager();
    }
    public void close(){connection.close();}

    public abstract C post(C obj) throws Exception;
    public abstract C update(C obj) throws Exception;
    public abstract C delete(C obj) throws Exception;
    public abstract C deleteByKey(K key) throws Exception;
    public abstract C getById(K key);
    public abstract List<C> getAll();

}
