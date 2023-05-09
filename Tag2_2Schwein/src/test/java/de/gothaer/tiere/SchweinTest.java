package de.gothaer.tiere;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchweinTest {

    private Schwein objectUnderTest;
    private final String validName = "Piggy";

    private final int initialWeight = 10;
    @BeforeEach
    void setup() {
        objectUnderTest = new Schwein();
    }
    @Test
    void ctor_default_objectCorrectIntializied() {
        assertEquals("Nobody", objectUnderTest.getName());
        assertEquals(initialWeight, objectUnderTest.getGewicht());
    }
    @Test
    void ctor_wrongNameNull_throwsIlliegalArgumentExceptionAndErrorMessage() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->new Schwein(null));
        assertEquals("Name darf nicht null sein", ex.getMessage());
    }

    @Test
    void ctor_wrongNameElsa_throsIlliegalArgumentException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, ()->new Schwein("Elsa"));
        assertEquals("Name darf nicht Elsa sein", ex.getMessage());
    }

    @Test
    void ctor_validName_objectCorrectIntializied() {

        Schwein objectUnderTest = new Schwein(validName);
        assertEquals("Piggy", objectUnderTest.getName());
        assertEquals(initialWeight, objectUnderTest.getGewicht());
    }

    @Test
    void setName_wrongNameNull_throsIlliegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->objectUnderTest.setName(null));

    }

    @Test
    void setName_wrongNameElsa_throsIlliegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()->objectUnderTest.setName("Elsa"));

    }

    @Test
    void setName_validName_NameIsStored() {

        objectUnderTest.setName(validName);
        assertEquals(validName, objectUnderTest.getName());

    }

    @Test
    void fuettern_happyDay_weightIncreased() {
        final int expectedWeight = initialWeight + 1;
        objectUnderTest.fuettern();
        assertEquals(expectedWeight, objectUnderTest.getGewicht());
    }
}