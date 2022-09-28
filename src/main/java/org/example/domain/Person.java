package org.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {

    private long id;
    private String name;
    private int age;
}
