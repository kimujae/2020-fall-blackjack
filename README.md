# 2020-fall-프로그램설계방법론-blackjack
  
# Model
  
[Class Card]
  
Constructor : 카드한장생성  
getSuit() : 카드무늬반환  
getRank() : 카드등급반환  
rankA() : 카드등급합이10이면A는11,아니면A는1  
  
  
[Class CardDeck]
  
Constructor : creatDeck()메소드실행하여카드한벌(52장)생성  
privatecreateSuit() : 13장생성  
createDeck() : 4개의무늬각각13장씩생성하여카드1벌만들기  
booleanmoreCards() : 덱에카드가남아있으면true,없으면false  
newCard() : 카드덱에서임의로한장을선택해뽑아주는메소드  
  
  
[interface CardPlayerBehavior]  

wantsACard() : 카드를더받을지결정  
receiveCard() : 카드받기  
booleanbust() : 카드합이21이상이면bust  
  
  
[Abstract Class CardPlayer implements CardPlayerBehavior]  
  
Constructor : 들고있는카드초기화(ModelCard[][])  
abstract boolean wantsACard()  
boolean receiveCard() : 카드받기성공하면true,실패하면false  
Card[] showCards() : 들고있는카드보여주기  
abstract boolean bust() 
  
  
[Class Dealer extends CardPlayer]  
  
Constructor : CardPlayer상속  
receiveCard() : CardDeckClass의newCard()를호출해카드2장받아오기 //처음에받은카드2장저장,hand[0]->비공개,hand[1]->공개  
countCard() : 들고있는카드등급판정  
wantsACard() : 등급이16이하이면한장더받기(receiveCard()실행)  //받은카드hand[2]에추가  
bust() : 2장혹은3장카드합이21이상이면bust  
  
  
[Class Visitor extends CardPlayer]  
  
Constructor : CardPlayer상속  
receiveCard() : CardDeck Class의newCard()를호출해카드2장받아오기  
처음에 받은 카드 2장 저장, hand[0] -> 비공개, hand[1]->공개countCard() : 들고있는 카드등급 판정  
wantsACard() : 등급이 16이하이면 Hit여부 묻기(JButton HitButton 호출)  // CardDeck Class의newCard()를호출해카드1장씩받아와저장  
bust : 모든 카드 합이 21이상이면 bust  
intchips : 가지고있는 칩 개수 반환(이기면+1,지면-1,비기면+0,블랙잭+2)  
  
  
# View  
  
[Class GameFrame]  
  
Constructor : 프레임 생성,패널 끼우기(게임진행 상황 프린트)  
clearFrame : 새 게임을 실행하면 프레임을 새로갈기  
  
**view에서해야할일: 게임진행여부를 묻는 버튼, 딜러가 나누어 준 카드, 게임결과를 frame에 나타낸다.   
  
  
update()  
손님이 Hit버튼을 누르면 실행되는 메소드로 딜러가 나눠준 카드정보와 손님의 현재 카드 총합을 frame에 표시하고, 손님에게 나누어준 첫 두장의 카드가 21이면 무조건 승리문구를 나타내며, 손님의 총 카드의 정보가 21을 초과하게 되면 bust했다는 내용의 문구를 띄운다.   
  
카드정보 : 손님이 ACE카드를 받게 되면 손님이 갖고있는 ACE제외 카드의 총합 +1/ 총합+10을 보여주고 총합21을 넘지 않는 범위에서 21에 가장 가까운 수로(1OR 10중 ) 자동으로 ACE에 숫자를 부여하여 총합을 나타낸다.   
  
setResult()  
손님이 Stay버튼을 누르면 실행되는 메소드로 최종결과를 frame에 출력해준다. 이때 승패의 결과와 함께 딜러의 총합과 손님의 총합을 함께 보여준다.  
  
  
  
  
  
  
  
  
# Controller  
  
[Class GameButton]  

Constructor : Model,View초기화  
  
  
**Controller에서 해야 할 일:  게임 진행과정(손님의 Hit &Stay 여부로 게임이 진행됨)을 받고 그에 따라 view에 딜러가 나눠준 카드정보를 나타내거나 규칙에 따라 bust, 승패 여부를 출력하는 총체적인 클래스  
  
actionPerfomed(ActionEvent e)  
Hit버튼과 Stay 버튼을 통해 딜러가 카드를 나눠주고, 나눠준 카드를 view에 나타내며 최종적으로 게임 결과를 view에 출력하게 한다.   

