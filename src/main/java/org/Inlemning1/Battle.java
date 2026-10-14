package org.Inlemning1;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Battle {
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
            Scanner scanner = new Scanner(System.in);

            int choice = Main.readIntInRange(
                    scanner,
                    "Välj Pokémon: ",
                    1,
                    pokedex.size()
            );

            Pokemon selectedPokemon = pokedex.get(choice - 1);

            System.out.println("Du valde " + selectedPokemon.getName() + "!");

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

            System.out.println("CPU valde" + cpuPokemon.getName()+ "!");
        }
    }


    }

