package br.com.zoo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Visitor extends User{

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Visit> visits;

    public Visitor(Integer id, String name, String login, String password, ETypeUser type) {
        super(id, name, login, password, type);
    }

    public Visitor() {
    }

    public List<Visit> getVisits() {
        return visits;
    }
}
