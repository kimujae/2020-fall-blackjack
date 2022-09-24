package BlackJack;

public class Card {


	public static final String SPADES = "Spade";
	public static final String HEARTS = "Heart";
	public static final String DIAMONDS = "Diamond";
	public static final String CLUBS = "Club";
	
	public static final int ACE = 1;
	public static final int JACK = 10;
	public static final int QUEEN = 10;
	public static final int KING = 10;
	
	public static final int SIZE_OF_ONE_SUIT = 13;
	
	private String suit;
	private int rank;

	/** Constructor - 카드 무늬와 등급 설정
	 * @param s - 카드 무늬
	 * @param r -카드 등급 */
	public Card(String s, int r) { 
		suit = s;
		rank = r;
	}

	/** getSuit - 카드 무늬 반환 */
	public String getSuit() { 
		return suit; 
	}

	/** getCount - 카드 등급 반환 */
	public int getRank() { 
		return rank; 
	}
}
