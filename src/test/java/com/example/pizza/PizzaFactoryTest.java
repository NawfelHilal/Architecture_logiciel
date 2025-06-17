package com.example.pizza;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires pour la classe PizzaFactory.
 * Vérifie le bon fonctionnement du pattern Factory pour la création de pizzas.
 */
class PizzaFactoryTest {

    /**
     * Test la création d'une pizza au fromage.
     * Vérifie que la factory crée correctement une instance de PizzaFromage.
     */
    @Test
    void testCreatePizzaFromage() {
        PizzaFactory factory = new PizzaFactory();
        Pizza pizza = factory.createPizza("fromage");

        assertNotNull(pizza);
        assertTrue(pizza instanceof PizzaFromage);
        assertEquals("Fine", pizza.getTypePate());
        assertEquals("Tomate", pizza.getTypeSauce());
        assertTrue(pizza.isContientFromage());
        assertFalse(pizza.isContientLegumes());
    }

    /**
     * Test la création d'une pizza végétarienne.
     * Vérifie que la factory crée correctement une instance de PizzaVegetarienne.
     */
    @Test
    void testCreatePizzaVegetarienne() {
        PizzaFactory factory = new PizzaFactory();
        Pizza pizza = factory.createPizza("vegetarienne");

        assertNotNull(pizza);
        assertTrue(pizza instanceof PizzaVegetarienne);
        assertEquals("Fine", pizza.getTypePate());
        assertEquals("Tomate", pizza.getTypeSauce());
        assertFalse(pizza.isContientFromage());
        assertTrue(pizza.isContientLegumes());
    }

    /**
     * Test la création d'une pizza avec un type invalide.
     * Vérifie que la factory lance une exception appropriée.
     */
    @Test
    void testCreatePizzaInvalidType() {
        PizzaFactory factory = new PizzaFactory();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createPizza("invalid_type"));

        assertEquals("Type de pizza non supporté: invalid_type", exception.getMessage());
    }

    /**
     * Test la création d'une pizza avec un type null.
     * Vérifie que la factory lance une exception appropriée.
     */
    @Test
    void testCreatePizzaNullType() {
        PizzaFactory factory = new PizzaFactory();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> factory.createPizza(null));

        assertEquals("Le type de pizza ne peut pas être null", exception.getMessage());
    }

    /**
     * Test la création de pizzas avec des types valides mais avec des variations de
     * casse.
     * Vérifie que la factory gère correctement les variations de casse.
     */
    @Test
    void testCreatePizzaCaseInsensitive() {
        PizzaFactory factory = new PizzaFactory();

        // Test avec majuscules
        Pizza pizza1 = factory.createPizza("FROMAGE");
        assertTrue(pizza1 instanceof PizzaFromage);

        // Test avec minuscules
        Pizza pizza2 = factory.createPizza("vegetarienne");
        assertTrue(pizza2 instanceof PizzaVegetarienne);

        // Test avec mélange de casse
        Pizza pizza3 = factory.createPizza("VeGeTaRiEnNe");
        assertTrue(pizza3 instanceof PizzaVegetarienne);
    }
}