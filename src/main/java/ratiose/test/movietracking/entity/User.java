package ratiose.test.movietracking.entity;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue
    Long id;

    @Column(unique=true)
    String email;

    String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
