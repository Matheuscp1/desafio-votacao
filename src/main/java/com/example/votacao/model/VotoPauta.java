package com.example.votacao.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "voto_pauta")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class VotoPauta extends AbstrataEntitdade{

    @Embedded
    private Voto voto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;
}
