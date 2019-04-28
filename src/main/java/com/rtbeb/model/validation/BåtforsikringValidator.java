package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt.Båt;
import com.rtbeb.model.base.forsikring.Båt.Båtforsikring;
import com.rtbeb.model.base.forsikring.Båt.Eier;

/**
 * @author Rany Tarek Bouorm - s236210
 * Validerer båtforsikringer i sin helhet.
 */
public class BåtforsikringValidator extends ForsikringValidator{

    /**
     * Validerer en båtforsikring med Båt, Eier og Båtforsikringsobjekt i sin helhet.
     * @param båtforsikring båtforsikringen til validering.
     * @return Returnerer true hvis gyldig.
     */
    public static boolean BåtforsikringIsValid(Båtforsikring båtforsikring){
        Båt båt = båtforsikring.getBåt();
        Eier eier = båt.getEier();

        return ForsikringValidator.ForsikringIsValid(båtforsikring) && BåtValidator.båtIsValid(båt) && EierValidator.EierIsValid(eier);
    }

}
