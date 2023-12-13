
import java.util.LinkedList;
import java.util.Random;

public class Deck {
    protected LinkedList<Card> m_cards ;


    public Deck(){
        m_cards  = new LinkedList<Card>();

        for (int i = 0; i <= 14; i++) {
            m_cards.add(new Card(i, Card.HEARTS));
            m_cards.add(new Card(i, Card.DIAMONDS));
            m_cards.add(new Card(i, Card.SPADES));
            m_cards.add(new Card(i, Card.CLUBS));
        }
        
    }

    public Deck(Deck c){
        this.m_cards.equals(c.m_cards);
    }

    public String toString(){
        String s = "";
    
        for (int i = 0; i < m_cards.size(); i++) {
            s += m_cards.get(i).toString();
        }
        return s;
    }

    public int getSize(){
        return m_cards.size();
    }

    public Card Deal(){
        Random r = new Random();
        int indexPicked = r.nextInt(m_cards.size());

        Card cardPicked = m_cards.get(indexPicked);
        m_cards.remove(indexPicked);
 
        return cardPicked; 
    }
}
