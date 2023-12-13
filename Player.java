
import java.util.LinkedList;

public class Player {
    private int m_playerNum;
    private LinkedList<Card> m_hand;
    private String m_pattern;

    public Player(int playerNum, String pattern){
        m_playerNum = playerNum;
        m_hand = new LinkedList<Card>(); 
        m_pattern = pattern;
    }

    public Player(int playerNum, LinkedList<Card> hand, String pattern){
        m_playerNum = playerNum;
        // m_hand = new LinkedList<>();
        m_hand = hand; 
        m_pattern = pattern;
    }

    public Card playCard(){
        return m_hand.removeFirst(); 
    }

    public boolean Slaps(LinkedList<Card> pile){
        if (m_pattern.equals("top bottom")) {
            return true;
        } else if (m_pattern.equals("sandwich")) {
            return true;
        } else if (m_pattern.equals("doubles")) {
            return true;
        } else {
            return false;
        }         
    }

    public int getPlayerNum(){
        return m_playerNum;
    }

    public LinkedList<Card> getHand(){
        return m_hand;
    }

    public String getPattern(){
        return m_pattern;
    }

    public String toString(){
        String s = "";
        s += "Player number: " + m_playerNum;
        s += "\nPattern: " + m_pattern;
        s += "\nPlayer's hand: " + m_hand.toString();
        return s;
    }

    public void slapPile(LinkedList<Card> pile){
        pile.addAll(m_hand);
        pile.clear();
    }
}
