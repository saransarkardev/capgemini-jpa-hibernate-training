package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.Comment;
import org.example.entities.Post;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa69");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Post p1 = new Post();
        p1.setTitle("JPA");
        p1.setContent("Coding Topic");

        Comment c1 = new Comment();
        c1.setContent("Content Comment 1");

        Comment c2 = new Comment();
        c2.setContent("Content Comment 2");

        p1.setComments(List.of(c1, c2));

        c1.setPost(p1);
        c2.setPost(p1);

//        c1.setPost(p1);

        em.persist(p1);
//        em.persist(c1);



        tx.commit();
    }
}