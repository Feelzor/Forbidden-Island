package ileinterdite.model;

import ileinterdite.util.Utils;

import java.util.*;

public class Deck {

    private DiscardPile discardPile;
    private Stack<Card> cards;
    private Utils.CardType cardType;

    public Deck(Stack<Card> cards, Utils.CardType type) {
        this.cards = cards;
        this.cardType = type;
    }

    public void setDiscardPile(DiscardPile discard) {
        this.discardPile = discard;
    }

	public Utils.CardType getCardType() {
		return cardType;
	}

	public Stack<Card> getCards() {
		return cards;
	}

	/**
	 * permet de picher un cetain nombre de cartes
	 * @param nbCards nb de cartes a piocher
	 * @return
	 */
	public ArrayList<Card> drawCards (int nbCards){
		ArrayList<Card> drawedCards = new ArrayList<>();

		for (int i = 0; i < nbCards; i++) {
            if (cards.size() == 0) {
                discardPile.shuffle();
                addAtTheTop(discardPile.getCards());
                discardPile.clearPile();
            }

			drawedCards.add(this.cards.pop());
		}
		return drawedCards;
	}

	/**
	 * ajoute des cartes en bas de la pile sans changer le haut
	 * @param cards
	 */
	public void addAtTheBottom(ArrayList<Card> cards){
		Stack<Card> decKTmp = new Stack<>();
		decKTmp.addAll(cards);
		decKTmp.addAll(this.cards);
		this.cards = decKTmp;
	}

	/**
	 * Ajoute toutes les carte en haut de la pioche
	 * @param newCards
	 */
	public void addAtTheTop(ArrayList<Card> newCards){
		cards.addAll(newCards);

	}

	public void shuffle(){
		Collections.shuffle(cards);

	}

}