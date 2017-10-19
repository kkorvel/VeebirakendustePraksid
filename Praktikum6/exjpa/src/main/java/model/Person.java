package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Data
@Entity
public class Person {

    @Id
    @SequenceGenerator(name = "my_seq", sequenceName = "seq1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @JoinColumn(name = "person_id", nullable = false)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Phone> phones = new ArrayList<>();
}
