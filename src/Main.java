import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            while (true) {
                Room room = new Room();
                room.startGame();
            }
        }catch (RuntimeException e ){
            System.out.println("Game Over");
        }
    }
}