package br.com.zoo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Worker extends User{

    @OneToMany(mappedBy = "worker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Report> reports;

    public Worker(Integer id, String name, String login, String password, ETypeUser type) {
        super(id, name, login, password, type);
    }

    public Worker() {
    }

    public List<Report> getReports() {
        return reports;
    }
}
