package de.gothaer.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private String id;
    private String vorname;
    private String nachname;


}
