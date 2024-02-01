import java.util.Set;

public class Card{
private String suit;
private String value;
private String card;
private String filePath;
private Chart correctChart;
private Set<Card> allCards;

	public Card() {
		correctChart = new Chart();
		suit = getSuit((int) (Math.random() * 4));
		value = getValue((int)(Math.random() * 13));
		card = value + suit;
		filePath = "PNG-cards-1.3/" + value + "_of_" + suit + ".png";
	}
	
	public Card(int suit, int value) {
		correctChart = new Chart();
		this.suit = getSuit(suit);
		this.value = getValue(value);
		card = this.value + this.suit;
		filePath = "PNG-cards-1.3/" + value + "_of_" + suit + ".png";
	}

	public String getCard() {
		return card;
	}
	
	public int valueToIndex() {
		switch(value) {
		case "Ace":
			return 12;
		case "King":
			return 11;
		case "Queen":
			return 10;
		case "Jack":
			return 9;
		default:
			return Integer.parseInt(value) - 2;
		
		
		}
		
	}
	
	
	
	public String getValue(int cardValue) {
		
		value = correctChart.getCard(cardValue);
		switch(value) {
		case "A":
			value = "Ace";
			break;
		case "K":
			value = "King";
			break;
		case "Q":
			value = "Queen";
			break;
		case "J":
			value = "Jack";
			break;
		case "T":
			value = "10";
			break;
		default: 
			break;
			
		}		
		return value;
	}
		
	
	

	public String getSuit(int suitValue) {
		switch(suitValue) {
		case 0:
			return "hearts";
		case 1:
			return "spades";
		case 2:
			return "clubs";
		case 3:
			return "diamonds";
		}
		return null;	
	}



	
	public String getSuit() {
		return suit;
	}
	
	
	
	public String getValue() {
		return value;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
}
