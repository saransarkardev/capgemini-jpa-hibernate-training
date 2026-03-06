package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.example.entities.Book;
import org.example.entities.ElectronicDevice;
import org.example.entities.Product;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        System.out.println("Starting JPA...");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("taskPU");


        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();


//        Book b1 = new Book();
//        b1.setId(1);
//        b1.setAuthor("Saran");
//
//        ElectronicDevice e1 = new ElectronicDevice();
//        e1.setId(2);
//        e1.setVoltage(220);
//
//        em.persist(b1);
//        em.persist(e1);


    // @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
        var sql = "SELECT p FROM Product p";
        em.createQuery(sql, Product.class)
                        .getResultList().forEach(System.out::println);





        tx.commit();


        emf.close();
        System.out.println("Closed successfully!");
    }
}