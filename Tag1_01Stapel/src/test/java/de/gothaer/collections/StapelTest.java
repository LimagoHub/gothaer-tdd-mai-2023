package de.gothaer.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StapelTest {

    private Stapel objectUnderTest;

    @BeforeEach
    void setUp() {
       objectUnderTest = new Stapel();
    }

    @Test
    @DisplayName("should return true when isEmpty called on empty Stapel")
    void isEmpty_emptyStack_returnsTrue() {
        // Arrange


        // Action
        var result = objectUnderTest.isEmpty();

        // Assertion
        assertTrue(result);
    }
    @Test
    void isEmpty_NotEmptyStack_returnsFalse() throws Exception{
        // Arrange

        objectUnderTest.push(1);

        // Action
        // Assertion
        assertFalse(objectUnderTest.isEmpty());
    }

    @Test
    void push_fillUpToLimit_noExceptionIsThrown() throws Exception{

        // Arrange
        fillUpToLimit();
    }

    @Test
    void push_overflow_StapelExceptionIsThrown() throws Exception{

        // Arrange
        fillUpToLimit();
        // Action + Assertion
        assertThrows(StapelException.class,()->objectUnderTest.push(1));

    }

    private void fillUpToLimit() {
        for (int i = 0; i < 10; i++) {
            assertDoesNotThrow(()->objectUnderTest.push(1));
        }
    }

}