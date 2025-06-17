package com.example.pizza;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant une pizza.
 * Cette classe définit la structure de base d'une pizza avec ses attributs
 * communs
 * et les méthodes que toutes les pizzas doivent implémenter.
 * Elle utilise le pattern Builder pour permettre une construction flexible des
 * pizzas.
 *
 * @author Hilal Nawfel
 * @version 1.0
 */
public abstract class Pizza {
    /**
     * Type de pâte de la pizza (fine, épaisse, etc.)
     */
    private String typePate;

    /**
     * Type de sauce de la pizza (tomate, crème, BBQ, etc.)
     */
    private String typeSauce;

    /**
     * Liste des garnitures de la pizza
     */
    private List<String> garnitures;

    /**
     * Indique si la pizza contient du fromage
     */
    private boolean contientFromage;

    /**
     * Indique si la pizza contient des légumes
     */
    private boolean contientLegumes;

    /**
     * Constructeur de base pour une pizza.
     * Initialise la liste des garnitures.
     */
    public Pizza() {
        this.garnitures = new ArrayList<>();
    }

    /**
     * Prépare la pizza.
     * Cette méthode doit être implémentée par les classes concrètes.
     */
    public abstract void preparer();

    /**
     * Cuit la pizza.
     * Cette méthode doit être implémentée par les classes concrètes.
     */
    public abstract void cuire();

    /**
     * Emballe la pizza.
     * Cette méthode doit être implémentée par les classes concrètes.
     */
    public abstract void emballer();

    /**
     * Retourne le type de pâte de la pizza.
     * 
     * @return Le type de pâte
     */
    public String getTypePate() {
        return typePate;
    }

    /**
     * Retourne le type de sauce de la pizza.
     * 
     * @return Le type de sauce
     */
    public String getTypeSauce() {
        return typeSauce;
    }

    /**
     * Retourne une copie défensive de la liste des garnitures.
     * 
     * @return Une nouvelle liste contenant les garnitures
     */
    public List<String> getGarnitures() {
        return new ArrayList<>(garnitures);
    }

    /**
     * Indique si la pizza contient du fromage.
     * 
     * @return true si la pizza contient du fromage, false sinon
     */
    public boolean isContientFromage() {
        return contientFromage;
    }

    /**
     * Indique si la pizza contient des légumes.
     * 
     * @return true si la pizza contient des légumes, false sinon
     */
    public boolean isContientLegumes() {
        return contientLegumes;
    }

    /**
     * Définit le type de pâte de la pizza.
     * 
     * @param typePate Le type de pâte à définir
     */
    protected void setTypePate(String typePate) {
        this.typePate = typePate;
    }

    /**
     * Définit le type de sauce de la pizza.
     * 
     * @param typeSauce Le type de sauce à définir
     */
    protected void setTypeSauce(String typeSauce) {
        this.typeSauce = typeSauce;
    }

    /**
     * Définit si la pizza contient du fromage.
     * 
     * @param contientFromage true si la pizza contient du fromage, false sinon
     */
    protected void setContientFromage(boolean contientFromage) {
        this.contientFromage = contientFromage;
    }

    /**
     * Définit si la pizza contient des légumes.
     * 
     * @param contientLegumes true si la pizza contient des légumes, false sinon
     */
    protected void setContientLegumes(boolean contientLegumes) {
        this.contientLegumes = contientLegumes;
    }

    /**
     * Ajoute une garniture à la pizza.
     * 
     * @param garniture La garniture à ajouter
     */
    protected void addGarniture(String garniture) {
        this.garnitures.add(garniture);
    }

    /**
     * Builder pour la construction de pizzas.
     * Permet une construction fluide et flexible des pizzas.
     */
    public static class PizzaBuilder {
        private final Pizza pizza;

        /**
         * Constructeur du builder.
         * 
         * @param pizza L'instance de pizza à construire
         */
        public PizzaBuilder(Pizza pizza) {
            this.pizza = pizza;
        }

        /**
         * Configure la pâte fine pour la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder pateFine() {
            pizza.setTypePate("Fine");
            return this;
        }

        /**
         * Configure la pâte épaisse pour la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder pateEpaisse() {
            pizza.setTypePate("Epaisse");
            return this;
        }

        /**
         * Configure la sauce tomate pour la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder sauceTomate() {
            pizza.setTypeSauce("Tomate");
            return this;
        }

        /**
         * Configure la sauce crème pour la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder sauceCreme() {
            pizza.setTypeSauce("Crème");
            return this;
        }

        /**
         * Configure la sauce BBQ pour la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder sauceBBQ() {
            pizza.setTypeSauce("BBQ");
            return this;
        }

        /**
         * Ajoute du fromage à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder avecFromage() {
            pizza.setContientFromage(true);
            return this;
        }

        /**
         * Retire le fromage de la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder sansFromage() {
            pizza.setContientFromage(false);
            return this;
        }

        /**
         * Ajoute des légumes à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder avecLegumes() {
            pizza.setContientLegumes(true);
            return this;
        }

        /**
         * Retire les légumes de la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder sansLegumes() {
            pizza.setContientLegumes(false);
            return this;
        }

        /**
         * Ajoute de la mozzarella à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterMozzarella() {
            pizza.addGarniture("Mozzarella");
            return this;
        }

        /**
         * Ajoute de l'emmental à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterEmmental() {
            pizza.addGarniture("Emmental");
            return this;
        }

        /**
         * Ajoute du jambon à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterJambon() {
            pizza.addGarniture("Jambon");
            return this;
        }

        /**
         * Ajoute des champignons à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterChampignons() {
            pizza.addGarniture("Champignons");
            return this;
        }

        /**
         * Ajoute des poivrons à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterPoivrons() {
            pizza.addGarniture("Poivrons");
            return this;
        }

        /**
         * Ajoute des oignons à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterOignons() {
            pizza.addGarniture("Oignons");
            return this;
        }

        /**
         * Ajoute des olives à la pizza.
         * 
         * @return Le builder pour le chaînage des méthodes
         */
        public PizzaBuilder ajouterOlives() {
            pizza.addGarniture("Olives");
            return this;
        }

        /**
         * Construit la pizza et vérifie sa validité.
         * 
         * @return La pizza construite
         * @throws IllegalStateException si la pizza n'est pas valide
         */
        public Pizza build() {
            validatePizza();
            return pizza;
        }

        /**
         * Vérifie que la pizza est valide.
         * 
         * @throws IllegalStateException si la pizza n'est pas valide
         */
        private void validatePizza() {
            if (pizza.getTypePate() == null || pizza.getTypePate().trim().isEmpty()) {
                throw new IllegalStateException("La pâte est requise");
            }
            if (pizza.getTypeSauce() == null || pizza.getTypeSauce().trim().isEmpty()) {
                throw new IllegalStateException("La sauce est requise");
            }
        }
    }
}