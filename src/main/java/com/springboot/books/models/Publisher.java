package com.springboot.books.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_PUBLISHER")
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @ToString(exclude = "books")
@EqualsAndHashCode(exclude = "books")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Integer foundedYear;

    @Column(nullable = false,unique = true)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "publisher")
    private List<Book> books= new ArrayList<>();

}
