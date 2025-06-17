package com.example.pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Gestionnaire de commandes de pizzas.
 * Cette classe implémente le pattern Singleton pour assurer une instance unique
 * du gestionnaire de commandes. Elle gère l'ajout, la suppression et la
 * récupération
 * des commandes de pizzas de manière thread-safe.
 *
 * @author Hilal Nawfel
 * @version 1.0
 */
public class CommandeManager {
    // Instance unique du CommandeManager (pattern Singleton)
    private static CommandeManager instance;

    // Lock utilisé pour assurer la thread-safety lors de la création de l'instance
    // Le ReentrantLock permet à un thread de réacquérir le même lock plusieurs fois
    // sans bloquer, ce qui est utile pour les méthodes récursives
    private static final ReentrantLock lock = new ReentrantLock();

    private final List<Pizza> commandes;

    /**
     * Constructeur privé pour empêcher l'instanciation directe.
     * Initialise la liste des commandes.
     */
    private CommandeManager() {
        this.commandes = new ArrayList<>();
    }

    /**
     * Retourne l'instance unique du CommandeManager.
     * Si l'instance n'existe pas, elle est créée de manière thread-safe.
     *
     * @return L'instance unique du CommandeManager
     */
    public static CommandeManager getInstance() {
        // Premier check sans lock pour éviter le coût du lock si l'instance existe déjà
        if (instance == null) {
            // Acquisition du lock pour la création de l'instance
            // Cela empêche plusieurs threads de créer simultanément l'instance
            lock.lock();
            try {
                // Double-check pattern pour éviter la création multiple d'instances
                // même si plusieurs threads passent le premier check
                if (instance == null) {
                    instance = new CommandeManager();
                }
            } finally {
                // Le lock est toujours libéré dans le bloc finally
                // pour garantir qu'il sera libéré même en cas d'exception
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Ajoute une commande de pizza.
     *
     * @param pizza La pizza à ajouter à la commande
     * @throws IllegalArgumentException si la pizza est null
     */
    public void ajouterCommande(Pizza pizza) {
        if (pizza == null) {
            throw new IllegalArgumentException("La pizza ne peut pas être null");
        }
        commandes.add(pizza);
    }

    /**
     * Supprime une commande de pizza.
     *
     * @param pizza La pizza à supprimer de la commande
     */
    public void supprimerCommande(Pizza pizza) {
        commandes.remove(pizza);
    }

    /**
     * Retourne une copie défensive de la liste des commandes.
     *
     * @return Une nouvelle liste contenant toutes les commandes
     */
    public List<Pizza> getCommandes() {
        return new ArrayList<>(commandes);
    }

    /**
     * Vide la liste des commandes.
     */
    public void viderCommandes() {
        commandes.clear();
    }
}