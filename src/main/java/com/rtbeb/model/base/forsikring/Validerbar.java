package com.rtbeb.model.base.forsikring;

public interface Validerbar {

    /**
     * En klasse som implementerer validatable har en metode isValid() for validering.
     * Valideringen er implementert i egne klasser.
     * @return Returnerer true hvis gyldig.
     */
    public boolean isValid();
}
