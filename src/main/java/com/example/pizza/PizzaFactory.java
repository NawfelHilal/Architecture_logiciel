package com.example.pizza;

/**
 * Factory pour la création de pizzas.
 * Cette classe implémente le pattern Factory pour créer différents types de
 * pizzas.
 * Elle permet de centraliser la logique de création des pizzas et de faciliter
 * l'ajout de nouveaux types de pizzas.
 *
 * @author Hilal Nawfel
 * @version 1.0
 */
public class PizzaFactory {

    /**
     * Crée une pizza en fonction du type spécifié.
     * 
     * @param type Le type de pizza à créer ("fromage" ou "vegetarienne")
     * @return Une instance de Pizza du type demandé
     * @throws IllegalArgumentException si le type est null ou non supporté
     */
    public Pizza createPizza(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Le type de pizza ne peut pas être null");
        }

        return switch (type.toLowerCase()) {
            case "fromage" -> new PizzaFromage();
            case "vegetarienne" -> new PizzaVegetarienne();
            default -> throw new IllegalArgumentException("Type de pizza non supporté: " + type);
        };
    }
}