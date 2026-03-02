package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.example.entities.Product;
import org.example.entities.Student;
import org.example.entities.keys.StudentKey;
import org.example.persistence.CustomPersistenceUnitInfo;
import org.hibernate.jpa.HibernatePersistenceProvider;

import java.util.HashMap;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        String puName = "pu-name";
        Map<String, String> props = new HashMap<>();
        props.put("hibernate.show_sql", "true");
        props.put("hibernate.hbm2ddl.auto", "create");




        EntityManagerFactory emf = new HibernatePersistenceProvider()
                .createContainerEntityManagerFactory(new CustomPersistenceUnitInfo(puName), props);

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

//            Product p1 = new Product();
//            p1.setCode("ABC");
//            p1.setNumber(10);
//            p1.setColor("Red");
//
//            em.persist(p1);

            StudentKey id = new StudentKey();
            id.setCode("ABC");
            id.setNumber(10);

            Student s = new Student();
            s.setId(id);
            s.setName("Saran");

            em.persist(s);

            System.out.println(s);


            em.getTransaction().commit(); // end of transaction
















//            Employee e1 = em.find(Employee.class, 1);
//            e1.setName("Rahul");
//            e1.setName("Saran");
//            e1.setName("King");

            //  Marks the managed entity for deletion(but still stays in persistence context); actual DELETE happens on transaction commit
            // em.remove(e1);

//            Employee e2 = new Employee();
//            e2.setId(3);
//            e2.setName("King");
//            e2.setAddress("Memari");
            // If a row with id = 1 already exists in the database
            // It will throw EntityExistsException or database constraint error (duplicate primary key).
            // Makes this new entity managed (stores in persistence context) and schedules it for insertion into the database
//            em.persist(e2);

//            System.out.println(e1);

//            em.getTransaction().commit(); // end of transaction

        } finally {
            em.close();
        }
    }
}

// ===============================================
// States of an Entity in JPA
// ===============================================

// Transient
// -> New Java object
// -> NOT inside Persistence Context
// -> NOT in database


// Managed (Persistent)
// -> Inside Persistence Context
// -> Hibernate tracks changes (dirty checking)
// -> Synced with database on commit/flush


// Detached
// -> Was managed before, but now removed from Persistence Context
// -> NOT inside Persistence Context
// -> Changes are NOT tracked automatically


// Removed
// -> Marked for deletion
// -> STILL inside Persistence Context
// -> DELETE executed on commit


// ===============================================
// JPA EntityManager Core Methods & Their Behavior
// (Persistence Context + Database Interaction)
// ===============================================

// em.persist(entity)
// -> Makes a new object MANAGED, stores it inside Persistence Context,
//    and schedules INSERT into database (on commit/flush)


// em.find(Entity.class, id)
// -> Fetches entity by primary key.
//    If found, returns a MANAGED entity (inside Persistence Context)


// em.remove(entity)
// -> Marks a MANAGED entity for deletion.
//    Entity remains INSIDE Persistence Context (state = REMOVED)
//    DELETE happens on commit


// em.merge(entity)
// -> Takes a DETACHED entity and copies its data into a new MANAGED entity.
//    Returned object is INSIDE Persistence Context
//    Original object remains OUTSIDE (detached)


// em.detach(entity)
// -> Removes a single entity from Persistence Context.
//    Entity becomes DETACHED (OUTSIDE Persistence Context)


// em.clear()
// -> Removes ALL entities from Persistence Context.
//    All become DETACHED (OUTSIDE Persistence Context)


// em.refresh(entity)
// -> Reloads entity data from database.
//    Entity remains MANAGED (INSIDE Persistence Context)


// em.getReference(Entity.class, id)
// -> Returns a lazy proxy entity.
//    Proxy is MANAGED and INSIDE Persistence Context,
//    but data is loaded only when accessed


// em.flush()
// -> Executes pending SQL (INSERT/UPDATE/DELETE) immediately.
//    Entities remain MANAGED (still INSIDE Persistence Context)


// em.close()
// -> Closes EntityManager and clears Persistence Context.
//    All entities become DETACHED (OUTSIDE Persistence Context)


// ==========================================================
// JPA Transaction Methods & Hibernate Session Core Methods
// (With Persistence Context Behavior)
// ==========================================================



// -------------------- JPA Transaction Methods --------------------


// em.getTransaction().begin()
// -> Starts a database transaction.
//    Persistence Context begins tracking changes within this transaction.


// em.getTransaction().commit()
// -> Commits the transaction.
//    Executes all pending SQL (INSERT/UPDATE/DELETE)
//    Managed entities remain inside Persistence Context
//    Changes are permanently saved to database.


// em.getTransaction().rollback()
// -> Cancels the transaction.
//    Discards uncommitted changes in database.
//    Persistence Context is cleared or becomes inconsistent depending on provider
//    (Usually entities become DETACHED).




// -------------------- Hibernate Session Methods --------------------


// session.save(entity)
// -> Saves a new entity and returns generated ID.
//    Entity becomes MANAGED (INSIDE Session / Persistence Context).
//    INSERT happens on flush/commit.


// session.update(entity)
// -> Reattaches a DETACHED entity to the Session.
//    Entity becomes MANAGED (INSIDE Session).
//    Schedules UPDATE on flush/commit.


// session.saveOrUpdate(entity)
// -> If entity has no ID → INSERT
//    If entity has existing ID → UPDATE
//    Entity becomes MANAGED (INSIDE Session).


// session.evict(entity)
// -> Removes a single entity from Session.
//    Entity becomes DETACHED (OUTSIDE Persistence Context).


// session.clear()
// -> Removes ALL entities from Session.
//    All become DETACHED (OUTSIDE Persistence Context).


// session.get(Entity.class, id)
// -> Immediately fetches entity from database.
//    Returns MANAGED entity (INSIDE Persistence Context).


// session.load(Entity.class, id)
// -> Returns a proxy (lazy object).
//    Proxy is MANAGED (INSIDE Persistence Context).
//    Database hit happens only when accessing properties.