package org.example;

import jakarta.persistence.*;
import org.example.entities.Product;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Starting JPA...");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("unit");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // SELECT, UPDATE, DELETE
            /*
            String jpql = "SELECT p FROM Product p";
            String jpql = "SELECT p FROM Product  p WHERE p.price > 5";
            String jpql = "SELECT p FROM Product p WHERE p.price > :price AND p.name LIKE :name";

            SELECT p FROM Product p --> Fetch all the attributes of the Product entity from the current context.
            SELECT * FROM Product --> Fetch all the columns from the table product

            TypedQuery<Product> q = em.createQuery(jpql, Product.class);
            q.setParameter("price", 3);
            q.setParameter("name", "%a%");

            List<Product> productList = q.getResultList();

            for (Product p : productList) {
                System.out.println(p);
            }
            */

            /*
            String jpql = "SELECT AVG(p.price) FROM Product p";
            TypedQuery<Double> q = em.createQuery(jpql, Double.class);

            Double avg = q.getSingleResult();

            System.out.println("Average: "+ avg);
             */

            /*
            String jpql = "SELECT COUNT(p) FROM Product p";
            TypedQuery<Long> q = em.createQuery(jpql, Long.class);
            Long noOfRecords = q.getSingleResult();

            System.out.println("No of Records: "+ noOfRecords);
            */

            /*
            String jpql = "SELECT p.name, p.price FROM Product p";
            TypedQuery<Objects[]> q = em.createQuery(jpql, Objects[].class);

            q.getResultList().forEach(objects -> {
                System.out.println(objects[0] + " " + objects[1]);
            });
            */

            /*
            String jpql = """
                         SELECT p.name , AVG(p.price) 
                         FROM Product p GROUP BY p.name
                         """;
            TypedQuery<Objects[]> q = em.createQuery(jpql, Objects[].class);

            q.getResultList().forEach(objects -> {
                System.out.println(objects[0] + " " + objects[1]);
            });
            */

            String jpql = "SELECT p FROM Product p WHERE p.name LIKE 'Candy";

            TypedQuery<Product> q = em.createQuery(jpql, Product.class);

            Optional<Product> p;
            try{
                p = Optional.of(q.getSingleResult());
            } catch (NoResultException e) {
                p = Optional.empty();
            }

            p.ifPresentOrElse(
                    System.out::println,
                    () -> System.out.println("Product Not Found")

            );

            tx.commit();

        } finally {
            em.close();
            emf.close();
        }
    }
}