package BlackJack;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;

public class GameFrame extends JFrame {
	
	 private Dealer dealer;
	 private Visitor visitor;
	 private GameButton[][] button;
	 private JLabel[][] dealer_deck;
	 private JLabel[][] visitor_deck;
	 private JLabel label = new JLabel("");
	 private JLabel label2 = new JLabel("");
	 private JLabel welcome_label = new JLabel("");
	 private boolean getresult;
	
	
	 /** Constructor - 프레임, 패널, 버튼 생성
	  * @param d - 딜러 객체
	  * @param p - 플레이어 객체 */
	 public GameFrame(Dealer d, Visitor p) {
	 	// 딜러, 플레이어 객체 호출, 카드 배열 생성, 게임 결과 초기화
	 	dealer = d;
	 	visitor = p;
	 	getresult = false;
	    // 버튼 객체 초기 설정
	 	button = new GameButton[1][5];
	 	button[0][0] = new GameButton(dealer, visitor, this); button[0][0].setText("START");
	 	button[0][1] = new GameButton(dealer, visitor, this); button[0][1].setText("HIT");
	 	button[0][2] = new GameButton(dealer, visitor, this); button[0][2].setText("STAY");
	 	button[0][3] = new GameButton(dealer, visitor, this); button[0][3].setText("NEXTGAME");
		button[0][4] = new GameButton(dealer, visitor, this); button[0][4].setText("QUIT");
		
	 	// 딜러 덱 버튼 생성
	 	dealer_deck = new JLabel[2][11];
	 	for(int i = 0; i < 2; i++) 
	     	for(int j = 0; j < 11; j++) {
	     		dealer_deck[i][j]= new JLabel("");
	 			dealer_deck[i][j].setBorder(new BevelBorder(BevelBorder.LOWERED));
	     	}
	 	
	 // 플레이어 덱 버튼 생성		
	 	visitor_deck = new JLabel[2][11];
	 	for(int i = 0; i < 2; i++) 
	     	for(int j = 0; j < 11; j++) {
	     		visitor_deck[i][j]= new JLabel("");
	     		visitor_deck[i][j].setBorder(new BevelBorder(BevelBorder.LOWERED));
	     	}
	 	// 프레임과 패널 생성
	 	Container c = getContentPane();
	 	c.setLayout(new BorderLayout());
	 	JPanel p1 = new JPanel(); // 딜러 덱 버튼
	 	JPanel p2 = new JPanel();
	 	JPanel p2_1 = new JPanel(); // 환영인사 문구
	 	JPanel p2_2 = new JPanel(); // 게임 진행 상황 문구 
	 	JPanel p2_3 = new JPanel(); // START,HIT,STAY.NEXTGAME,QUIT 버튼
	 	JPanel p3 = new JPanel(); // 플레이어 덱 버튼
	 	label = new JLabel("게임 진행중입니다.");
	 	label2 = new JLabel("현재 Chip 개수 : " + visitor.getChip());
		welcome_label = new JLabel("");
	 	
	 	p1.setLayout(new GridLayout(2,11));
	     for(int i = 0; i < 2; i++)
	     	for(int j = 0; j < 11; j++)
	     		p1.add(dealer_deck[i][j]);
	
	     p2.setLayout(new BorderLayout());
	     p2.add(p2_1, BorderLayout.NORTH);
	 	 p2_1.add(welcome_label);
	 	 p2.add(p2_2, BorderLayout.CENTER);
	 	 p2_2.add(label);
	 	 p2_2.add(label2);
	     p2.add(p2_3, BorderLayout.SOUTH);
	 	 p2_3.add(button[0][0]);
	 	 p2_3.add(button[0][1]);
	 	 p2_3.add(button[0][2]);
	 	 p2_3.add(button[0][3]);
	  	 p2_3.add(button[0][4]);
	 	
	     p3.setLayout(new GridLayout(2,11));
	     for(int i = 0; i < 2; i++) {
	     	for(int j = 0; j < 11; j++)
	     		p3.add(visitor_deck[i][j]);
	     }
	     
	 	c.add(p1, BorderLayout.NORTH);
	 	c.add(p2, BorderLayout.CENTER);
	 	c.add(p3, BorderLayout.SOUTH);
	 	
	 	setTitle("BLACKJACK");
	 	setSize(1200,500);
	 	setVisible(true);
	 }
	
	 /** update - 프레임 상태 갱신 */
	 public void update() {
		 welcome_label.setText("Welcome To Softopia Casino");
		 
		 if ((visitor.showHand().equals("bust!") && visitor.getHavingCardsNum() == 2) || (visitor.showHand().equals("black jack!!") && visitor.getHavingCardsNum() == 2)) { 
			 for (int i = 0; i < dealer.getHavingCardsNum(); i++) {
			 		dealer_deck[0][i].setText(dealer.hand(i).getSuit());
			 		dealer_deck[1][i].setText("" + dealer.hand(i).getRank());
			 	}
			 	
			 	for (int i = 0; i < visitor.getHavingCardsNum(); i++) {
			 		visitor_deck[0][i].setText(visitor.hand(i).getSuit());
			 		visitor_deck[1][i].setText("" + visitor.hand(i).getRank());	
			 	}
	 		setResult();
		 }
		 
		 else if ((visitor.showHand().equals("bust!") && visitor.getHavingCardsNum() != 2) || (visitor.showHand().equals("black jack!!") && visitor.getHavingCardsNum() != 2)) {
		 	for (int i = 0; i < dealer.getHavingCardsNum(); i++) {
		 		dealer_deck[0][i].setText(dealer.hand(i).getSuit());
		 		dealer_deck[1][i].setText("" + dealer.hand(i).getRank());
		 	}
		 	
		 	for (int i = 0; i < visitor.getHavingCardsNum(); i++) {
		 		visitor_deck[0][i].setText(visitor.hand(i).getSuit());
		 		visitor_deck[1][i].setText("" + visitor.hand(i).getRank());	
		 	}
			setResult();
		 }
	 
		 else {
			 if (visitor.getHavingCardsNum() == 2 && !getResult()) {
				 dealer_deck[0][dealer.getHavingCardsNum()-1].setText(dealer.hand(dealer.getHavingCardsNum() - 1).getSuit());
				 dealer_deck[1][dealer.getHavingCardsNum()-1].setText(""+dealer.hand(dealer.getHavingCardsNum() - 1).getRank());
				 visitor_deck[0][visitor.getHavingCardsNum()-1].setText(visitor.hand(visitor.getHavingCardsNum() - 1).getSuit());
				 visitor_deck[1][visitor.getHavingCardsNum()-1].setText(""+visitor.hand(visitor.getHavingCardsNum() - 1).getRank());
				 dealer_deck[0][dealer.getHavingCardsNum()-2].setText("###");
				 dealer_deck[1][dealer.getHavingCardsNum()-2].setText("###");
				 visitor_deck[0][visitor.getHavingCardsNum()-2].setText(visitor.hand(visitor.getHavingCardsNum() - 2).getSuit());
				 visitor_deck[1][visitor.getHavingCardsNum()-2].setText(""+visitor.hand(visitor.getHavingCardsNum() - 2).getRank());	
			 }
			 
			 else if (!getResult()) {
				 visitor_deck[0][visitor.getHavingCardsNum()-1].setText(visitor.hand(visitor.getHavingCardsNum()-1).getSuit());
				 visitor_deck[1][visitor.getHavingCardsNum()-1].setText(""+visitor.hand(visitor.getHavingCardsNum()-1).getRank());			
			 }
		 
			 else {
				 while(dealer.wantsACard()) 
					 dealer.dealTo(dealer);	   
				
				 	for(int i = 0; i<dealer.getHavingCardsNum(); i++ ) {
				    dealer_deck[0][i].setText(dealer.hand(i).getSuit());
					dealer_deck[1][i].setText(""+dealer.hand(i).getRank());
					}
			 }
		 }
	 	}	 
		 
	 	/** setResult - 게임 결과를 프레임에 출력하여 나타내기 */
	 public void setResult() { 
	 		if(!dealer.wantsACard()) {
	 		label.setText(dealer.getResult(visitor));
	 		}
	 		getresult = true;
	 		label2.setText("현재 Chip 개수 : " + visitor.getChip());
	 		
	 	}
	
	 	/** getResult - 게임의 승패 판정 여부를 반환
	 	 * @return - 승패 결과가 도출되면 true, 도출되지 않으면 false */
	 public boolean getResult() {
	 		return getresult;
	 	}
 }
