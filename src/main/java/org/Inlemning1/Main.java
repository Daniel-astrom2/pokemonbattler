package org.Inlemning1;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        ArrayList<Pokemon> pokedex = new ArrayList<>();

        Path path = Path.of("pokedex.txt");

        if (Files.exists(path)) {
            loadFromFile(pokedex);
        } else {
            resetSeedData(pokedex);
        }






        while (true) {

            System.out.println("\n--- POKÉDEX ---");
            System.out.println("1) Lägg till");
            System.out.println("2) Visa alla");
            System.out.println("3) Sök");
            System.out.println("4) Ta bort");
            System.out.println("5) Ändra");
            System.out.println("6) Filtera efter typ");
            System.out.println("7) Sortera efter namn");
            System.out.println("8) Statistik");
            System.out.println("9) Återställ seed-data");
            System.out.println("10) Spara");
            System.out.println("11) Ladda");
            System.out.println("12) Avsluta");

            int choice = readIntInRange(scanner, "Val: ", 1, 12);

            // Här kommer vi lägga switchen
            switch (choice){
                case 1: {
                    String name = readNonEmptyString(scanner, "Ange namn: ");

                    int hp = readIntInRange(scanner, "HP: ", 1, 1000);

                    System.out.println("Välj typ:");
                    System.out.println("1) FIRE");
                    System.out.println("2) WATER");
                    System.out.println("3) GRASS");
                    System.out.println("4) ELECTRIC");
                    System.out.println("5) NORMAL");

                    int typeChoice = readIntInRange(scanner, "Typ: ", 1, 5);

                    Type type;

                    switch (typeChoice) {
                        case 1:
                            type = Type.FIRE;
                            break;
                        case 2:
                            type = Type.WATER;
                            break;
                        case 3:
                            type = Type.GRASS;
                            break;
                        case 4:
                            type = Type.ELECTRIC;
                            break;
                        default:
                            type = Type.NORMAL;
                            break;
                    }

                    Pokemon pokemon = new Pokemon(name, type, hp);
                    pokedex.add(pokemon);

                    int numberOFAttacks = readIntInRange(
                            scanner,
                    "hur många attacker ska pokemon ha ?",
                    1,
                    4
                    );

                    for  (int i = 0; i < numberOFAttacks; i ++) {
                        System.out.println("\n--- Attack "  + (i + 1) + " ---");
                        String attackName = readNonEmptyString(


                                scanner,
                                "Ange attackens namn: "
                        );
                        System.out.println("Välj attackens typ:");
                        System.out.println("1) FIRE");
                        System.out.println("2) WATER");
                        System.out.println("3) GRASS");
                        System.out.println("4) ELECTRIC");
                        System.out.println("5) NORMAL");

                        int attackTypeChoice = readIntInRange(scanner, "Typ: ", 1, 5);

                        Type attackType;

                        switch (attackTypeChoice) {
                            case 1:
                                attackType = Type.FIRE;
                                break;
                            case 2:
                                attackType = Type.WATER;
                                break;
                            case 3:
                                attackType = Type.GRASS;
                                break;
                            case 4:
                                attackType = Type.ELECTRIC;
                                break;
                            default:
                                attackType = Type.NORMAL;
                                break;
                        }
                        int accuracy = readIntInRange(
                                scanner,
                                "ange attackens accuracy: ",
                                1,
                                100
                        );
                        int damage = readIntInRange(
                                scanner,
                                "Ange attackens damage: ",
                                1,
                                1000
                        );

                        Attack attack = new Attack(
                                attackName,
                                damage,
                                accuracy,
                                attackType
                        );


                    pokemon.addAttack(attack);
                }


                    System.out.println("Pokemon tillagd!");
                    break;
                }

                case 2: {
                    System.out.println("\n--- ALLA POKEMON ---");

                    for (Pokemon pokemon : pokedex) {
                        System.out.println("Namn: " + pokemon.getName()
                                + ", Typ: " + pokemon.getType()
                                + ", HP: " + pokemon.getCurrentHp()
                                + "/" + pokemon.getMaxHp());


                        for (Attack attack : pokemon.getAttacks()) {
                            System.out.println("  Attack: " + attack.getName()
                                    + ", Damage: " + attack.getBaseDamage()
                                    + ", Accuracy: " + attack.getAccuracy()
                                    + ", Typ: " + attack.getType());
                        }
                    }


                    break;
                }
                case 3:
                    String search = readNonEmptyString(scanner, "sök efter namn");

                    boolean found = false;

                    for (Pokemon pokemon : pokedex) {
                        if (pokemon.getName().equalsIgnoreCase(search)) {
                            System.out.println("Namn: " + pokemon.getName()
                                    + ", Typ: " + pokemon.getType()
                                    + ", HP: "  + pokemon.getCurrentHp()
                                    + "/" + pokemon.getMaxHp());

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("pokemon hittades inte.");
                    }

                    break;


                case 4:
                    String nameToDelete = readNonEmptyString(
                            scanner,
                            "Ange namnet på pokemon som ska tas bort: "
                    );

                    boolean deleted = false;

                    for (int i = 0; i < pokedex.size(); i++) {

                        if (pokedex.get(i).getName().equalsIgnoreCase(nameToDelete)) {

                            pokedex.remove(i);

                            deleted = true;

                            System.out.println("pokemon borttagen!");

                            break;
                        }
                    }

                    if (!deleted) {
                        System.out.println("pokemon hittades inte.");
                    }

                    break;
                case 5:
                    String nametoedit = readNonEmptyString(
                            scanner,
                            "Ange namnet på den pokemon som ska ändras: "
                    );


                    boolean edited = false;

                    for (int i = 0; i < pokedex.size(); i++) {

                        if (pokedex.get(i).getName().equalsIgnoreCase(nametoedit)) {

                            System.out.println("Vad vill du ändra?");
                            System.out.println("1) Namn, HP och typ");
                            System.out.println("2) Lägg till attack");
                            System.out.println("3) Ta bort attack");

                            int editChoice = readIntInRange(
                                    scanner,
                                    "Val: ",
                                    1,
                                    3
                            );
                            switch (editChoice) {
                                case 1:
                                    String newName = readNonEmptyString(
                                            scanner,
                                            "Ange ett nytt namn: "
                                    );
                                    System.out.println("Välj ny typ:");
                                    System.out.println("1) FIRE");
                                    System.out.println("2) WATER");
                                    System.out.println("3) GRASS");
                                    System.out.println("4) ELECTRIC");
                                    System.out.println("5) NORMAL");

                                    int newTypeChoice = readIntInRange(scanner, "Typ: ", 1, 5);
                                    Type newType = Type.NORMAL;
                                    switch (newTypeChoice) {
                                        case 1:
                                            newType = Type.FIRE;
                                            break;
                                        case 2:
                                            newType = Type.WATER;
                                            break;
                                        case 3:
                                            newType = Type.GRASS;
                                            break;


                                        case 4:
                                            newType = Type.ELECTRIC;
                                            break;
                                        default:
                                            newType = Type.NORMAL;
                                            break;
                                    }
                                    int newHP = readIntInRange(
                                            scanner,
                                            "Ange ny HP: ",
                                            1,
                                            1000
                                    );
                                    pokedex.get(i).setType(newType);
                                    pokedex.get(i).setName(newName);
                                    pokedex.get(i).setMaxHp(newHP);

                                    System.out.println("pokemon ändrad");
                                    break;
                                case 2:
                                    addAttack(scanner, pokedex.get(i));
                                    edited = true;
                                    break;
                                case 3:
                                    removeAttack(scanner, pokedex.get(i));
                                    edited = true;
                                    break;

                            }


                        }
                    }

                    if (!edited) {
                        System.out.println("pokemon hittades inte");
                    }

                    break;






                case 6:
                    System.out.println("Välj typ att filtrera:");
                    System.out.println("1) FIRE");
                    System.out.println("2) WATER");
                    System.out.println("3) GRASS");
                    System.out.println("4) ELECTRIC");
                    System.out.println("5) NORMAL");

                    int filterChoice = readIntInRange(
                            scanner,
                            "Typ: ",
                            1,
                            5
                    );

                    Type filterType;

                    switch (filterChoice) {
                        case 1:
                            filterType = Type.FIRE;
                            break;
                        case 2:
                            filterType = Type.WATER;
                            break;
                        case 3:
                            filterType = Type.GRASS;
                            break;
                        case 4:
                            filterType = Type.ELECTRIC;
                            break;
                        default:
                            filterType = Type.NORMAL;
                            break;
                    }

                    boolean typeFound = false;

                    for (Pokemon pokemon : pokedex) {
                        if (pokemon.getType() == filterType) {
                            System.out.println(
                                    "Namn: " + pokemon.getName()
                                            + ", Typ: " + pokemon.getType()
                                            + ", HP: " + pokemon.getMaxHp()
                            );
                            typeFound = true;
                        }
                    }

                    if (!typeFound) {
                        System.out.println("Inga Pokemon med den typen hittades.");
                    }

                    break;

                case 7:
                    pokedex.sort((p1, p2) ->
                            p1.getName().compareToIgnoreCase(p2.getName())
                    );

                    System.out.println("Pokemon sorterade efer namn");
                    break;
                case 8:
                    if (pokedex.isEmpty()) {
                        System.out.println("Pokedex är tom.");
                        break;
                    }

                    int totalHp = 0;

                    for (Pokemon pokemon : pokedex) {
                        totalHp += pokemon.getMaxHp();
                    }

                    double averageHp = (double) totalHp / pokedex.size();

                    System.out.println("--- STATISTIK ---");
                    System.out.println("Antal Pokemon: " + pokedex.size());
                    System.out.println("Genomsnittlig HP: " + averageHp);

                    break;


                case 9:
                    resetSeedData(pokedex);
                    System.out.println("seed-data återställd!");
                    break;


                case 10:
                    saveToFile(pokedex);
                    break;

                case 11:
                    loadFromFile(pokedex);
                    break;

                case 12:
                    saveToFile(pokedex);
                    System.out.println("Avslutar");
                    return;



            }
        }
    }

    public static int readIntInRange(Scanner scanner, String prompt,
                                     int min, int max) {

        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value < min || value > max) {
                    System.out.printf(
                            "Ange ett tal mellan %d och %d.%n",
                            min, max
                    );
                    continue;
                }

                return value;

            } catch (NumberFormatException e) {
                System.out.println(
                        "Det där var inget heltal. Försök igen."
                );
            }
        }
    }
    public static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("fältet får inte vara tomt försök igen. ");
        }
    }
    public static void resetSeedData(ArrayList<Pokemon> pokedex) {
        pokedex.clear();

        Pokemon charmander = new Pokemon("Charmander", Type.FIRE, 100);
        charmander.addAttack(new Attack("Ember", 40, 100, Type.FIRE));
        pokedex.add(charmander);

        Pokemon squirtle = new Pokemon("Squirtle", Type.WATER, 100);
        squirtle.addAttack(new Attack("Water Gun", 40, 100, Type.WATER));
        pokedex.add(squirtle);

        Pokemon bulbasaur = new Pokemon("Bulbasaur", Type.GRASS, 100);
        bulbasaur.addAttack(new Attack("Vine Whip", 40, 100, Type.GRASS));
        pokedex.add(bulbasaur);

        Pokemon pikachu = new Pokemon("Pikachu", Type.ELECTRIC, 100);
        pikachu.addAttack(new Attack("Thunder Shock", 40, 100, Type.ELECTRIC));
        pokedex.add(pikachu);

        Pokemon eevee = new Pokemon("Eevee", Type.NORMAL, 100);
        eevee.addAttack(new Attack("Tackle", 40, 100, Type.NORMAL));
        pokedex.add(eevee);

        Pokemon growlithe = new Pokemon("Growlithe", Type.FIRE, 100);
        growlithe.addAttack(new Attack("Bite", 40, 100, Type.NORMAL));
        pokedex.add(growlithe);
    }
    public static void saveToFile(ArrayList<Pokemon> pokedex) {

        Path path = Path.of("pokedex.txt");

        StringBuilder data = new StringBuilder();

        for (Pokemon pokemon : pokedex) {

            data.append(pokemon.getName())
                    .append(";")
                    .append(pokemon.getType())
                    .append(";")
                    .append(pokemon.getMaxHp())
                    .append("\n");

            for (Attack attack : pokemon.getAttacks()) {
                data.append("ATTACK;")
                        .append(attack.getName())
                        .append(";")
                        .append(attack.getBaseDamage())
                        .append(";")
                        .append(attack.getAccuracy())
                        .append(";")
                        .append(attack.getType())
                        .append("\n");
            }
        }

        try {
            Files.writeString(path, data.toString());
            System.out.println("Pokedex sparad!");

        } catch (IOException e) {
            System.out.println("Kunde inte spara Pokedex.");
        }
    }
    public static void loadFromFile(ArrayList<Pokemon> pokedex) {

        Path path = Path.of("pokedex.txt");

        if (!Files.exists(path)) {
            System.out.println("Ingen sparad Pokedex hittades.");
            return;
        }

        try {
            ArrayList<String> lines =
                    new ArrayList<>(Files.readAllLines(path));

            pokedex.clear();

            Pokemon currentPokemon = null;

            for (String line : lines) {

                String[] parts = line.split(";");

                if (parts[0].equals("ATTACK")) {

                    String attackName = parts[1];
                    int damage = Integer.parseInt(parts[2]);
                    int accuracy = Integer.parseInt(parts[3]);
                    Type attackType = Type.valueOf(parts[4]);

                    Attack attack = new Attack(
                            attackName,
                            damage,
                            accuracy,
                            attackType
                    );

                    if (currentPokemon != null) {
                        currentPokemon.addAttack(attack);
                    }

                } else {

                    String name = parts[0];
                    Type type = Type.valueOf(parts[1]);
                    int hp = Integer.parseInt(parts[2]);

                    currentPokemon = new Pokemon(name, type, hp);
                    pokedex.add(currentPokemon);
                }
            }

            System.out.println("Pokedex laddad!");

        } catch (IOException e) {
            System.out.println("Kunde inte läsa Pokedex-filen.");

        } catch (RuntimeException e) {
            System.out.println("Pokedex-filen innehåller felaktig data.");
        }
    }



    public static void removeAttack(Scanner scanner, Pokemon pokemon) {

        if (pokemon.getAttacks().isEmpty()) {
            System.out.println("Pokemon har inga attacker.");
            return;
        }

        System.out.println("Välj attack att ta bort:");

        for (int i = 0; i < pokemon.getAttacks().size(); i++) {
            System.out.println(
                    (i + 1) + ") " + pokemon.getAttacks().get(i).getName()
            );
        }

        int choice = readIntInRange(
                scanner,
                "Val: ",
                1,
                pokemon.getAttacks().size()
        );

        pokemon.getAttacks().remove(choice - 1);

        System.out.println("Attack borttagen!");
    }
    public static void addAttack(Scanner scanner, Pokemon pokemon) {

        String attackName = readNonEmptyString(
                scanner,
                "Ange en ny attack: "
        );

        int damage = readIntInRange(
                scanner,
                "Ange attackens damage: ",
                1,
                1000
        );

        int accuracy = readIntInRange(
                scanner,
                "Ange attackens accuracy: ",
                1,
                100
        );

        System.out.println("Välj attackens typ:");
        System.out.println("1) FIRE");
        System.out.println("2) WATER");
        System.out.println("3) GRASS");
        System.out.println("4) ELECTRIC");
        System.out.println("5) NORMAL");

        int typeChoice = readIntInRange(
                scanner,
                "Typ: ",
                1,
                5
        );

        Type attackType;

        switch (typeChoice) {
            case 1:
                attackType = Type.FIRE;
                break;
            case 2:
                attackType = Type.WATER;
                break;
            case 3:
                attackType = Type.GRASS;
                break;
            case 4:
                attackType = Type.ELECTRIC;
                break;
            default:
                attackType = Type.NORMAL;
                break;
        }

        Attack attack = new Attack(
                attackName,
                damage,
                accuracy,
                attackType
        );

        pokemon.addAttack(attack);

        System.out.println("Attack tillagd!");
    }






}
