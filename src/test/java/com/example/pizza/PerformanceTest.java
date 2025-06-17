package com.example.pizza;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de performance pour le système de gestion de pizzas.
 * Ces tests évaluent les performances des opérations clés du système.
 */
class PerformanceTest {

    private static final int ITERATIONS = 1000;
    private static final long TIMEOUT_MS = 5000; // 5 secondes maximum par test

    /**
     * Test de performance pour la création de pizzas via la factory.
     * Mesure le temps nécessaire pour créer un grand nombre de pizzas.
     */
    @Test
    @Timeout(value = TIMEOUT_MS, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test de performance de création de pizzas")
    void testPizzaCreationPerformance() {
        PizzaFactory factory = new PizzaFactory();
        List<Pizza> pizzas = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            pizzas.add(factory.createPizza("fromage"));
            pizzas.add(factory.createPizza("vegetarienne"));
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Conversion en millisecondes

        System.out.println("Temps de création de " + ITERATIONS * 2 + " pizzas: " + duration + "ms");
        System.out.println("Temps moyen par pizza: " + (duration / (ITERATIONS * 2)) + "ms");

        assertTrue(duration < TIMEOUT_MS, "La création des pizzas prend trop de temps");
    }

    /**
     * Test de performance pour la gestion des commandes.
     * Mesure le temps nécessaire pour ajouter et supprimer un grand nombre de
     * commandes.
     */
    @Test
    @Timeout(value = TIMEOUT_MS, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test de performance de gestion des commandes")
    void testCommandeManagementPerformance() {
        CommandeManager manager = CommandeManager.getInstance();
        PizzaFactory factory = new PizzaFactory();
        List<Pizza> pizzas = new ArrayList<>();

        // Préparation des pizzas
        for (int i = 0; i < ITERATIONS; i++) {
            pizzas.add(factory.createPizza("fromage"));
        }

        // Test d'ajout de commandes
        long startTime = System.nanoTime();
        for (Pizza pizza : pizzas) {
            manager.ajouterCommande(pizza);
        }
        long endTime = System.nanoTime();
        long addDuration = (endTime - startTime) / 1_000_000;

        System.out.println("Temps d'ajout de " + ITERATIONS + " commandes: " + addDuration + "ms");
        System.out.println("Temps moyen par ajout: " + (addDuration / ITERATIONS) + "ms");

        // Test de récupération des commandes
        startTime = System.nanoTime();
        List<Pizza> commandes = manager.getCommandes();
        endTime = System.nanoTime();
        long getDuration = (endTime - startTime) / 1_000_000;

        System.out.println("Temps de récupération de " + ITERATIONS + " commandes: " + getDuration + "ms");

        // Test de suppression des commandes
        startTime = System.nanoTime();
        for (Pizza pizza : pizzas) {
            manager.supprimerCommande(pizza);
        }
        endTime = System.nanoTime();
        long removeDuration = (endTime - startTime) / 1_000_000;

        System.out.println("Temps de suppression de " + ITERATIONS + " commandes: " + removeDuration + "ms");
        System.out.println("Temps moyen par suppression: " + (removeDuration / ITERATIONS) + "ms");

        assertTrue(addDuration < TIMEOUT_MS, "L'ajout des commandes prend trop de temps");
        assertTrue(getDuration < TIMEOUT_MS, "La récupération des commandes prend trop de temps");
        assertTrue(removeDuration < TIMEOUT_MS, "La suppression des commandes prend trop de temps");
    }

    /**
     * Test de performance pour la construction de pizzas avec le Builder.
     * Mesure le temps nécessaire pour construire un grand nombre de pizzas
     * personnalisées.
     */
    @Test
    @Timeout(value = TIMEOUT_MS, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test de performance du Builder")
    void testBuilderPerformance() {
        List<Pizza> pizzas = new ArrayList<>();

        long startTime = System.nanoTime();

        for (int i = 0; i < ITERATIONS; i++) {
            Pizza pizza = new Pizza() {
                @Override
                public void preparer() {
                }

                @Override
                public void cuire() {
                }

                @Override
                public void emballer() {
                }
            };

            Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizza);
            builder.pateFine()
                    .sauceTomate()
                    .avecFromage()
                    .ajouterMozzarella()
                    .ajouterChampignons()
                    .build();

            pizzas.add(pizza);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;

        System.out.println("Temps de construction de " + ITERATIONS + " pizzas avec le Builder: " + duration + "ms");
        System.out.println("Temps moyen par construction: " + (duration / ITERATIONS) + "ms");

        assertTrue(duration < TIMEOUT_MS, "La construction des pizzas avec le Builder prend trop de temps");
    }
}