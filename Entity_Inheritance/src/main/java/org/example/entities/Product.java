package org.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

//Another annotation we can use in place of parent Entity -> @MappedSuperClass (Then we can not make jpql query from Product)

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @Id
    protected  int id;

    protected String name;

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
