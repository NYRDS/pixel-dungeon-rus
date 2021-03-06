package com.watabou.pixeldungeon.items.armor;

import com.nyrds.pixeldungeon.ml.R;
import com.nyrds.platform.util.StringsManager;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.hero.HeroClass;
import com.watabou.pixeldungeon.utils.GLog;

import org.jetbrains.annotations.NotNull;

public class WitchdoctorArmor extends GnollArmor {
	
	{
        name = StringsManager.getVar(R.string.GnollArmor_Name);
		image = 27;
		hasHelmet = true;
		coverHair = true;
		coverFacialHair = true;

	}

	@Override
	public boolean doEquip(@NotNull Char hero ) {
		//if (hero.getSubClass() == HeroSubClass.WITCHDOCTOR) {
		if (hero.getHeroClass() == HeroClass.GNOLL) {
			return super.doEquip( hero );
		} else {
            GLog.w(StringsManager.getVar(R.string.GnollArmor_NotGnoll));
			return false;
		}
	}
}