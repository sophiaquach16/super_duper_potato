package com.company;
import java.util.*;

public class Main2 {
    public static void main(String[] args) {
        Card[] deck = new Card[20];

        // populate the deck with 20 cards
        int counter = 0;
        for (int i=1; i<=10; i++) {
            String made_title = Integer.toString(i) + "Heart";
            deck[counter++] = new Card(made_title, "Normal card");
        }
        for (int i=1; i<=10; i++) {
            String made_title = Integer.toString(i) + "Spade";
            deck[counter++] = new Card(made_title, "Normal card");
        }

        System.out.println("All cards:");
        for (int i=0; i<counter; i++) {
            System.out.print("[ " + deck[i].getTitle() + " ] ");
        }
        deck = shuffle(deck);
        System.out.println("\nAll cards shuffled:");
        for (int i=0; i<counter; i++) {
            System.out.print("[ " + deck[i].getTitle() + " ] ");
        }

        int remaining = counter;
        Scanner keyboard = new Scanner(System.in);

        play(remaining, keyboard, deck); // main function for playing the game after initial setup

    }

    public static Card[] shuffle(Card[] deck) { // shuffle cards in place using Random
            Random rand = new Random();

            for (int i = 0; i < deck.length; i++) {
                int randomIndexToSwap = rand.nextInt(deck.length);
                Card temp = deck[randomIndexToSwap];
                deck[randomIndexToSwap] = deck[i];
                deck[i] = temp;
            }
            return deck;

    }

    public static void drawing(Card[] deck, int remaining, Scanner keyboard) { // draw 'x' cards
        int played = 0;
        Card[] discard = new Card[20];
        int discard_counter = 0;
        while (remaining > 0) {
            System.out.println("\nHow many cards would you like to draw?");
            int x = keyboard.nextInt();
            if (remaining < x) {
                System.out.println("Not enough cards in the pile");
            } else {
                Card[] hand = Arrays.copyOf(deck, deck.length);
                System.out.println("Hand played:");
                remaining = remaining - x;

                for (int i=remaining; i<hand.length-played; i++) { // print the ones left to the "end"
                    System.out.print("[ " + hand[i].getTitle() + " ] ");
                    discard[discard_counter++] = hand[i];
                }
                played = played + x;
                System.out.println("\nDiscard:");

                for (int i=0; i<discard_counter; i++) {
                    System.out.print("[ " + discard[i].getTitle() + " ] ");
                }
            }
        }
    }

    public static void play(int remaining, Scanner keyboard, Card[] deck) {
            drawing(deck, remaining, keyboard);

        System.out.print("\nWould you like to play again?\n");
        String decision = keyboard.next();
        if (decision.equals("yes")) {
            deck = shuffle(deck);
            play(20, keyboard, deck);
        }
        else if (decision.equals("no")) {
            System.out.println("\nThanks for playing!");
        }
    }
}
