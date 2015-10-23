package clueGame;

public class Card {
	private String cardName;
	private CardType cardType;
	
	public Card() {
		super();
	}
	
	public Card(String cardName, CardType cardType) {
		super();
		this.cardName = cardName;
		this.cardType = cardType;
	}
	
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
