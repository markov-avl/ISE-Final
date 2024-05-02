package ru.dvfu.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
public class Game extends BaseEntity implements Serializable {

    @ManyToOne
    @JoinColumn(nullable = false)
    private Publisher publisher;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private String name;

}
