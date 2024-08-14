import java.util.ArrayList;
import java.util.*;

public class Player {
    String name ;
    int score, pScore;
    ArrayList<Card> deck = new ArrayList<>();
    public Player(String name){
        this.name = name;
        score = 0;
    }
    public void addCard(Card card){
        deck.add(card);
    }
    public void setDeck(ArrayList<Card> deck){
        this.deck = deck;
    }
public void addToScore(int point){
        pScore = score;
        score+=point;
}
public void clearDeck(){
        deck.clear();
}

}
