import java.util.HashMap;
import java.util.*;

public class CardGSystem {

    public boolean isPair(int numOfSameCards, int numOfMatches, ArrayList<Card> cards) {
        Collections.sort(cards);
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Card card : cards) {
            if (frequency.get(card.type) == null) {
                frequency.put(card.type, 1);
            } else {
                frequency.put(card.type, 1 + frequency.get(card.type));
            }
        }
        int g = 0;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() >= numOfSameCards) {
                g++;
            }
        }
        if (g == numOfMatches) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTwoPair(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isPair(2, 2, cards);
    }

    public boolean isThreeOfKind(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isPair(3, 1, cards);
    }

    public boolean isStraight(ArrayList<Card> cards, boolean isSameSuitForALl) {
        Collections.sort(cards);
        for (int y = 0; y < cards.size() - 1; y++) {
            if (isSameSuitForALl) {
                if (cards.get(y).suit != cards.get(y + 1).suit || cards.get(y).type + 1 != cards.get(y + 1).type) {
                    return false;
                }
            } else {
                if (cards.get(y).type + 1 != cards.get(y + 1).type) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isFlush(ArrayList<Card> cards) {
        Collections.sort(cards);
        int suit = cards.get(0).suit;
        for (int y = 0; y < cards.size(); y++) {
            if (cards.get(y).suit != suit) {
                return false;
            }
        }
        return true;
    }

    public boolean isFullHouse(ArrayList<Card> cards) {
        Collections.sort(cards);
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Card card : cards) {
            if (frequency.get(card.type) == null) {
                frequency.put(card.type, 1);
            } else {
                frequency.put(card.type, 1 + frequency.get(card.type));
            }
        }
        return frequency.size() == 2 && (frequency.get(cards.get(0)) == 2 || frequency.get(cards.get(0)) == 3);
    }

    public boolean isFourOfKind(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isPair(4, 1, cards);
    }

    public boolean isStraightFlush(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isStraight(cards, true) && isFlush(cards);
    }

    public boolean isRoyalFlush(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isStraightFlush(cards) && (cards.get(0).type == 9);
    }

    public int valueOf(ArrayList<Card> cards) {
        if (isRoyalFlush(cards)) {
            return 22;
        } else if (isStraightFlush(cards)) {
            return 21;
        } else if (isFourOfKind(cards)) {
            return 20;
        } else if (isFullHouse(cards)) {
            return 19;
        } else if (isFlush(cards)) {
            return 18;
        } else if (isStraight(cards, false)) {
            return 17;
        } else if (isThreeOfKind(cards)) {
            return 16;
        } else if (isTwoPair(cards)) {
            return 15;
        } else if (isPair(2, 1, cards)) {
            return 14;
        }
        Collections.sort(cards);
        return cards.get(cards.size() - 1).type;
    }

    public ArrayList<Card> highestValueOf(ArrayList<Card> deck7, ArrayList<Card> variant, int g) {
        if (variant.size() == 5) {
            return variant;
        } else {
            ArrayList<ArrayList<Card>> variants = new ArrayList<>();
            for (int y = 0; y < deck7.size(); y++) {
                ArrayList<Card> variant1 = new ArrayList<>();
                ArrayList<Card> deck71 = new ArrayList<>();
                for(Card card : variant){
                    variant1.add(card);
                }
                for(Card card : deck7){
                    deck71.add(card);
                }
                variant1.add(deck71.get(y));
                deck71.remove(y);
                variants.add(highestValueOf(deck71, variant1, g + 1));
            }
            DeckComparator comp = new DeckComparator();
            Collections.sort(variants, comp);
            return variants.get(0);
        }
    }
}
