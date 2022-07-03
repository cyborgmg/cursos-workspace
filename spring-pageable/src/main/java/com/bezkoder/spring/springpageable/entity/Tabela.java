package com.bezkoder.spring.springpageable.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tabela")
public class Tabela implements Serializable {

    @Id
    private Long id;

    private String nome;

    @Override
    public String toString() {
        return "Tabela{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
