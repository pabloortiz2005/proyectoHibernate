package org.example;

import org.example.DAO.AnimalDAOimpl;


import org.example.DAO.AnimalDAOimpl;
import org.example.entities.Animal;
import org.example.entities.Animales;
import org.example.entities.Estado;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Scanner;

    public class Main {
        public static void main(String[] args) {

            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();

            AnimalDAOimpl animalDAO = new AnimalDAOimpl();
            Scanner scanner = new Scanner(System.in);
            int option;

            do {
                System.out.println("\n--- Menú de Opciones ---");
                System.out.println("1. Mostrar todos los animales");
                System.out.println("2. Buscar animal por ID");
                System.out.println("3. Buscar animal por nombre");
                System.out.println("4. Buscar animales por especie");
                System.out.println("5. Buscar animales por estado");
                System.out.println("6. Cambiar estado de un animal");
                System.out.println("7. Acoger un animal");
                System.out.println("8. Crear un nuevo animal");
                System.out.println("9. Eliminar un animal por ID");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");

                option = scanner.nextInt();
                scanner.nextLine(); // Consumir salto de línea

                switch (option) {
                    case 1: // Mostrar todos los animales
                        List<Animal> animales = animalDAO.findAll();
                        animales.forEach(System.out::println);
                        break;
                    case 2: // Buscar animal por ID
                        System.out.print("Ingrese el ID del animal: ");
                        int id = scanner.nextInt();
                        Animal animalPorId = animalDAO.findById(id);
                        System.out.println(animalPorId != null ? animalPorId : "Animal no encontrado.");
                        break;
                    case 3: // Buscar animal por nombre
                        System.out.print("Ingrese el nombre del animal: ");
                        String nombre = scanner.nextLine();
                        Animal animalPorNombre = animalDAO.findByNombre(nombre);
                        System.out.println(animalPorNombre != null ? animalPorNombre : "Animal no encontrado.");
                        break;
                    case 4: // Buscar animales por especie
                        System.out.println("Seleccione la especie: (1) Aranya, (2) Perro, (3) Gato, (4) Pajaro, (5) Cerdo vietnamita, (6) Serpiente, (7) Camaleon");
                        int opcionEspecie = scanner.nextInt();
                        Animales especie = Animales.values()[opcionEspecie - 1];
                        List<Animal> animalesPorEspecie = animalDAO.findByEspecie(especie);
                        animalesPorEspecie.forEach(System.out::println);
                        break;
                    case 5: // Buscar animales por estado
                        System.out.println("Seleccione el estado: (1) Recien Acogido, (2) Veterano en el refugio, (3) En proceso de acogida");
                        int opcionEstado = scanner.nextInt();
                        Estado estado = Estado.values()[opcionEstado - 1];
                        List<Animal> animalesPorEstado = animalDAO.findByEstado(estado);
                        animalesPorEstado.forEach(System.out::println);
                        break;
                    case 6: // Cambiar estado de un animal
                        System.out.print("Ingrese el ID del animal: ");
                        int idCambiarEstado = scanner.nextInt();
                        Animal animalParaCambiar = animalDAO.findById(idCambiarEstado);
                        if (animalParaCambiar != null) {
                            animalDAO.ChangeEstado(animalParaCambiar.getEstado());
                            System.out.println("Estado cambiado exitosamente.");
                        } else {
                            System.out.println("Animal no encontrado.");
                        }
                        break;
                    case 7: // Acoger un animal
                        System.out.print("Ingrese el ID del animal: ");
                        int idAcoger = scanner.nextInt();
                        Animal animalParaAcoger = animalDAO.findById(idAcoger);
                        if (animalParaAcoger != null) {
                            animalDAO.Acoger(animalParaAcoger.getEstado());
                        } else {
                            System.out.println("Animal no encontrado.");
                        }
                        break;
                    case 8: // Crear un nuevo animal
                        Animal nuevoAnimal = animalDAO.create(new Animal());
                        System.out.println("Animal creado: " + nuevoAnimal);
                        break;
                    case 9: // Eliminar un animal por ID
                        System.out.print("Ingrese el ID del animal a eliminar: ");
                        int idEliminar = scanner.nextInt();
                        animalDAO.deleteById(idEliminar);
                        break;
                    case 0: // Salir
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente de nuevo.");
                        break;
                }
            } while (option != 0);

            scanner.close();
            session.close();
        }
    }

