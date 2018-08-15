import static org.junit.Assert.*;

import org.junit.Test;


public class PlayingCardTest {

	@Test
	public void testPlayingCardConstructor() {
		PlayingCard card = new PlayingCard();
		assertNotNull(card);
	}
	
	@Test
	public void testPlayingCardGetRank() {
		PlayingCard card = new PlayingCard("A","S");
		assertEquals("Ace of Spades and is Face Up",card.toString());
		assertEquals("A",card.getRank());
		card = new PlayingCard("2","S");
		assertEquals("2",card.getRank());
		card = new PlayingCard("3","S");
		assertEquals("3",card.getRank());
		card = new PlayingCard("4","S");
		assertEquals("4",card.getRank());
		card = new PlayingCard("5","S");
		assertEquals("5",card.getRank());
		card = new PlayingCard("6","S");
		assertEquals("6",card.getRank());
		card = new PlayingCard("7","S");
		assertEquals("7",card.getRank());
		card = new PlayingCard("8","S");
		assertEquals("8",card.getRank());
		card = new PlayingCard("9","S");
		assertEquals("9",card.getRank());
		card = new PlayingCard("10","S");
		assertEquals("10",card.getRank());
		card = new PlayingCard("J","S");
		assertEquals("J",card.getRank());
		card = new PlayingCard("Q","S");
		assertEquals("Q",card.getRank());
		card = new PlayingCard("K","S");
		assertEquals("K",card.getRank());

		card = new PlayingCard("0","S");
		assertEquals("2",card.getRank());
		card = new PlayingCard("-1","S");
		assertEquals("2",card.getRank());
		card = new PlayingCard("B","S");
		assertEquals("2",card.getRank());
		card = new PlayingCard("11","S");
		assertEquals("2",card.getRank());
	}
	
	@Test
	public void testPlayingCardGetSuit() {
		PlayingCard card = new PlayingCard("10","S");
		assertEquals("S",card.getSuit());
		card = new PlayingCard("10","C");
		assertEquals("C",card.getSuit());
		card = new PlayingCard("10","D");
		assertEquals("D",card.getSuit());
		card = new PlayingCard("10","H");
		assertEquals("H",card.getSuit());

		card = new PlayingCard("10","A");
		assertEquals("C",card.getSuit());
		card = new PlayingCard("10","-A");
		assertEquals("C",card.getSuit());
		card = new PlayingCard("10","0");
		assertEquals("C",card.getSuit());
		card = new PlayingCard("10","2");
		assertEquals("C",card.getSuit());
	}

	@Test
	public void testPlayingisFaceUp() {
		PlayingCard card = new PlayingCard("10","S");
		assertEquals("Ten of Spades and is Face Up",card.toString());
		card.flip();
		assertEquals("Ten of Spades and is Face Down",card.toString());
		card.flip();
		assertEquals("Ten of Spades and is Face Up",card.toString());
	}
	
	@Test
	public void testPlayingCardConstructorClubs() {
		PlayingCard card = new PlayingCard("A","C");
		assertEquals("Ace of Clubs and is Face Up", card.toString());
		card = new PlayingCard("2","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("3","C");
		assertEquals("Three of Clubs and is Face Up", card.toString());
		card = new PlayingCard("4","C");
		assertEquals("Four of Clubs and is Face Up", card.toString());
		card = new PlayingCard("5","C");
		assertEquals("Five of Clubs and is Face Up", card.toString());
		card = new PlayingCard("6","C");
		assertEquals("Six of Clubs and is Face Up", card.toString());
		card = new PlayingCard("7","C");
		assertEquals("Seven of Clubs and is Face Up", card.toString());
		card = new PlayingCard("8","C");
		assertEquals("Eight of Clubs and is Face Up", card.toString());
		card = new PlayingCard("9","C");
		assertEquals("Nine of Clubs and is Face Up", card.toString());
		card = new PlayingCard("10","C");
		assertEquals("Ten of Clubs and is Face Up", card.toString());
		card = new PlayingCard("J","C");
		assertEquals("Jack of Clubs and is Face Up", card.toString());
		card = new PlayingCard("Q","C");
		assertEquals("Queen of Clubs and is Face Up", card.toString());
		card = new PlayingCard("K","C");
		assertEquals("King of Clubs and is Face Up", card.toString());

		//Test invalid arguments
		card = new PlayingCard("0","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("1","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("11","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("12","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("13","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("-1","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("B","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("C","C");
		assertEquals("Two of Clubs and is Face Up", card.toString());
	}
	
	@Test
	public void testPlayingCardConstructorDiamonds() {
		PlayingCard card = new PlayingCard("A","D");
		assertEquals("Ace of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("2","D");
		assertEquals("Two of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("3","D");
		assertEquals("Three of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("4","D");
		assertEquals("Four of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("5","D");
		assertEquals("Five of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("6","D");
		assertEquals("Six of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("7","D");
		assertEquals("Seven of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("8","D");
		assertEquals("Eight of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("9","D");
		assertEquals("Nine of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("10","D");
		assertEquals("Ten of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("J","D");
		assertEquals("Jack of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("Q","D");
		assertEquals("Queen of Diamonds and is Face Up", card.toString());
		card = new PlayingCard("K","D");
		assertEquals("King of Diamonds and is Face Up", card.toString());

		//Test invalid arguments
		card = new PlayingCard("0","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("1","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("11","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("12","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("13","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("-1","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("B","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("C","D");
		assertEquals("Two of Clubs and is Face Up", card.toString());
	}

	@Test
	public void testPlayingCardConstructorHearts() {
		PlayingCard card = new PlayingCard("A","H");
		assertEquals("Ace of Hearts and is Face Up", card.toString());
		card = new PlayingCard("2","H");
		assertEquals("Two of Hearts and is Face Up", card.toString());
		card = new PlayingCard("3","H");
		assertEquals("Three of Hearts and is Face Up", card.toString());
		card = new PlayingCard("4","H");
		assertEquals("Four of Hearts and is Face Up", card.toString());
		card = new PlayingCard("5","H");
		assertEquals("Five of Hearts and is Face Up", card.toString());
		card = new PlayingCard("6","H");
		assertEquals("Six of Hearts and is Face Up", card.toString());
		card = new PlayingCard("7","H");
		assertEquals("Seven of Hearts and is Face Up", card.toString());
		card = new PlayingCard("8","H");
		assertEquals("Eight of Hearts and is Face Up", card.toString());
		card = new PlayingCard("9","H");
		assertEquals("Nine of Hearts and is Face Up", card.toString());
		card = new PlayingCard("10","H");
		assertEquals("Ten of Hearts and is Face Up", card.toString());
		card = new PlayingCard("J","H");
		assertEquals("Jack of Hearts and is Face Up", card.toString());
		card = new PlayingCard("Q","H");
		assertEquals("Queen of Hearts and is Face Up", card.toString());
		card = new PlayingCard("K","H");
		assertEquals("King of Hearts and is Face Up", card.toString());

		//Test invalid arguments
		card = new PlayingCard("0","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("1","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("11","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("12","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("13","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("-1","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("B","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("C","H");
		assertEquals("Two of Clubs and is Face Up", card.toString());
	}

	@Test
	public void testPlayingCardConstructorSpades() {
		PlayingCard card = new PlayingCard("A","S");
		assertEquals("Ace of Spades and is Face Up", card.toString());
		card = new PlayingCard("2","S");
		assertEquals("Two of Spades and is Face Up", card.toString());
		card = new PlayingCard("3","S");
		assertEquals("Three of Spades and is Face Up", card.toString());
		card = new PlayingCard("4","S");
		assertEquals("Four of Spades and is Face Up", card.toString());
		card = new PlayingCard("5","S");
		assertEquals("Five of Spades and is Face Up", card.toString());
		card = new PlayingCard("6","S");
		assertEquals("Six of Spades and is Face Up", card.toString());
		card = new PlayingCard("7","S");
		assertEquals("Seven of Spades and is Face Up", card.toString());
		card = new PlayingCard("8","S");
		assertEquals("Eight of Spades and is Face Up", card.toString());
		card = new PlayingCard("9","S");
		assertEquals("Nine of Spades and is Face Up", card.toString());
		card = new PlayingCard("10","S");
		assertEquals("Ten of Spades and is Face Up", card.toString());
		card = new PlayingCard("J","S");
		assertEquals("Jack of Spades and is Face Up", card.toString());
		card = new PlayingCard("Q","S");
		assertEquals("Queen of Spades and is Face Up", card.toString());
		card = new PlayingCard("K","S");
		assertEquals("King of Spades and is Face Up", card.toString());

		//Test invalid arguments
		card = new PlayingCard("0","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("1","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("11","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("12","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("13","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("-1","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("B","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("C","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
		card = new PlayingCard("-1","S");
		assertEquals("Two of Clubs and is Face Up", card.toString());
	}
	
	@Test
	public void testPlayingCardFlip() {
		PlayingCard card = new PlayingCard("A","S");
		assertEquals("Ace of Spades and is Face Up", card.toString());
		card.flip();
		assertEquals("Ace of Spades and is Face Down", card.toString());
		card.flip();
		assertEquals("Ace of Spades and is Face Up", card.toString());
		card.flip();
		assertEquals("Ace of Spades and is Face Down", card.toString());
	}
	
	@Test
	public void testPlayingCardEquals() {
		//CLUBS
		PlayingCard card = new PlayingCard("A","C");
		PlayingCard card2 = new PlayingCard("A","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("2","C");
		card2 = new PlayingCard("2","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("3","C");
		card2 = new PlayingCard("3","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("4","C");
		card2 = new PlayingCard("4","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("5","C");
		card2 = new PlayingCard("5","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("6","C");
		card2 = new PlayingCard("6","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("7","C");
		card2 = new PlayingCard("7","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("8","C");
		card2 = new PlayingCard("8","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("9","C");
		card2 = new PlayingCard("9","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("10","C");
		card2 = new PlayingCard("10","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("J","C");
		card2 = new PlayingCard("J","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("Q","C");
		card2 = new PlayingCard("Q","C");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("K","C");
		card2 = new PlayingCard("K","C");
		assertTrue(card.equals(card2));

		//DIAMONDS
		card = new PlayingCard("A","D");
		card2 = new PlayingCard("A","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("2","D");
		card2 = new PlayingCard("2","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("3","D");
		card2 = new PlayingCard("3","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("4","D");
		card2 = new PlayingCard("4","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("5","D");
		card2 = new PlayingCard("5","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("6","D");
		card2 = new PlayingCard("6","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("7","D");
		card2 = new PlayingCard("7","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("8","D");
		card2 = new PlayingCard("8","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("9","D");
		card2 = new PlayingCard("9","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("10","D");
		card2 = new PlayingCard("10","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("J","D");
		card2 = new PlayingCard("J","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("Q","D");
		card2 = new PlayingCard("Q","D");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("K","D");
		card2 = new PlayingCard("K","D");
		assertTrue(card.equals(card2));

		//HEARTS
		card  = new PlayingCard("A","H");
		card2 = new PlayingCard("A","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("2","H");
		card2 = new PlayingCard("2","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("3","H");
		card2 = new PlayingCard("3","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("4","H");
		card2 = new PlayingCard("4","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("5","H");
		card2 = new PlayingCard("5","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("6","H");
		card2 = new PlayingCard("6","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("7","H");
		card2 = new PlayingCard("7","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("8","H");
		card2 = new PlayingCard("8","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("9","H");
		card2 = new PlayingCard("9","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("10","H");
		card2 = new PlayingCard("10","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("J","H");
		card2 = new PlayingCard("J","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("Q","H");
		card2 = new PlayingCard("Q","H");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("K","H");
		card2 = new PlayingCard("K","H");
		assertTrue(card.equals(card2));

		//SPADES
		card  = new PlayingCard("A","S");
		card2 = new PlayingCard("A","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("2","S");
		card2 = new PlayingCard("2","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("3","S");
		card2 = new PlayingCard("3","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("4","S");
		card2 = new PlayingCard("4","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("5","S");
		card2 = new PlayingCard("5","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("6","S");
		card2 = new PlayingCard("6","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("7","S");
		card2 = new PlayingCard("7","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("8","S");
		card2 = new PlayingCard("8","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("9","S");
		card2 = new PlayingCard("9","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("10","S");
		card2 = new PlayingCard("10","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("J","S");
		card2 = new PlayingCard("J","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("Q","S");
		card2 = new PlayingCard("Q","S");
		assertTrue(card.equals(card2));

		card  = new PlayingCard("K","S");
		card2 = new PlayingCard("K","S");
		assertTrue(card.equals(card2));

		//***ALTERNATIVE TESTS****
		card = new PlayingCard("A","S");
		card2 = card;
		assertTrue(card.equals(card2));

		card = new PlayingCard("2","C");
		card2 = new PlayingCard("3","C");
		assertFalse(card.equals(card2));

		card = new PlayingCard("2","C");
		card2 = new PlayingCard("2","H");
		assertFalse(card.equals(card2));

		//***MONKEY TEST**** Should be mostly TRUE (2 of Clubs)
		card = new PlayingCard("-1","H");
		card2 = new PlayingCard("-1","H");
		assertTrue(card.equals(card2));

		card = new PlayingCard("1","-H");
		card2 = new PlayingCard("1","-H");
		assertTrue(card.equals(card2));

		card = new PlayingCard("1","-H");
		card2 = new PlayingCard("1","-H");
		assertTrue(card.equals(card2));
		
		card = new PlayingCard("2","S");   //valid card
		card2 = new PlayingCard("3","-S"); //becomes 2 of Clubs
		assertFalse(card.equals(card2));

		card = new PlayingCard("0","0");
		card2 = new PlayingCard("0","0");
		assertTrue(card.equals(card2));
	}
}
