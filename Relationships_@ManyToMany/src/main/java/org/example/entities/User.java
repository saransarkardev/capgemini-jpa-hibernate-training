package org.example.entities;

import jakarta.persistence.*;

import java.util.List;

//One User can belong to multiple Groups.

@Entity
@Table(name = "users_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @ManyToMany(mappedBy = "users")
    private List<Group> groups;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
