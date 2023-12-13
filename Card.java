
public class Card {
    public static final int HEARTS = 0;
    public static final int SPADES = 1;
    public static final int CLUBS = 2;
    public static final int DIAMONDS = 3;

    public static final int JACK = 11;
    public static final int QUEEN = 12;
    public static final int KING = 13;
    public static final int ACE = 14;

    private int m_rank; 
    private int m_suit; 


    public Card(){
        m_rank = ACE;
        m_suit = SPADES;
    }

    public Card(int rank, int suit){
        m_rank = rank;
        m_suit = suit;
    }

    public void copyCard(Card c){
        this.m_rank = c.m_rank;
        this.m_suit = c.m_suit;
    }

    public String toString(){
        String s = "";
        /* BEGIN CODE FROM CHAT GPT: PROMPT ASKED: "Why is my code not working 
        public String toString(){
        String s = "";
        
        if ((m_rank >= 0 && m_rank <= 10) && m_suit == 0) {
            s += m_rank + " OF HEARTS\n";
        } if (m_rank == 14 && m_suit == 1) {
            s += "ACE OF SPADES\n";
        } if (m_rank == 14 && m_suit == 0) {
            s += "ACE OF HEARTS\n";
        } if (m_rank == 14 && m_suit == 2) {
            s += "ACE OF CLUBS\n";
        } if (m_rank == 14 && m_suit == 3) {
            s += "ACE OF DIAMONDS\n";
        } if ((m_rank >= 0 && m_rank <= 10) && m_suit == 1) {
            s += m_rank + " OF SPADES\n";
        } if ((m_rank >= 0 && m_rank <= 10) && m_suit == 2) {
            s += m_rank + " OF CLUBS\n";
        } if ((m_rank >= 0 && m_rank <= 10) && m_suit == 0) {
            s += m_rank + " OF DIAMONDS\n";
        } else {
            s += "didn't work";
        }
    
        return s;
        }" */
        if (m_rank >= 1 && m_rank <= 10) {
            s += m_rank + " OF ";
        } else if (m_rank == 11) {
            s += "JACK OF ";
        } else if (m_rank == 12) {
            s += "QUEEN OF ";
        } else if (m_rank == 13) {
            s += "KING OF ";
        } else if (m_rank == 14) {
            s += "ACE OF ";
        }

        if (m_rank >= 1) {
            switch (m_suit) {
            case 0:
                s += "HEARTS\n";
                break;
            case 1:
                s += "SPADES\n";  
                break;
            case 2:
                s += "CLUBS\n";
                break;
            case 3:
                s += "DIAMONDS\n";
                break;
            default:
                break;
        }
        }
        /* END OF CODE FROM CHAT GPT */
        
        return s;
    }

    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        
        if (!(o instanceof Card)) {
            return false;
        }

        Card m = (Card) o;

        return ((this.m_rank == m.m_rank) &&
            (this.m_suit == m.m_suit));
    }

    public void setRank(int cardRank){
        m_rank = cardRank;
    }

    public void setSuit(int cardSuit){
        m_suit = cardSuit;
    }
    
    public int getRank(){
        return m_rank;
    }

    public int getSuit(){
        return m_suit;
    }
}
