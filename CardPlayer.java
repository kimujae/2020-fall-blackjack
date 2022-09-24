package BlackJack;

public class CardPlayer {

	public Card[] hand;
    private int sum;
    private int having_cards_num;
    private int number_of_ace;

    /** Constructor - 손에 들고 있는 카드 장수와, 등급 합 초기화 */
	public CardPlayer() {
	having_cards_num =0;	
	hand = new Card[11];
	sum =0;
	number_of_ace = 0;
	}
	
	/** receiveCard - 카드를 받는 메소드
	 * @param n - 카드를 받은 순서(해당 카드가 첫번째로 받은 카드이면 n==1)
	 * @param c - 새로 받은 카드
	 * return - 카드 합이 21을 넘으면 bust로 false, 성공적으로 받으면 true */
	public boolean receiveCard(int n, Card c) {
		if(sum > 21) 
			return false;
		
		else if(c.getRank()==1 && sum <11) {
				hand[n] = c;
				number_of_ace ++;
				sum =sum +11;
				having_cards_num++;
			}
		else {
			hand[n] = c;
			sum = sum + c.getRank();
			having_cards_num++;
		    
		}
		while(sum > 21 && number_of_ace>0) {
			sum -=10;
			number_of_ace -=1;
			
		}
		return true;
	}
	
	/** showHand - 손에 들고 있는 카드 보여주기
	 * return - 들고 있는 카드의 무늬와 등급 */
	public String showHand() {
		return "#" + this.getHavingCardsNum() + " = " + hand[this.getHavingCardsNum()-1].getSuit() + " " +hand[this.getHavingCardsNum()-1].getRank()+"\n" ;
		}
	
	/** getSumCard - 들고있는 카드의 등급 총합을 반환 */
	public int getSumCard() { return sum; }
	
	/** getHavingCardNum - 들고 있는 카드 장 수를 반환 */
	public int getHavingCardsNum() { return having_cards_num; }
	
	/** hand - 카드 배열에서 특정 카드 한장을 반환
	 * @param i - 반환하고싶은 카드의 인덱스 */
	public Card hand(int i) { return hand[i]; }
	
	/** setFresh - 플레이어가 들고 있는 카드의 상태를 초기화 */
	public void setFresh() {
		having_cards_num = 0;	
		hand = new Card[11];
		sum = 0;
		number_of_ace = 0;  
		}
}
