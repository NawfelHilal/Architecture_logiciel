package com.example.pizza;

/**
 * Classe représentant une pizza végétarienne.
 * Cette classe implémente les méthodes spécifiques pour la préparation,
 * la cuisson et l'emballage d'une pizza végétarienne.
 * 
 * @author Hilal Nawfel
 * @version 1.0
 */
public class PizzaVegetarienne extends Pizza {
    /**
     * Constructeur de la pizza végétarienne.
     * Configure les ingrédients spécifiques à une pizza végétarienne
     * en utilisant le pattern Builder.
     */
    public PizzaVegetarienne() {
        super();
        PizzaBuilder builder = new PizzaBuilder(this);
        builder.pateFine()
                .sauceTomate()
                .avecLegumes()
                .ajouterChampignons()
                .ajouterPoivrons()
                .ajouterOignons()
                .build();
    }

    /**
     * Prépare la pizza végétarienne.
     * Cette méthode est appelée lors de la préparation de la commande.
     */
    @Override
    public void preparer() {
        System.out.println("Préparation de la pizza végétarienne");
    }

    /**
     * Cuit la pizza végétarienne.
     * Cette méthode est appelée après la préparation.
     */
    @Override
    public void cuire() {
        System.out.println("Cuisson de la pizza végétarienne");
    }

    /**
     * Emballe la pizza végétarienne.
     * Cette méthode est appelée après la cuisson.
     */
    @Override
    public void emballer() {
        System.out.println("Emballage de la pizza végétarienne");
    }
}