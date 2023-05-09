package de.gothaer.tiere;

import lombok.*;

import java.util.Objects;


@Data
public class Schwein {

    private String name;

    @Setter(AccessLevel.PRIVATE)
    private int gewicht;

    public Schwein() {
        this("Nobody");
    }

    public Schwein(String name) {
        setName(name);
        setGewicht(10);
    }



    public void setName(final String name) {
        if(name == null )
            throw new IllegalArgumentException("Name darf nicht null sein");

        if("Elsa".equals(name) )
            throw new IllegalArgumentException("Name darf nicht Elsa sein");
        this.name = name;
    }


    public void fuettern() {
        setGewicht(getGewicht() + 1);
    }


}
