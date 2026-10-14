package org.Inlemning1;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Battle {
    private static int wins = 0;
    private static int losses = 0;

    public void start(List<Pokemon> pokedex) {
        System.out.println("En Pokémon-strid startar!");
        System.out.println("Antal Pokémon att välja mellan: " + pokedex.size());
        if (pokedex.isEmpty()) {
            System.out.println("Du har inga Pokémon. Lägg till en Pokémon först!");
            return;
        }
        System.out.println("\nVälj din Pokémon:");

        for (int i = 0; i < pokedex.size(); i++) {
            Pokemon pokemon = pokedex.get(i);

            System.out.println((i + 1) + ") " + pokemon.getName()
                    + " - HP: " + pokemon.getCurrentHp()
                    + "/" + pokemon.getMaxHp());
        }

        Scanner scanner = new Scanner(System.in);

        int choice = Main.readIntInRange(
                scanner,
                "Välj Pokémon: ",
                1,
                pokedex.size()
        );

        Pokemon selectedPokemon = pokedex.get(choice - 1);

        System.out.println("Du valde " + selectedPokemon.getName() + "!");

        if (selectedPokemon.getAttacks().isEmpty()) {
            System.out.println("Den här Pokémon har inga attacker och kan inte strida.");
            return;
        }

        Random random = new Random();
        Pokemon wildPokemon = pokedex.get(random.nextInt(pokedex.size()));

        Pokemon cpuPokemon = new Pokemon(
                wildPokemon.getName(),
                wildPokemon.getType(),
                wildPokemon.getMaxHp()

        );
        for (Attack attack : wildPokemon.getAttacks()) {
            cpuPokemon.addAttack(attack);
        }
        if (cpuPokemon.getAttacks().isEmpty()) {
            System.out.println("CPU:s Pokémon har inga attacker. Striden avbryts.");
            return;
        }
        while (!selectedPokemon.isFainted() && !cpuPokemon.isFainted()) {
            System.out.println("\nVälj attack:");

            for (int j = 0; j < selectedPokemon.getAttacks().size(); j++) {
                Attack attack = selectedPokemon.getAttacks().get(j);

                System.out.println((j + 1) + ") " + attack.getName()
                        + " - Skada: " + attack.getBaseDamage()
                        + " - Träffsäkerhet: " + attack.getAccuracy() + "%");
            }

            int attackChoice = Main.readIntInRange(
                    scanner,
                    "Välj attacknummer: ",
                    1,
                    selectedPokemon.getAttacks().size()
            );

            Attack chosenAttack = selectedPokemon.getAttacks().get(attackChoice - 1);

            System.out.println("Du valde attacken " + chosenAttack.getName() + "!");

            if (random.nextInt(100) < chosenAttack.getAccuracy()) {
                double multiplier = getTypeMultiplier(
                        chosenAttack.getType(),
                        cpuPokemon.getType()
                );

                double randomFactor = 0.85 + random.nextDouble() * 0.15;

                int damage = (int) (chosenAttack.getBaseDamage()
                        * multiplier * randomFactor);
                cpuPokemon.takeDamage(damage);

                System.out.println(cpuPokemon.getName() + " tog " + damage + " skada!");
            } else {
                System.out.println("Din attack missade!");
            }

            System.out.println("CPU har " + cpuPokemon.getCurrentHp()
                    + "/" + cpuPokemon.getMaxHp() + " HP kvar.");

            System.out.println("CPU valde" + cpuPokemon.getName() + "!");

            if (cpuPokemon.isFainted()) {
                System.out.println("CPU:s Pokémon har svimmat!");
                System.out.println("Du vann striden!");
                return;
            }

            Attack cpuAttack = cpuPokemon.getAttacks().get(
                    random.nextInt(cpuPokemon.getAttacks().size())
            );

            System.out.println("CPU använder " + cpuAttack.getName() + "!");

            if (random.nextInt(100) < cpuAttack.getAccuracy()) {
                double cpuMultiplier = getTypeMultiplier(
                        cpuAttack.getType(),
                        selectedPokemon.getType()
                );

                double cpuRandomFactor = 0.85 + random.nextDouble() * 0.15;

                int cpuDamage = (int) (cpuAttack.getBaseDamage()
                        * cpuMultiplier * cpuRandomFactor);
                selectedPokemon.takeDamage(cpuDamage);

                System.out.println(selectedPokemon.getName()
                        + " tog " + cpuDamage + " skada!");
            } else {
                System.out.println("CPU:s attack missade!");
            }

            System.out.println("Du har " + selectedPokemon.getCurrentHp()
                    + "/" + selectedPokemon.getMaxHp() + " HP kvar.");
            if (selectedPokemon.isFainted()) {
                System.out.println("Din Pokémon har svimmat!");
                System.out.println("Du förlorade striden!");
                return;
            }
        }
    }

        private double getTypeMultiplier(Type attackType, Type defenderType) {
            if (attackType == Type.FIRE && defenderType == Type.GRASS) {
                return 2.0;
            }
            if (attackType == Type.WATER && defenderType == Type.FIRE) {
                return 2.0;
            }
            if (attackType == Type.GRASS && defenderType == Type.WATER) {
                return 2.0;
            }
            if (attackType == Type.ELECTRIC && defenderType == Type.WATER) {
                return 2.0;
            }
            if (attackType == Type.FIRE && defenderType == Type.WATER) {
                return 0.5;
            }
            if (attackType == Type.WATER && defenderType == Type.GRASS) {
                return 0.5;
            }
            if (attackType == Type.GRASS && defenderType == Type.FIRE) {
                return 0.5;
            }

            return 1.0;
        }

    }







