package de.gothaer.service.internal;

import de.gothaer.persistence.Person;
import de.gothaer.persistence.PersonenRepository;
import de.gothaer.service.BlacklistService;
import de.gothaer.service.PersonenServiceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;
    @Mock
    private PersonenRepository personenRepositoryMock;

    @Mock
    private BlacklistService blacklistServiceMock;


    @Test
    void speichern_parameterIsNull_throwsPersonenServiceException() {
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(null));
        assertEquals("Person darf nicht null sein", ex.getMessage());
    }
    @Test
    void speichern_vornameIsNull_throwsPersonenServiceException() {
        Person invalidPerson = Person.builder().id("1").vorname(null).nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }

    @Test
    void speichern_vornameTooShort_throwsPersonenServiceException() {
        Person invalidPerson = Person.builder().id("1").vorname("J").nachname("Doe").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Vorname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_nachnameIsNull_throwsPersonenServiceException() {
        Person invalidPerson = Person.builder().id("1").vorname("John").nachname(null).build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Nachname zu kurz", ex.getMessage());
    }
    @Test
    void speichern_nachnametooShort_throwsPersonenServiceException() {
        Person invalidPerson = Person.builder().id("1").vorname("John").nachname("D").build();
        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(invalidPerson));
        assertEquals("Nachname zu kurz", ex.getMessage());
    }

    @Test
    void speichern_unerwuenschtePerson_throwsPersonenServiceException() {
        Person blacklistedPerson = Person.builder().id("1").vorname("John").nachname("der Hunne").build();

        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(true);

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(blacklistedPerson));
        assertEquals("Unerwuenschte Person", ex.getMessage());

        verify(blacklistServiceMock).isBlacklisted(blacklistedPerson);
        verify(personenRepositoryMock, never()).save(any(Person.class));
    }

    @Test
    void speichern_unexpectedRuntimeExceptionInRepo_throwsPersonenServiceException() {
        Person validPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
        // RecordMode
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(false);

        doThrow(ArithmeticException.class).when(personenRepositoryMock).save(any(Person.class));

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(validPerson));
        assertEquals("Ein Fehler ist aufgetreten", ex.getMessage());
        assertEquals("ArithmeticException",ex.getCause().getClass().getSimpleName());



    }
    @Test
    void speichern_unexpectedRuntimeExceptionInBlacklist_throwsPersonenServiceException() {
        Person validPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
        // RecordMode
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenThrow(ArrayIndexOutOfBoundsException.class);

        PersonenServiceException ex = assertThrows(PersonenServiceException.class, ()->objectUnderTest.speichern(validPerson));
        assertEquals("Ein Fehler ist aufgetreten", ex.getMessage());
        assertEquals(ArrayIndexOutOfBoundsException.class,ex.getCause().getClass());
    }


    @Test
    void speichern_happyDay_personIsPassedToRepository() throws Exception{

        InOrder order = Mockito.inOrder(blacklistServiceMock, personenRepositoryMock);

        // Arrange
        Person validPerson = Person.builder().id("1").vorname("John").nachname("Doe").build();
        when(blacklistServiceMock.isBlacklisted(any(Person.class))).thenReturn(false);

        // Action
        objectUnderTest.speichern(validPerson);

       // Assertion
        order.verify(blacklistServiceMock).isBlacklisted(validPerson);
        order.verify(personenRepositoryMock, times(1)).save(validPerson);


    }

}