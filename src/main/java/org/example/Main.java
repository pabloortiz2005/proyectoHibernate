package org.example;

import org.example.DAO.AnimalDAOimpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {




        AnimalDAOimpl AnimalDAOimpl = new AnimalDAOimpl();
        System.out.println(AnimalDAOimpl.findAll().toString());
        }
    }
