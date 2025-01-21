package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Animales;
import org.example.entities.Estado;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import java.util.List;
import java.util.List;

public class AnimalDAOimpl implements IAnimal{
    /**
     * @return todos los animales
     */
    @Override
    public List<Animal> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Animal> animal = session.createQuery("from Animal", Animal.class).list();

        session.close();

        return animal;
    }

    /**
     * @param id
     * @return Segun id
     */
    @Override
    public Animal findById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Animal animal2 = session.find(Animal.class, id);

        session.close();

        return animal2;
    }

    /**
     * @param nombre
     * @return Segun Nombre
     */
    @Override
    public Animal findByNombre(String nombre) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Animal animal3 = session.find(Animal.class, nombre);

        session.close();

        return animal3;
    }

    /**
     * @param Especie
     * @return Segun Especie
     */
    @Override
    public List<Animal> findByEspecie(Animales Especie) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Animal> animal4 = session.createQuery("from Animal where especie = especie", Animal.class).list();

        session.close();

        return animal4;
    }

    /**
     * @param estado
     * @return Segun Estado
     */
    @Override
    public List<Animal> findByEstado(Estado estado) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Animal> animal5 = session.createQuery("from Animal where estado = estado", Animal.class).list();

        session.close();

        return animal5;
    }

    /**
     * @param estado
     * @return Cambiar Estado
     */
    @Override
    public Animal ChangeEstado(Estado estado) {
        return null;
    }

    @Override
    public Animal create(Animal animal) {
        return null;
    }

    @Override
    public Animal update(Animal animal) {
        return null;
    }

    /**
     * @param id
     * @return borra un id concreto
     */
    @Override
    public boolean deleteById(Integer id) {
        return false;
    }
}
