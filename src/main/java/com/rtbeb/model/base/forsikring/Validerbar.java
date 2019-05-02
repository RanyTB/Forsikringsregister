package com.rtbeb.model.base.forsikring;

/**
 * @author Rany Tarek Bouorm - s236210
 */
public interface Validerbar {

    /**
     * En klasse som implementerer validatable har en metode isValid() for validering. Metoden kaller klassens
     * statiske valideringsmetode.
     * @return Returnerer true hvis gyldig.
     */
    boolean isValid();
}
