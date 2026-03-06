package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.Group;
import org.example.entities.User;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taskPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        User u1 = new User();
        u1.setName("User 1");
        User u2 = new User();
        u2.setName("User 2");

        Group g1 = new Group();
        g1.setName("Group 1");
        Group g2 = new Group();
        g2.setName("Group 2");

        g1.setUsers(Arrays.asList(u1, u2));
        g2.setUsers(Arrays.asList(u2));

        u1.setGroups(Arrays.asList(g1));
        u2.setGroups(Arrays.asList(g1, g2));

//
//        em.persist(u1);
//        em.persist(u2);
        em.persist(g1);
        em.persist(g2);


        tx.commit();

        em.close();
        emf.close();
    }
}