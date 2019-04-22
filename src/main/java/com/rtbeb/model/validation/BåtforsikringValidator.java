package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt.Båt;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Båt.Eier;

/**
 * Validerer båtforsikringer i sin helhet.
 */
public class BåtforsikringValidator extends ForsikringValidator{

    /**
     * Validerer Båt, eier og båtforsikringen.
     * @param båtforsikring
     * @return Returnerer true hvis gyldig.
     */
    public static boolean BåtforsikringIsValid(Båtforsikring båtforsikring){
        Båt båt = båtforsikring.getBåt();
        Eier eier = båt.getEier();

        return ForsikringValidator.ForsikringIsValid(båtforsikring) && BåtValidator.båtIsValid(båt) && EierValidator.EierIsValid(eier);
    }

}
