package mage.game.permanent.token;

import mage.MageInt;
import mage.constants.CardType;

public class OozeToken extends Token {

    public OozeToken(int power, int toughness) {
        super("Ooze", power + "/" + toughness + " green ooze creature token");
        cardType.add(CardType.CREATURE);
        color.setGreen(true);
        subtype.add("Ooze");
        this.power = new MageInt(power);
        this.toughness = new MageInt(toughness);
    }

    public OozeToken() {
        super("Ooze", "X/X green ooze creature token");
        cardType.add(CardType.CREATURE);
        color.setGreen(true);
        subtype.add("Ooze");
        power = new MageInt(0);
        toughness = new MageInt(0);
    }
}
