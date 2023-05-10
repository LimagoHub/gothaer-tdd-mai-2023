package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ComputerPlayerTest {

    @InjectMocks
    private ComputerPlayer objectUnderTest;

    @Mock
    private Writer writerMock;
    @ParameterizedTest(name = "Durchlauf Nr. {index} with {0} stones expects {1} as return")
    @CsvSource({"20,3","21,1","22,1","23,2"})
    void doTurn(int stones, int expected){

        assertEquals(expected, objectUnderTest.doTurn(stones));
        verify(writerMock).write(String.format("Computer nimmt %s Steine.", expected));
    }



}