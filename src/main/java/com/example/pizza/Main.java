package com.example.pizza;

/**
 * Classe principale du système de gestion de commandes de pizzas.
 * Cette classe démontre l'utilisation des différents patterns de conception
 * (Factory, Singleton, Builder) dans le contexte d'un système de commande de
 * pizzas.
 * @author Hilal Nawfel
 * @version 1.0
 */
public class Main {
    /**
     * Point d'entrée du programme.
     * 
     * @param args Arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        System.out.println("=== Système de Gestion de Commandes de Pizzas ===");

        // Création de la factory pour la production de pizzas
        PizzaFactory factory = new PizzaFactory();

        // Démonstration de la création de pizzas via la Factory
        System.out.println("\n1. Création de pizzas via la Factory :");
        Pizza pizzaFromage = factory.createPizza("fromage");
        Pizza pizzaVegetarienne = factory.createPizza("vegetarienne");

        // Démonstration de la création d'une pizza personnalisée via le Builder
        System.out.println("\n2. Création d'une pizza personnalisée via le Builder :");
        Pizza pizzaCustom = creerPizzaPersonnalisee();

        // Démonstration de la gestion des commandes avec le Singleton
        System.out.println("\n3. Gestion des commandes :");
        CommandeManager manager = CommandeManager.getInstance();

        // Ajout des commandes
        manager.ajouterCommande(pizzaFromage);
        manager.ajouterCommande(pizzaVegetarienne);
        manager.ajouterCommande(pizzaCustom);

        // Affichage des commandes
        afficherCommandes(manager);

        // Démonstration de la suppression d'une commande
        System.out.println("\n4. Suppression d'une commande :");
        manager.supprimerCommande(pizzaFromage);
        System.out.println("Nombre de commandes restantes : " + manager.getCommandes().size());

        // Démonstration du processus de préparation d'une pizza
        System.out.println("\n5. Processus de préparation d'une pizza :");
        pizzaFromage.preparer();
        pizzaFromage.cuire();
        pizzaFromage.emballer();
    }

    /**
     * Crée une pizza personnalisée en utilisant le pattern Builder.
     * 
     * @return Une pizza personnalisée
     */
    private static Pizza creerPizzaPersonnalisee() {
        Pizza pizzaCustom = new Pizza() {
            @Override
            public void preparer() {
                System.out.println("Préparation de la pizza personnalisée");
            }

            @Override
            public void cuire() {
                System.out.println("Cuisson de la pizza personnalisée");
            }

            @Override
            public void emballer() {
                System.out.println("Emballage de la pizza personnalisée");
            }
        };

        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizzaCustom);
        builder.pateFine()
                .sauceTomate()
                .avecFromage()
                .ajouterJambon()
                .ajouterChampignons()
                .build();

        return pizzaCustom;
    }

    /**
     * Affiche les détails des commandes actuelles.
     * 
     * @param manager Le gestionnaire de commandes
     */
    private static void afficherCommandes(CommandeManager manager) {
        System.out.println("\nCommandes actuelles :");
        manager.getCommandes().forEach(pizza -> {
            StringBuilder description = new StringBuilder("- Pizza avec ");
            if (pizza.isContientFromage()) {
                description.append("fromage ");
            }
            if (pizza.isContientLegumes()) {
                description.append("légumes ");
            }
            description.append("et garnitures : ").append(pizza.getGarnitures());
            System.out.println(description.toString());
        });
    }
}