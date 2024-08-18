import java.util.HashMap;
import java.util.*;

public class CardGSystem {

    public int isPair(int numOfSameCards, int numOfMatches, ArrayList<Card> cards) {
        Collections.sort(cards);
        Map<Integer, Integer> frequency = new HashMap<>();
        for (Card card : cards) {
            if (frequency.get(card.type) == null) {
                frequency.put(card.type, 1);
            } else {
                frequency.put(card.type, 1 + frequency.get(card.type));
            }
        }
        int g = 0, type = 0;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() >= numOfSameCards) {
                g++;
                type = entry.getKey();
            }
        }
        if (g == numOfMatches) {
            return type;
        } else {
            return -1;
        }
    }

    public boolean isTwoPair(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isPair(2, 2, cards) != -1;
    }
    public int isThreeOfKind(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isPair(3, 1, cards);
    }

    public int isStraight(ArrayList<Card> cards, boolean isSameSuitForALl) {
        Collections.sort(cards);
        for (int y = 0; y < cards.size() - 1; y++) {
            if (isSameSuitForALl) {
                if (cards.get(y).suit != cards.get(y + 1).suit || cards.get(y).type + 1 != cards.get(y + 1).type) {
                    return -1;
                }
            } else {
                if (cards.get(y).type + 1 != cards.get(y + 1).type) {
                    return -1;
                }
            }
        }
        return cards.get(0).type;
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

    public int isFourOfKind(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isPair(4, 1, cards);
    }

    public int isStraightFlush(ArrayList<Card> cards) {
        Collections.sort(cards);
        if(isFlush(cards)){
            return isStraight(cards, true);
        }
        return -1;
    }

    public boolean isRoyalFlush(ArrayList<Card> cards) {
        Collections.sort(cards);
        return isStraightFlush(cards) != -1 && (cards.get(0).type == 9);
    }

    public int valueOf(ArrayList<Card> cards) {
        if (isRoyalFlush(cards)) {
            return 74;
        } else if (isStraightFlush(cards) != -1) {
            return 64 + isStraightFlush(cards);
        } else if (isFourOfKind(cards) != -1) {
            return 51 + isFourOfKind(cards);
        } else if (isFullHouse(cards)) {
            return 51;
        } else if (isFlush(cards)) {
            return 50;
        } else if (isStraight(cards, false) != -1) {
            return 40 + isStraight(cards, false) ;
        } else if (isThreeOfKind(cards) != -1) {
            return 27 + isThreeOfKind(cards);
        } else if (isTwoPair(cards)) {
            return 27;
        } else if (isPair(2, 1, cards) != -1) {
            return 13 + isPair(2, 1, cards);
        }
        Collections.sort(cards);
        return cards.get(cards.size() - 1).type;
    }
    public ArrayList<Card> highestValueOf(ArrayList<Card> deck7, ArrayList<Card> variant) {
        if (variant.size() == 5){
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
                variants.add(highestValueOf(deck71, variant1));
            }
            DeckComparator comp = new DeckComparator();
            Collections.sort(variants, comp);
            return variants.get(0);
        }
    }





}
