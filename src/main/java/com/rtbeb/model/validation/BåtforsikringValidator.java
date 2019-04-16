package com.rtbeb.model.validation;

import com.rtbeb.model.base.Båt;
import com.rtbeb.model.base.Båtforsikring;
import com.rtbeb.model.base.Eier;

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
