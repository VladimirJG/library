package ru.danilov.library.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @NotEmpty(message = "name must not be empty")
    @Column(name = "name")
    private String name;
    @Column(name = "year")
    @NotNull(message = "year must no be null")
    @Min(value = 1900, message = "min value = 1900")
    @Max(value = 2024, message = "max value = 2024")
    private int year;
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author owner;
}