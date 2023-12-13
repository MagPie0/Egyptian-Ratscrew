
import java.util.LinkedList;

public class Dealer {
    private Deck m_deck;

    public Dealer(){
        m_deck = new Deck();
    }

    public LinkedList<Card> deals(int n){
        LinkedList<Card> deals = new LinkedList<Card>();

         if (m_deck.getSize() >= n) {
            for (int j = 0; j < n; j++) {
                deals.add(m_deck.Deal());
            }
            return deals;
        } else {
            return deals;
        }
    
    }

    public int getSize(){
        return m_deck.getSize();
    }

    public String toString(){
        String s = "";
        s += m_deck.toString();

        return s;
    }
}

