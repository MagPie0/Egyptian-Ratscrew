import java.util.LinkedList;
import java.util.Random;

public class Game {
    private LinkedList<Player> m_players;
    private static LinkedList<Card> m_pile = new LinkedList<>();
    private Dealer m_dealer;
    private static String[] m_patterns = {"top bottom", "sandwich", "doubles"};


    public Game(){
        m_players = new LinkedList<>();
        int amntCards = 52/2;
        for (int i = 0; i < 2; ++i){
            m_players.add(new Player(i, m_dealer.deals(amntCards), m_patterns[randomPattern()])); 
        }
        // m_pile = new LinkedList<>();
    }

    public Game(int numPlayers){
        m_dealer = new Dealer();
        m_players = new LinkedList<>();
        /*
         * Code received from Dr Elia
         * START
         */
        int amntCards = 52/numPlayers;
        for (int i = 0; i < numPlayers; ++i){
            m_players.add(new Player(i, m_dealer.deals(amntCards), m_patterns[randomPattern()])); //take in the number of players
        }
        /* END */
    }

    public Player getPlayer(int i){
        return m_players.get(i);
    }
    
    public int randomPattern(){
        Random r = new Random();
        int indexPicked = r.nextInt(m_patterns.length);
        return indexPicked;
    }

    public LinkedList<Player> getPlayers(){
        return m_players;
    }

    public LinkedList<Card> getPile(){
        return m_pile;
    }

    public Dealer getDealer(){
        return m_dealer;
    }

    public String[] getPattern(){
        return m_patterns;
    }

    public static boolean whichPattern(LinkedList<Card> pile){ //idk if this needs to be public
        if (isDoubles(pile)) { 
            m_patterns = new String[]{"doubles"};
            return true;
        }
        if (isTopBottom(pile)) { 
            m_patterns = new String[]{"top bottom"}; 
            return true;
        }
        if (isSandwich(pile)) { 
            m_patterns = new String[]{"sandwich"};
            return true;
        } else {
            return false;
        }
    }

    public Player nextPlayer(int currentPlayerNum){
        if (m_players.get(currentPlayerNum).equals(m_players.getLast())) {
            return m_players.getFirst();
        } 
        return m_players.get(++currentPlayerNum);
    }



    public int Play(){
        Player currentPlayer = m_players.get(0);
        while (m_players.size() > 1) {
                int chances = 0;
                m_pile.add(currentPlayer.playCard()); //each player plays a card
                System.out.println(m_pile.toString());
                System.out.println("\n");
                chances = 0;
                chances = isFaceCardPlayed(m_pile); //checks each time a card is played if it is a face card
                while (chances >= 1) {
                    System.out.println("Player " + currentPlayer.getPlayerNum() + " has played a face card!");
                    currentPlayer = nextPlayer(currentPlayer.getPlayerNum());
                    System.out.println("Player " + currentPlayer.getPlayerNum() + " has " + chances + " chances to play another face card\n");                
                    if (didFaceCardPlayAgain(m_pile)) {
                        m_pile.add(currentPlayer.playCard()); //have the new player play the amount of chances
                        System.out.println(m_pile.toString());
                        currentPlayer.slapPile(m_pile); 
                        System.out.println("Player " + currentPlayer.getPlayerNum() + " has slapped!!\n");
                        break;
                    } else {
                        if (chances == 0) {
                            break;
                        } 
                        m_pile.add(currentPlayer.playCard()); //have the new player play the amount of chances
                        System.out.println(m_pile.toString());
                        System.out.println("Player " + currentPlayer.getPlayerNum() + " has " + (--chances) + " chances left!");
                    }
                    
                }
                
                // if (whichPattern(m_pile)) { //if the player gets a pattern
                //     if (m_patterns.toString().equals(m_players.get(i).getPattern())) { //is the players pattern equal to the pattern in the pile
                //         //m_players.get(i).Slaps(m_pile);
                //         m_players.get(i).slapPile(m_pile);
                //         System.out.println("Player " + i + " has slapped with the pattern " + getPattern() + "\n");
                //     }
                // }
                if (isOutOfGame(currentPlayer)) { //if player has no cards then they are removed from the list
                    System.out.println("Player " + currentPlayer.getPlayerNum() + " is out\n");
                    m_players.remove(currentPlayer);
                }
            }
            if (m_players.size() == 1) { //if the linked list has 1 person left, then they are returned as the winner
                Player winner = m_players.get(0);
                System.out.println("Player " + winner.getPlayerNum() + " has won!!!!");
                return m_players.size();
            }
            currentPlayer = nextPlayer(currentPlayer.getPlayerNum());
            return 2;
    }
    
    private boolean didFaceCardPlayAgain(LinkedList<Card> pile){
        Card topCard = pile.getLast();
        if (topCard.getRank() == Card.ACE) { //make sure it only checks top of card
                return true;
            } else if (topCard.getRank() == Card.KING) {
                return true;
            } else if (topCard.getRank() == Card.QUEEN) {
                return true;
            } else if (topCard.getRank() == Card.JACK) {
                return true;
            } else {
                return false;
            }
    }


    private boolean isOutOfGame(Player player){
        if (player.getHand().size() == 0) {
            return true;
        } else {
            return false;
        }
    }
 
    private static boolean isSandwich(LinkedList<Card> pile){
        int index = pile.size();
        Card breadCard = pile.getLast();
        Card bottomBreadCard = pile.get(index - 2);

        if (breadCard.equals(bottomBreadCard)) {
            return true;
        }
        return false;
    }
    
    private static boolean isDoubles(LinkedList<Card> pile){
        int index = pile.size();
        Card firstCard = pile.getLast();
        Card secondCard = pile.get(index - 1);

        if (firstCard.equals(secondCard)) {
            return true;
        }
        return false;
    }

    private static boolean isTopBottom(LinkedList<Card> pile){
        Card firstCard = pile.getFirst();
        Card lastCard = pile.getLast();

        if (firstCard.equals(lastCard)) {
            return true;
        }
        return false;
    }
    /*GOT THE CONDITIONALS FROM CHATGBT
     * PROMPT:
     * why does my code not work 
        private int isFaceCardPlayed(LinkedList<Card> pile){
        if (pile.getfirst().contains(Card.ACE)) { //make sure it only checks top of card
            return 4;
        } else if (pile.contains(Card.KING)) {
            return 3;
        } else if (pile.contains(Card.QUEEN)) {
            return 2;
        } else if (pile.contains(Card.JACK)) {
            return 1;
        } else {
            return 0;
        }
    }
     */
    private int isFaceCardPlayed(LinkedList<Card> pile){
        if (!pile.isEmpty()) {
            Card topCard = pile.getLast();
        
            if (topCard.getRank() == Card.ACE) { //make sure it only checks top of card
                return 4;
            } else if (topCard.getRank() == Card.KING) {
                return 3;
            } else if (topCard.getRank() == Card.QUEEN) {
                return 2;
            } else if (topCard.getRank() == Card.JACK) {
                return 1;
            } else {
                return 0;
            }
        }
        return 0;
    }
    /* End */
}
