package BlackJack;

import javax.swing.*;
import java.awt.event.*;

public class GameButton extends JButton implements ActionListener {
 private Dealer dealer;
 private Visitor visitor;
 private GameFrame view;
 
 public GameButton(Dealer d, Visitor p, GameFrame v) {
	 dealer = d;
	 visitor = p;
	 view = v;
	 addActionListener(this);
 }
 
 public void actionPerformed(ActionEvent e) {
	 String s;
	 s = getText();
	 
	 // START 버튼 - 딜러-플레이어-딜러-플레이어 순으로 카드 줌
	 if(s.equals("START")) {
		 if(dealer.getHavingCardsNum() == 0) {
				 dealer.dealTo(dealer);
				 dealer.dealTo(visitor);
				 dealer.dealTo(dealer);
				 dealer.dealTo(visitor);
				 view.update();
		 }
	 }
	 // HIT 버튼 - 플레이어가 카드를 받을 수 있으면 플레이어에게 카드 주기
	 else if(s.equals("HIT")) {
		 if (!view.getResult()) {
			 if(visitor.wantsACard()) {
				 if(dealer.getHavingCardsNum() != 0 && dealer.getHavingCardsNum() <= 2) {
					 dealer.dealTo(visitor);
					 view.update();
				 }
				 if(view.getResult()) {
					 while(dealer.wantsACard())
					 dealer.dealTo(dealer);	
					 view.setResult();
					 view.update();
				 }
			 }
		 }
	 }
	 // STAY 버튼 - 딜러가 카드를 받을 수 있으면 딜러에게 카드 주기, 아니면 결과 출력
	 else if(s.equals("STAY")) {
		 if(!(dealer.getHavingCardsNum() == 0 && visitor.getHavingCardsNum() == 0)) {
		 while(dealer.wantsACard())
			 dealer.dealTo(dealer);	   
		 
		 if(view.getResult())
			 return;
		 
		 view.setResult();
		 view.update();
		 }
	 }
	 // MoreGame 버튼 - 게임이 끝나면 활성화되어, 새로운 세트를 시작함
	 else if (s.equals("NEXTGAME")) {
		 if(!view.getResult())
			 return;
		   view.dispose();
		   dealer= new Dealer();
		   visitor.setFresh();
		   view = new GameFrame(dealer, visitor);
	 }
	 // QUIT 버튼 - 게임 종료
	 else if(s.equals("QUIT"))
		 System.exit(0);
	 }
}