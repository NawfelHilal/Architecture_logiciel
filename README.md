# Projet Java

Ce projet est une application Java de base créée avec Maven.

## Prérequis

- Java JDK 17 ou supérieur
- Maven 3.6 ou supérieur

## Structure du projet

```
java-project/
├── src/
│   ├── main/java/    # Code source principal
│   └── test/java/    # Tests unitaires
├── pom.xml           # Configuration Maven
└── README.md         # Ce fichier
```

## Comment exécuter

Pour compiler et exécuter le projet :

```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="com.example.App"
```

## Tests

Pour exécuter les tests :

```bash
mvn test
```
