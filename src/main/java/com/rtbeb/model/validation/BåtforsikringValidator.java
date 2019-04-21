package com.rtbeb.model.validation;

import com.rtbeb.model.base.forsikring.Båt;
import com.rtbeb.model.base.forsikring.Båtforsikring;
import com.rtbeb.model.base.forsikring.Eier;

/**
 * Validerer båtforsikringer i sin helhet.
 */
public class BåtforsikringValidator extends ForsikringValidator{

    public static boolean BåtforsikringIsValid(Båtforsikring båtforsikring){
        Båt båt = båtforsikring.getBåt();
        Eier eier = båt.getEier();

        if(ForsikringValidator.ForsikringIsValid(båtforsikring) && BåtValidator.båtIsValid(båt) && EierValidator.EierIsValid(eier)){
            return true;
        }
        return false;
    }

}
