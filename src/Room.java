import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Room {
    public void startGame(){
    Scanner in = new Scanner(System.in);
    try {
        System.out.println("CARD GAME");
        ArrayList<Player> players = new ArrayList<>();
        //Scanner number of players
        System.out.print("Enter number of players: ");
        boolean isEnoughVotes = true;
        int playerNum = in.nextInt();
        //Scanner names simultaneously creating players
        for (int y = 1; y <= playerNum; y++) {
            System.out.print("Enter name for player number " + y + ": " );
            players.add(new Player(in.next()));
        }
        while (isEnoughVotes) {
            CardG f = new CardG(players);
            CardGSystem s = new CardGSystem();
            ArrayList<Player> listOfWinners = f.go();
            int maxPoint = playerNum + 1, maxValue = s.valueOf(listOfWinners.get(0).deck);
            for(int y = 1 ; y <= 30; y++){
                if(y < 25) {
                    Thread.sleep(25);
                }else{
                    Thread.sleep(25*y);
                }
                System.out.print("/");
            }
            Thread.sleep(1000);
            for(int y =1 ; y <= 70 ; y++ ){
                Thread.sleep(7);
                System.out.print("/");
            }
            System.out.println();
            System.out.println("List: ");
            for (int y = 1; y < listOfWinners.size() + 1; y++) {
                System.out.print(y + " - \"" + listOfWinners.get(y - 1).name + "\" with deck of : {");
                for (Card card : listOfWinners.get(y - 1).deck) {
                    System.out.print(card.name + "  ");
                }
                SetTypes[] setTypes = SetTypes.values();
                int value = s.valueOf(listOfWinners.get(y - 1).deck);
                System.out.println("} = SET: " + setTypes[value - 1] + "(value: " + value + ")");
                if (s.valueOf(listOfWinners.get(y - 1).deck) != maxValue) {
                    maxPoint--;
                    maxValue = s.valueOf(listOfWinners.get(y - 1).deck);
                }
                listOfWinners.get(y - 1).addToScore(maxPoint);
            }
            ScoreCmparator comp = new ScoreCmparator();
            Collections.sort(listOfWinners, comp);
            System.out.println();

            for(int y =0 ; y < listOfWinners.get(0).name.length() ; y++){
                System.out.print(" ");

            }
            System.out.println(" Scores : ");
            for (Player player : listOfWinners) {
                System.out.println(player.name + ": " + player.pScore + "  -->  " + player.score);
            }
            System.out.println();
            int votes = votes(players);
            System.out.println("Votes : " + votes + " for Yes and " + (playerNum - votes) + " for No");
            if (votes == playerNum) {
                isEnoughVotes = true;
            } else {
                isEnoughVotes = false;
            }
            System.out.println("|\n|\n\\/");
            if (isEnoughVotes) {
                System.out.println("Game will keep going\n\n\n");
                for(int y = 1 ; y <= 70 ; y++){
                    Thread.sleep(y);
                    System.out.print("/");
                }
                Thread.sleep(2000);
                System.out.println("////");
            }
            for (int y = 0; y < players.size(); y++) {
                players.get(y).clearDeck();
            }
        }
        throw new RuntimeException();
    }catch (InputMismatchException e){
        System.out.println("It's not a number. Try again");
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }


    }

    public int votes(ArrayList<Player> players) {
        Scanner in = new Scanner(System.in);
        System.out.println("PLay again?(Yes/No)");
        int sum = 0;
        for (Player player : players) {
            System.out.print(player.name + "' response: ");
            if (in.next().equals("Yes")) {
                sum++;
            }
        }
        return sum;
    }
}
