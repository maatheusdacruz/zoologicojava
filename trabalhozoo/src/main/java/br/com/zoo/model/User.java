package br.com.zoo.model;

import javax.persistence.*;

@Entity
@Table(name = "tb_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(length = 50,nullable = false)
    private String name;
    @Column(length = 20,nullable = false, unique = true)
    private String login;
    @Column(length = 60,nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private ETypeUser type;

    public User(Integer id, String name, String login, String password, ETypeUser type) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type.name();
    }

    public void setType(String name) {
        this.type = ETypeUser.valueOf(name);
    }
}
