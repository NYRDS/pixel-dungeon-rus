/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.watabou.pixeldungeon.items.weapon.melee;

import com.nyrds.pixeldungeon.ml.R;
import com.nyrds.platform.util.StringsManager;
import com.watabou.pixeldungeon.actors.hero.Belongings;
import com.watabou.pixeldungeon.sprites.ItemSpriteSheet;

public class Quarterstaff extends MeleeWeapon {
	{
		image = ItemSpriteSheet.QUARTERSTAFF;
		animation_class = STAFF_ATTACK;
	}
	
	public Quarterstaff() {
		super( 2, 1f, 0.8f );
	}
	
	@Override
	public String desc() {
        return StringsManager.getVar(R.string.Quarterstaff_Info);
    }

	@Override
	public Belongings.Slot slot(Belongings belongings) {
		return Belongings.Slot.WEAPON;
	}

	@Override
	public Belongings.Slot blockSlot() {
		return Belongings.Slot.LEFT_HAND;
	}

}
