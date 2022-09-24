package BlackJack;

public class Visitor extends CardPlayer implements CardPlayerBehavior {

	private int chip;
	private boolean receive_chip;
	/** Constructor - �տ� ��� �ִ� ī�� �ʱ�ȭ */
	public Visitor() {
		hand = new Card[11];
		chip = 0;
		receive_chip=false;
	}
	/** receiveACard - ī�� ������ ���θ� ��ȯ (21�ʰ� �� ī�带 ���� �� ����) */
	public boolean wantsACard() {
		if (this.getSumCard() < 21)
			return true;
		else 
			return false;
	}
	
	/** showHand - �տ� ��� �ִ� ī���� ���¸� ��ȯ */
	public String showHand() {
		if(this.getSumCard() > 21) 
			return "bust!";
	
		else if (this.getSumCard() == 21)
			return "black jack!!";
			
		else if(this.getHavingCardsNum() == 0)
			return "����ִ� ī�尡 �����ϴ�."; 
		
		else 
			return super.showHand() +"  �հ� :  "+ this.getSumCard();
	}
	
	/** setChip - �մ��� Ĩ ���� ����
	 * @param n - �մ��� �̱�� 1, �������� �̱�� 2, �մ��� ���� -1 */
	public void setChip(int n) { 
		if(!receive_chip) {
		chip += n; 
		receive_chip = true;	
		}
	}

	/** getChip - �մ��� Ĩ ������ ��ȯ */
	public int getChip() { return chip;  }
	
	//chip�� �޾Ҵ��� ����
	public boolean receiveChip() {
		return receive_chip;
	}
	
	// �θ� �޼ҵ忡 ���� receive chip���� �ʱ�ȭ(���� ���ν��۵Ǹ� �ʱ�ȭ)
	public void setFresh() {
		super.setFresh();
		receive_chip = false;
	}
}
