package ileinterdite.factory;

import ileinterdite.model.*;

import java.util.Collections;
import java.util.Stack;

public class TreasureCardsFactory {

    public static Stack<TreasureCard> treasureCardsFactory(Grid grid) {
        Stack<TreasureCard> treasureCards = new Stack<>();

        for (int i=0; i<5; i++) {
            treasureCards.add(new TreasureCard("La Pierre sacrée",grid));
        }

        for (int i=0; i<5; i++) {
            treasureCards.add(new TreasureCard("La Statue du zéphyr",grid));
        }

        for (int i=0; i<5; i++) {
            treasureCards.add(new TreasureCard("Le Cristal ardent",grid));
        }

        for (int i=0; i<5; i++) {
            treasureCards.add(new TreasureCard("Le Calice de l'onde",grid));
        }

        for (int i=0; i<3; i++) {
            treasureCards.add(new TreasureCard("Montée des eaux",grid));
        }

        for (int i=0; i<3; i++) {
            treasureCards.add(new TreasureCard("Helicoptère",grid));
        }

        for (int i=0; i<2; i++) {
            treasureCards.add(new TreasureCard("Sacs de sable",grid));
        }

        return treasureCards;
    }
}