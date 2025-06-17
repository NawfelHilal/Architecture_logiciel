package com.example.pizza;

/**
 * Classe représentant une pizza au fromage.
 * Cette classe implémente les méthodes spécifiques pour la préparation,
 * la cuisson et l'emballage d'une pizza au fromage.
 * @author Hilal Nawfel
 * @version 1.0
 */
public class PizzaFromage extends Pizza {
    /**
     * Constructeur de la pizza au fromage.
     * Configure les ingrédients spécifiques à une pizza au fromage
     * en utilisant le pattern Builder.
     */
    public PizzaFromage() {
        super();
        PizzaBuilder builder = new PizzaBuilder(this);
        builder.pateFine()
                .sauceTomate()
                .avecFromage()
                .ajouterMozzarella()
                .ajouterEmmental()
                .build();
    }

    /**
     * Prépare la pizza au fromage.
     * Cette méthode est appelée lors de la préparation de la commande.
     */
    @Override
    public void preparer() {
        System.out.println("Préparation de la pizza fromage");
    }

    /**
     * Cuit la pizza au fromage.
     * Cette méthode est appelée après la préparation.
     */
    @Override
    public void cuire() {
        System.out.println("Cuisson de la pizza fromage");
    }

    /**
     * Emballe la pizza au fromage.
     * Cette méthode est appelée après la cuisson.
     */
    @Override
    public void emballer() {
        System.out.println("Emballage de la pizza fromage");
    }
}