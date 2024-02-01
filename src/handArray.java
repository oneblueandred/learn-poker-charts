import java.util.ArrayList;

public class handArray {
public ArrayList<Card[]> hands;
	
	public handArray() {
		hands = new ArrayList<Card[]>();
	}
	
	public void addHand(Card[] hand) {
		hands.add(hand);
	}	
	
}
