import java.util.ArrayList;
import java.util.Comparator;

public class DeckComparator implements Comparator<ArrayList<Card>> {
    @Override
    public int compare(ArrayList<Card> o1, ArrayList<Card> o2) {
        CardGSystem system = new CardGSystem();
        if(system.valueOf(o1) > system.valueOf(o2)){
            return -1;
        }else if(system.valueOf(o1) == system.valueOf(o2)){
            return 0;
        }
        return 1;
    }
}
