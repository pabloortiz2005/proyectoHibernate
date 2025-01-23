package org.example.DAO;

import static org.junit.jupiter.api.Assertions.*;
import org.example.entities.Animal;
import org.example.entities.Estado;
import org.example.entities.Animales;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class AnimalDAOimplTest {

    private static SessionFactory sessionFactory;
    private static Session session;


    @BeforeAll //Creo la session porque si no me falla
    public static void setUp() {

        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @AfterAll //Cierro la sesion
    public static void tearDown() {

        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    /**
     * @return todos los animales
     */
    @Test
    public void testFindAll() {
        AnimalDAOimpl animalDAO = new AnimalDAOimpl();
        List<Animal> animals = animalDAO.findAll();


        assertNotNull(animals);
        assertTrue(animals.isEmpty(), "La lista debería estar vacía al principio");
    }

    /**
     * @return Segun id
     */
    @Test
    public void testFindById() {
        Session session = HibernateUtil.getSessionFactory().openSession();


        session.beginTransaction();

        try {

            Animal anim1 = new Animal();
            anim1.setNombre("Raul");
            anim1.setEspecie(Animales.Perro);
            anim1.setEstado(Estado.RecienAb);


            anim1.setEdad(5);


            session.save(anim1);


            session.getTransaction().commit();


            Integer id = anim1.getId();


            Animal encontrado = session.get(Animal.class, id);


            assertNotNull(encontrado);
            assertEquals("Raul", encontrado.getNombre());
        } catch (Exception e) {

            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {

            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    /**
     * @return Segun Especie
     */
    @Test
    public void testFindByEspecie() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Animal anim1 = new Animal();
            anim1.setNombre("Perro Sanchez");
            anim1.setEspecie(Animales.Camaleon);
            anim1.setEstado(Estado.RecienAb);
            anim1.setEdad(5);
            session.save(anim1);


            Animal anim2 = new Animal();
            anim2.setNombre("Thor");
            anim2.setEspecie(Animales.Gato);
            anim2.setEstado(Estado.RecienAb);
            anim2.setEdad(3);
            session.save(anim2);


            session.getTransaction().commit();


            List<Animal> Camaleon = session.createQuery("FROM Animal WHERE especie = :especie", Animal.class)
                    .setParameter("especie", Animales.Camaleon)
                    .getResultList();


            assertNotNull(Camaleon);
            assertEquals(1, Camaleon.size());
            assertEquals("Perro Sanchez", Camaleon.get(0).getNombre());
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    /**
     * @return Cambiar Estado
     */
    @Test
    public void testChangeEstado() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Animal anim3 = new Animal();
            anim3.setNombre("Olga");
            anim3.setEspecie(Animales.Perro);
            anim3.setEstado(Estado.RecienAb);
            anim3.setEdad(4);
            session.save(anim3);


            anim3.setEstado(Estado.TiempoRef);
            session.update(anim3);


            session.getTransaction().commit();


            Animal changedAnimal = session.get(Animal.class, anim3.getId());
            assertNotNull(changedAnimal);
            assertEquals(Estado.TiempoRef, changedAnimal.getEstado());
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    /**
     * @return Crear Animal
     */
    @Test
    public void testCreateAnimal() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Animal anim5 = new Animal();
            anim5.setNombre("CR7");
            anim5.setEspecie(Animales.Gato);
            anim5.setEstado(Estado.ProxAcog);
            anim5.setEdad(6);


            session.save(anim5);


            session.getTransaction().commit();


            Animal createdAnimal = session.get(Animal.class, anim5.getId());
            assertNotNull(createdAnimal);
            assertEquals("CR7", createdAnimal.getNombre());
            assertEquals(Animales.Gato, createdAnimal.getEspecie());
            assertEquals(Estado.ProxAcog, createdAnimal.getEstado());
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * @return Borrar animal por id
     */
    @Test
    public void testDeleteById() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        try {

            Animal anim4 = new Animal();
            anim4.setNombre("Messi");
            anim4.setEspecie(Animales.Perro);
            anim4.setEstado(Estado.RecienAb);
            anim4.setEdad(2);
            session.save(anim4);


            session.getTransaction().commit();


            Animal Anim = session.get(Animal.class, anim4.getId());
            assertNotNull(Anim);


            session.beginTransaction();
            session.delete(Anim);
            session.getTransaction().commit();


            Animal borrao = session.get(Animal.class, anim4.getId());
            assertNull(borrao);
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
