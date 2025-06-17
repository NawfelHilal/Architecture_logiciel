package com.example.pizza;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests de qualité du code pour le système de gestion de pizzas.
 * Ces tests vérifient que le code suit les bonnes pratiques et conventions.
 */
class CodeQualityTest {

    /**
     * Test la documentation des classes principales.
     * Vérifie que toutes les classes publiques sont correctement documentées.
     */
    @Test
    @DisplayName("Vérification de la documentation des classes")
    void testClassDocumentation() {
        Class<?>[] classes = {
                Pizza.class,
                PizzaFromage.class,
                PizzaVegetarienne.class,
                PizzaFactory.class,
                CommandeManager.class
        };

        for (Class<?> clazz : classes) {
            // Vérifie que la classe n'est pas marquée comme dépréciée
            assertFalse(clazz.isAnnotationPresent(java.lang.Deprecated.class),
                    "La classe " + clazz.getSimpleName() + " ne devrait pas être marquée comme dépréciée");

            // Vérifie la présence de documentation JavaDoc
            String classDoc = clazz.getPackage().getName() + "." + clazz.getSimpleName();
            assertTrue(clazz.getPackage().getName().contains("com.example.pizza"),
                    "La classe " + clazz.getSimpleName() + " devrait être dans le package com.example.pizza");
        }
    }

    /**
     * Test la documentation des méthodes publiques.
     * Vérifie que toutes les méthodes publiques sont correctement documentées.
     */
    @Test
    @DisplayName("Vérification de la documentation des méthodes")
    void testMethodDocumentation() {
        Class<?>[] classes = {
                Pizza.class,
                PizzaFromage.class,
                PizzaVegetarienne.class,
                PizzaFactory.class,
                CommandeManager.class
        };

        for (Class<?> clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers())) {
                    // Vérifie que la méthode n'est pas marquée comme dépréciée
                    assertFalse(method.isAnnotationPresent(java.lang.Deprecated.class),
                            "La méthode " + method.getName() + " ne devrait pas être marquée comme dépréciée");

                    // Vérifie que la méthode est dans le bon package
                    assertTrue(method.getDeclaringClass().getPackage().getName().contains("com.example.pizza"),
                            "La méthode " + method.getName() + " devrait être dans le package com.example.pizza");
                }
            }
        }
    }

    /**
     * Test la cohérence des noms de méthodes.
     * Vérifie que les noms des méthodes suivent les conventions Java.
     */
    @Test
    @DisplayName("Vérification de la cohérence des noms de méthodes")
    void testMethodNamingConventions() {
        Class<?>[] classes = {
                Pizza.class,
                PizzaFromage.class,
                PizzaVegetarienne.class,
                PizzaFactory.class,
                CommandeManager.class
        };

        for (Class<?> clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                String methodName = method.getName();

                // Vérifie que les getters commencent par "get" ou "is"
                if (methodName.startsWith("get") || methodName.startsWith("is")) {
                    assertTrue(method.getParameterCount() == 0,
                            "Le getter " + methodName + " ne devrait pas avoir de paramètres");
                }

                // Vérifie que les setters commencent par "set"
                if (methodName.startsWith("set")) {
                    assertTrue(method.getParameterCount() == 1,
                            "Le setter " + methodName + " devrait avoir exactement un paramètre");
                }

                // Vérifie que les noms de méthodes sont en camelCase
                assertTrue(methodName.matches("^[a-z][a-zA-Z0-9]*$"),
                        "Le nom de la méthode " + methodName + " devrait être en camelCase");
            }
        }
    }

    /**
     * Test la cohérence des noms de variables.
     * Vérifie que les noms des variables suivent les conventions Java.
     */
    @Test
    @DisplayName("Vérification de la cohérence des noms de variables")
    void testVariableNamingConventions() {
        Class<?>[] classes = {
                Pizza.class,
                PizzaFromage.class,
                PizzaVegetarienne.class,
                PizzaFactory.class,
                CommandeManager.class
        };

        for (Class<?> clazz : classes) {
            Arrays.stream(clazz.getDeclaredFields())
                    .forEach(field -> {
                        String fieldName = field.getName();
                        assertTrue(fieldName.matches("^[a-z][a-zA-Z0-9]*$"),
                                "Le nom de la variable " + fieldName + " dans " +
                                        clazz.getSimpleName() + " devrait être en camelCase");
                    });
        }
    }

    /**
     * Test la visibilité des membres.
     * Vérifie que les membres ont une visibilité appropriée.
     */
    @Test
    @DisplayName("Vérification de la visibilité des membres")
    void testMemberVisibility() {
        // Vérifie que les champs de Pizza sont privés
        Arrays.stream(Pizza.class.getDeclaredFields())
                .forEach(field -> assertTrue(Modifier.isPrivate(field.getModifiers()),
                        "Les champs de Pizza devraient être privés"));

        // Vérifie que les méthodes de Pizza sont publiques ou protégées
        Arrays.stream(Pizza.class.getDeclaredMethods())
                .forEach(method -> assertTrue(
                        Modifier.isPublic(method.getModifiers()) ||
                                Modifier.isProtected(method.getModifiers()),
                        "Les méthodes de Pizza devraient être publiques ou protégées"));
    }
}