public class Card implements Comparable<Card>{
    int type, suit;
    String name;
    int order;
    public Card(int type, int suit) {
        this.type = type;
        this.suit = suit;
        CardTypes[] types = CardTypes.values();
        CardSuits[] suits = CardSuits.values();
        name = types[type - 1] + " " + suits[suit - 1];
    }
    @Override
    public int compareTo(Card o) {
        if (this.type > o.type) {
            return 1;
        } else if (this.type == o.type) {
            return 0;
        }
        return -1;
    }
}
