package model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Person {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "seq1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    @NonNull
    private String name;
}
