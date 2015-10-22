package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public CardType getCardType() {
		return cardType;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public void setName(String name) {
		cardName = name;
	}
	
	public void setType(CardType type) {
		cardType = type;
	}
}
