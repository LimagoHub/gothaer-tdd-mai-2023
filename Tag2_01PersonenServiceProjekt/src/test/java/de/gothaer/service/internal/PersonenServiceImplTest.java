package de.gothaer.service.internal;

import de.gothaer.persistence.Person;
import de.gothaer.persistence.PersonenRepository;
import de.gothaer.service.PersonenServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PersonenServiceImplTest {

    @InjectMocks
    private PersonenServiceImpl objectUnderTest;
    @Mock
    private PersonenRepository personenRepositoryMock;


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

}