package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Animales;
import org.example.entities.Estado;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.sound.midi.SysexMessage;
import java.util.List;
import java.util.Scanner;

import static org.example.entities.Animales.*;
import static org.example.entities.Estado.TiempoRef;
import static org.example.entities.Estado.RecienAb;
import static org.example.entities.Estado.ProxAcog;

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
     * @param especie
     * @return Segun Especie
     */
    @Override
    public List<Animal> findByEspecie(Animales especie) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        List<Animal> animal4 = session.createQuery("from Animal where especie = :especie", Animal.class)
                .setParameter("especie", especie)
                .list();

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
        Session session = HibernateUtil.getSessionFactory().openSession();

        Animal animal6 = session.find(Animal.class, estado);
        System.out.println("El estado de este animal es: " + animal6.getEstado());


        if (animal6.getEstado()==RecienAb || animal6.getEstado()==TiempoRef){
        System.out.println("Inserte los datos de la persona que va a adoptar al animal");
            Scanner scanner = new Scanner(System.in);
            Scanner scanner2 = new Scanner(System.in);

            // Obtener datos de la familia
            System.out.print("Nombre: ");
            animal6.setNombreF(scanner.nextLine());
            System.out.print("Edad: ");
            animal6.setEdadF(scanner2.nextInt());
            System.out.print("Ciudad: ");
            animal6.setCiudadF(scanner.nextLine());
        } if (animal6.getEstado()==ProxAcog) {
            System.out.println("Tristemente la familia abandono el proceso de acoger");

            animal6.setEstado(TiempoRef);

        } else {
            System.out.println("No existe ese estado");
        }
        return animal6;
    }


    /**
     * @param estado
     */
    @Override
    public void Acoger(Estado estado) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Animal animal7 = session.find(Animal.class, estado);
        System.out.println("El estado de este animal es: " + animal7.getEstado() + "y esta adoptado");
        if (animal7.getEstado()==ProxAcog) {

            deleteById(animal7.getId());
        } else {
            System.out.println("No tiene familia escogida, cambie el estado a Proceso de acogida para insertar la familia y asi poder acogerlo ");
        }

    }

    /**
     * @param animal
     * @return Crear Animal
     */

    @Override
    public Animal create(Animal animal) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();

        Animal animal8 = new Animal();

        System.out.println("Nombre");
        animal8.setNombre(scanner.nextLine());



        Animales especie = Aranya;

        System.out.println(" Especie: (1)Aranya, (2)Perro, (3)Gato, (4)Pajaro, (5)Cerdo vietnamita, (6)Serpiente, (7)Camaleon");
        int especie2 = scanner2.nextInt();


        switch (especie2) {

            case 1:
                especie=Aranya;
                break;
            case 2:
                especie=Perro;
                break;
            case 3:
                especie=Gato;
                break;
            case 4:
                especie=Pajaro;
                break;
            case 5:
                especie=CerdoV;
                break;
            case 6:
                especie=Serpiente;
                break;
            case 7:
                especie=Camaleon;
                break;
            default:
                System.out.println("Elige una opcion correcta");
                break;
        }
        animal8.setEspecie(especie);

        System.out.println("Edad: ");
        animal8.setEdad(scanner2.nextInt());

        System.out.println("Descripcion: ");
        animal8.setDesc(scanner.nextLine());

        System.out.println("Estado");

        Estado estado =  RecienAb;

        System.out.println(" Estado: (1)Recien Acogido, (2)Veterano en el refugio, (3)En proceso de acogida");
        int estado2 = scanner2.nextInt();


        switch (estado2) {
            case 1:
                estado=RecienAb;
                break;
            case 2:
                estado=TiempoRef;
                break;
            case 3:
                estado=ProxAcog;
                break;
            default:
                System.out.println("Elige una opcion correcta");
                break;

        }
        animal8.setEstado(estado);

        if (estado==ProxAcog){
            System.out.print("Nombre: ");
            animal8.setNombreF(scanner.nextLine());
            System.out.print("Edad: ");
            animal8.setEdadF(scanner2.nextInt());
            System.out.print("Ciudad: ");
            animal8.setCiudadF(scanner.nextLine());
        } else {
            animal8.setNombreF(null);
            animal8.setEdadF(null);
            animal8.setCiudadF(null);
        }

        return animal8;
    }


    /**
     * @param id
     * @return borra un id concreto
     */
    @Override
    public void deleteById(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Iniciar una transacción
        Transaction transaction = null;

        try {
            // Iniciar la transacción
            transaction = session.beginTransaction();

            // Buscar el animal por su ID
            Animal animal = session.get(Animal.class, id);

            // Verificar si el animal fue encontrado
            if (animal != null) {
                // Eliminar el animal
                session.delete(animal);
                System.out.println("El animal con ID " + id + " ha sido eliminado.");
            } else {
                System.out.println("No se encontró un animal con el ID proporcionado.");
            }

            // Hacer commit de la transacción
            transaction.commit();
        } catch (Exception e) {
            // Si hay algún error, hacer rollback
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Cerrar la sesión
            session.close();
        }
    }
}
