package org.example.DAO;

import org.example.entities.Animal;
import org.example.entities.Animales;
import org.example.entities.Estado;

import java.util.List;

public interface IAnimal {
    /**
     *
     * @return todos los animales
     */

    List<Animal> findAll();


    /**
     *
     *@param id
     * @return Segun id
     */
    Animal findById(Integer id);

    /**
     *
     *@param nombre
     * @return Segun Nombre
     */
    Animal findByNombre(String nombre);


    /**
     *
     *@param Especie
     * @return Segun Especie
     */
    List<Animal> findByEspecie(Animales Especie);


    /**
     *
     *@param estado
     * @return Segun Estado
     */
    List<Animal> findByEstado(Estado estado);

    /**
     *
     *@param estado
     * @return Cambiar Estado
     */
    Animal ChangeEstado(Estado estado);



    Animal create (Animal animal);

    Animal update (Animal animal);

    /**
     *
     * @param id
     * @return borra un id concreto
     */
    boolean deleteById (Integer id);
}
