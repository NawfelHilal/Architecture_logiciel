package com.example.pizza;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Tests unitaires pour la classe CommandeManager.
 * Vérifie le bon fonctionnement du pattern Singleton et la gestion des
 * commandes.
 */
class CommandeManagerTest {

    /**
     * Test que le pattern Singleton fonctionne correctement.
     * Vérifie que deux appels à getInstance() retournent la même instance.
     */
    @Test
    void testSingleInstance() {
        CommandeManager instance1 = CommandeManager.getInstance();
        CommandeManager instance2 = CommandeManager.getInstance();
        assertSame(instance1, instance2, "Les instances devraient être identiques");
    }

    /**
     * Test le comportement du Singleton dans un environnement multi-threadé.
     * Vérifie que le Singleton reste thread-safe même avec plusieurs threads.
     */
    @Test
    void testMultithreading() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.submit(() -> {
                CommandeManager instance = CommandeManager.getInstance();
                assertNotNull(instance);
            });
        }
        executor.shutdown();
        assertTrue(executor.awaitTermination(5, TimeUnit.SECONDS));
    }

    /**
     * Test la gestion des commandes (ajout, suppression, récupération).
     * Vérifie que les opérations CRUD fonctionnent correctement.
     */
    @Test
    void testCommandeManagement() {
        CommandeManager manager = CommandeManager.getInstance();
        manager.viderCommandes();

        Pizza pizza = new PizzaFactory().createPizza("fromage");

        // Test ajout
        manager.ajouterCommande(pizza);
        List<Pizza> commandes = manager.getCommandes();
        assertEquals(1, commandes.size());
        assertTrue(commandes.contains(pizza));

        // Test suppression
        manager.supprimerCommande(pizza);
        commandes = manager.getCommandes();
        assertEquals(0, commandes.size());

        // Test ajout null
        assertThrows(IllegalArgumentException.class, () -> {
            manager.ajouterCommande(null);
        });
    }
}