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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardName == null) ? 0 : cardName.hashCode());
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (cardName == null) {
			if (other.cardName != null)
				return false;
		} else if (!cardName.equals(other.cardName))
			return false;
		if (cardType != other.cardType)
			return false;
		return true;
	}
	
	
}
