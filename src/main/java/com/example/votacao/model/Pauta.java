package com.example.votacao.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "pautaS")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pauta extends  AbstrataEntitdade{

    private String titulo;

    private String descricao;

    @Embedded
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<AssociadoPauta> associados = new HashSet<>();

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<VotoPauta> votos = new ArrayList<>();

    private Boolean ativo = true;

}
