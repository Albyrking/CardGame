import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CardG {
    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Card> communityCards = new ArrayList<>();
    ArrayList<Card> cards = new ArrayList<>();

    public CardG(ArrayList<Player> players) {
        this.players = players;
        //Create all cards
        for (int y = 1; y <= CardTypes.values().length; y++) {
            for (int u = 1; u <= CardSuits.values().length; u++) {
                cards.add(new Card(y, u));
            }
        }
        System.out.println();
        Collections.shuffle(cards);

        //Determine community cards
        for (int y = 0; y < 5; y++) {
            communityCards.add(cards.get(0));
            cards.remove(0);
        }

        //Spread cards to players
        for (int y = 0; y < players.size() * 2; y++) {
            players.get(y % players.size()).addCard(cards.get(0));
            cards.remove(0);
        }

    }

    public ArrayList<Player> go() {
        showCards(players);
        showGame();
        for(int y = 0; y < players.size() ; y++){
            CardGSystem system = new CardGSystem();
            ArrayList<Card> emptyArray = new ArrayList<>();
            for(int u = 0; u < 5 ; u++){
                players.get(y).addCard(communityCards.get(u));
            }
            players.get(y).setDeck(system.highestValueOf(players.get(y).deck, emptyArray));
        }
        System.out.println();
        PlayersDeckComparator comp = new PlayersDeckComparator();
        Collections.sort(players, comp);
        return players;
    }

    public void showCards(ArrayList<Player> players) {
        System.out.println("Cards of players: ");
            for (int y = 0; y < players.size() ; y++) {
                System.out.println(players.get(y).name + "'s deck :");
                for (Card card : players.get(y).deck) {
                    System.out.print(card.name + "   ");
                }
                System.out.println("\n");
            }
        System.out.println();
    }

    public void showGame() {
        System.out.print("5 Cards : ");
        for (Card card : communityCards) {
            System.out.print(card.name + "   ");
        }
        System.out.println();
    }


}
