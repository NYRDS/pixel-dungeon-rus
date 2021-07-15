package com.nyrds.pixeldungeon.ml.actions;

import com.nyrds.pixeldungeon.mechanics.spells.Spell;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.scenes.GameScene;

public class UseSpell extends CharAction{

    public Spell spell;

    public UseSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public boolean act(Char hero) {
        spell.cast(hero);

        hero.curAction = null;

        hero.curAction = null;
        if(GameScene.defaultCellSelector() && ! hero.getSprite().doingSomething()) {
            hero.next();
        }

        return false;
    }
}