package com.example.votacao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "associadoAS")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Associado extends AbstrataEntitdade {

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String CPF;
}
