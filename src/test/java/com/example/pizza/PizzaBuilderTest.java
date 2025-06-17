package com.example.pizza;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Tests unitaires pour la classe PizzaBuilder.
 * Vérifie le bon fonctionnement du pattern Builder pour la construction de
 * pizzas.
 */
class PizzaBuilderTest {
    /**
     * Classe de test interne pour tester le Builder avec une implémentation
     * concrète de Pizza.
     */
    private static class TestPizza extends Pizza {
        @Override
        public void preparer() {
        }

        @Override
        public void cuire() {
        }

        @Override
        public void emballer() {
        }
    }

    /**
     * Test la construction d'une pizza avec des ingrédients de base.
     */
    @Test
    void testBuildPizza() {
        Pizza pizza = new TestPizza();
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizza);
        builder.pateFine()
                .sauceTomate()
                .avecFromage()
                .ajouterMozzarella()
                .build();

        assertNotNull(pizza);
        assertEquals("Fine", pizza.getTypePate());
        assertEquals("Tomate", pizza.getTypeSauce());
        assertTrue(pizza.isContientFromage());
        List<String> garnitures = pizza.getGarnitures();
        assertEquals(1, garnitures.size());
        assertTrue(garnitures.contains("Mozzarella"));
    }

    /**
     * Test la construction d'une pizza avec plusieurs ingrédients
     * en utilisant l'interface fluide du Builder.
     */
    @Test
    void testBuildPizzaFluentInterface() {
        Pizza pizza = new TestPizza();
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizza);
        builder.pateFine()
                .sauceTomate()
                .avecFromage()
                .avecLegumes()
                .ajouterMozzarella()
                .ajouterChampignons()
                .build();

        assertNotNull(pizza);
        assertTrue(pizza.isContientFromage());
        assertTrue(pizza.isContientLegumes());
        List<String> garnitures = pizza.getGarnitures();
        assertEquals(2, garnitures.size());
        assertTrue(garnitures.contains("Mozzarella"));
        assertTrue(garnitures.contains("Champignons"));
    }

    /**
     * Test les cas limites de la construction d'une pizza.
     * Vérifie que les validations fonctionnent correctement.
     */
    @Test
    void testBuildPizzaEdgeCases() {
        // Test sans pâte
        assertThrows(IllegalStateException.class, () -> {
            Pizza pizza = new TestPizza();
            Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizza);
            builder.sauceTomate().build();
        });

        // Test sans sauce
        assertThrows(IllegalStateException.class, () -> {
            Pizza pizza = new TestPizza();
            Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizza);
            builder.pateFine().build();
        });

        // Test avec pâte vide
        assertThrows(IllegalStateException.class, () -> {
            Pizza pizza = new TestPizza();
            Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder(pizza);
            builder.sauceTomate().build();
        });
    }
}