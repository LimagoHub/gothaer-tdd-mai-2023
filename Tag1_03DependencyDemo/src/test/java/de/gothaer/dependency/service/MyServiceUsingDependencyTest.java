package de.gothaer.dependency.service;

import de.gothaer.dependency.Dependency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyServiceUsingDependencyTest {

    private Dependency demo ;

    private MyServiceUsingDependency objectUnderTest;
    private Dependency dependencyMock;

    @BeforeEach
    void setup() {
        dependencyMock = mock(Dependency.class);
        objectUnderTest = new MyServiceUsingDependency(dependencyMock);
    }

    @Test
    void method1_bla() {
        objectUnderTest.methode1("abc");
        // Assert
        verify(dependencyMock, times(1)).foo("ABC");
    }

    @Test
    void method2_bla() {

        // Recordmock (Mock wird auf ein Verhalten programmiert)
        when(dependencyMock.bar()).thenReturn(3);
        // Replay

        var result = objectUnderTest.methode2();
        // Assert
        verify(dependencyMock).bar();
        assertEquals(9, result);
    }

    @Test
    void method3_bla() {

        // Recordmock (Mock wird auf ein Verhalten programmiert)
        when(dependencyMock.foobar("Hallo")).thenReturn(10).thenReturn(20).thenReturn(30);
        //when(dependencyMock.foobar("Hello")).thenReturn(15);
        // Replay

        var result = objectUnderTest.methode3();
        // Assert
        verify(dependencyMock, atLeast(1)).foobar("Hallo");
        assertEquals(60, result);
    }
}