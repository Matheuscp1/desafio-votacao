package com.example.votacao.service;

import com.example.votacao.dto.ResultadoResponse;
import com.example.votacao.exceptions.ConflictException;
import com.example.votacao.exceptions.NotFoundException;
import com.example.votacao.model.Associado;
import com.example.votacao.model.AssociadoPauta;
import com.example.votacao.model.Pauta;
import com.example.votacao.model.VotoPauta;
import com.example.votacao.service.interfaces.IVotoService;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VotoService implements IVotoService {

    private final VotoPautaService votoPautaService;

    private final AssociadoService associadoService;

    private final AssociadoPautaService associadoPautaService;

    public VotoService(VotoPautaService votoPautaService, AssociadoService associadoService,  AssociadoPautaService associadoPautaService){

        this.votoPautaService = votoPautaService;
        this.associadoService = associadoService;
        this.associadoPautaService = associadoPautaService;
    }

    @Override
    public VotoPauta cadastrarVoto(VotoPauta votoPauta, String CPF) {

        Associado associado = associadoService.buscarPorCPF(CPF);
        Pauta pauta = votoPauta.getPauta();

        if (!validarDataHoraVoto(votoPauta))
            throw new ConflictException("Fora do horário de votacao!");

        if (!associadoPautaService.associadoEstaCadastrado(associado, pauta))
            throw new NotFoundException("Associado não esta cadastrado para votar nessa pauta!");

        AssociadoPauta associadoPauta = associadoPautaService.buscarAssociadoPauta(associado, pauta);

        if(associadoPauta.getVotou())
            throw new ConflictException("Associado já votou nesta pauta!");

        associadoPauta.setVotou(true);

        return votoPautaService.cadastrarVotoPauta(votoPauta);
    }

    public Boolean validarDataHoraVoto(VotoPauta votoPauta){

        LocalDateTime inicioSessao = votoPauta.getPauta().getSessao().getInicioSessao();
        LocalDateTime finalSessao = votoPauta.getPauta().getSessao().getFinalSessao();

        LocalDateTime dataHoraVoto = votoPauta.getVoto().getDataHoraVoto();

        return dataHoraVoto.isAfter(inicioSessao) && dataHoraVoto.isBefore(finalSessao);
    }


    public ResultadoResponse resultadoVotacao(Pauta pauta){

        Integer totalVotos = votoPautaService.contagemVotos(pauta);

        Integer votosPositivos = votoPautaService.contagemVotosPositivos(pauta);
        Integer votosNegativos = totalVotos - votosPositivos;

        return new ResultadoResponse(votosPositivos, votosNegativos);
    }


}
