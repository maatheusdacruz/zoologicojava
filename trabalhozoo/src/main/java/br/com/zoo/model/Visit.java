package br.com.zoo.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "visitor_id")
    private Visitor visitor;

    public Visit(int id, LocalDate date, Visitor visitor) {
        this.id = id;
        this.date = date;
        this.visitor = visitor;
    }

    public Visit() {

    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }
}
