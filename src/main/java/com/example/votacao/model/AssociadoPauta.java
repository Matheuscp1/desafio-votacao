package com.example.votacao.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "associado_pauta")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssociadoPauta extends AbstrataEntitdade {

    private Boolean votou;

    @ManyToOne(fetch = FetchType.LAZY)
    private Associado associado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;
}
