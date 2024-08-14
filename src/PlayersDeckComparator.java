import java.util.Comparator;

public class PlayersDeckComparator implements Comparator<Player> {
    CardGSystem system = new CardGSystem();
    @Override
    public int compare(Player o1, Player o2) {
        if(system.valueOf(o1.deck) > system.valueOf(o2.deck)){
            return -1;
        }else if(system.valueOf(o1.deck) == system.valueOf(o2.deck)){
            return 0;
        }
        return 1;
    }
}
