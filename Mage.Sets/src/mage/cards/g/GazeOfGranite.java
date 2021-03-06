/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.cards.g;

import java.util.UUID;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.cards.CardSetInfo;
import mage.filter.Filter;
import mage.filter.common.FilterCreaturePermanent;
import mage.filter.predicate.mageobject.ConvertedManaCostPredicate;
import mage.game.Game;
import mage.game.permanent.Permanent;

/**
 *
 * @author LevelX2
 */
public class GazeOfGranite extends CardImpl {

    private static final FilterCreaturePermanent filter = new FilterCreaturePermanent("each nonland permanent with converted mana cost X or less");
    static {
        filter.add(new ConvertedManaCostPredicate(Filter.ComparisonType.LessThan, 4));
    }

    public GazeOfGranite(UUID ownerId, CardSetInfo setInfo) {
        super(ownerId,setInfo,new CardType[]{CardType.SORCERY},"{X}{B}{B}{G}");


        // Destroy each nonland permanent with converted mana cost X or less.
         this.getSpellAbility().addEffect(new GazeOfGraniteEffect());
    }

    public GazeOfGranite(final GazeOfGranite card) {
        super(card);
    }

    @Override
    public GazeOfGranite copy() {
        return new GazeOfGranite(this);
    }
}

class GazeOfGraniteEffect extends OneShotEffect {

    public GazeOfGraniteEffect() {
        super(Outcome.DestroyPermanent);
        staticText = "Destroy each nonland permanent with converted mana cost X or less";
    }

    public GazeOfGraniteEffect(final GazeOfGraniteEffect effect) {
        super(effect);
    }

    @Override
    public GazeOfGraniteEffect copy() {
        return new GazeOfGraniteEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        for (Permanent permanent : game.getBattlefield().getActivePermanents(source.getControllerId(), game)) {
            if (!permanent.getCardType().contains(CardType.LAND) && permanent.getConvertedManaCost() <= source.getManaCostsToPay().getX()) {
                permanent.destroy(source.getSourceId(), game, false);
            }
        }
        return true;
    }

}
