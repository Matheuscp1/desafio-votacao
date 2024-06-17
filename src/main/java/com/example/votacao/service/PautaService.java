package com.example.votacao.service;

import com.example.votacao.exceptions.NoContentException;
import com.example.votacao.exceptions.NotFoundException;
import com.example.votacao.model.Pauta;
import com.example.votacao.repository.PautaRepository;
import com.example.votacao.service.interfaces.IPautaService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class PautaService implements IPautaService {

    private final PautaRepository pautaRepository;

    private final SessaoService sessaoService;
    
    public PautaService(PautaRepository pautaRepository, SessaoService sessaoService){
        this.pautaRepository = pautaRepository;
        this.sessaoService = sessaoService;
    }

    @Transactional
    @Override
    public Pauta cadastrarPauta(Pauta pauta) {

        return pautaRepository.save(
                Pauta.builder()
                        .titulo(pauta.getTitulo())
                        .descricao(pauta.getDescricao())
                        .sessao(sessaoService.validarSessao(pauta.getSessao()))
                        .ativo(true)
                        .build()
        );
    }

    public Pauta buscarPautaPorID(String id) {

        Optional<Pauta> pauta = pautaRepository.findById(id);

        if(pauta.isEmpty() || !pauta.get().getAtivo())
            throw new NotFoundException("A pauta de codigo: "+id+" não existe!");

        return pauta.get();
    }

    @Override
    public Page<Pauta> listarPautas(Pageable pageable){

        Optional<Page<Pauta>> pautas = pautaRepository.findAllByAtivoTrue(pageable);

        if (pautas.isEmpty())
            throw new NoContentException("Não existem pautas cadastradas!");

        return pautas.get();
    }

    @Transactional
    @Override
    public Pauta deletarPauta(String id){

        Optional<Pauta> optionalPauta = pautaRepository.findById(id);

        if (optionalPauta.isEmpty() || !optionalPauta.get().getAtivo())
            throw new NotFoundException("A pauta de codigo: "+id+" não existe!");

        Pauta pauta = optionalPauta.get();

        pauta.setAtivo(false);

        return pauta;
    }

    @Transactional
    @Override
    public Pauta atualizarPauta(Pauta pauta) {

        Pauta pautaAtualizada = buscarPautaPorID(pauta.getId());

        pautaAtualizada.setTitulo(pauta.getTitulo());
        pautaAtualizada.setDescricao(pauta.getDescricao());
        pautaAtualizada.setSessao(sessaoService.validarSessao(pauta.getSessao()));

        return pautaAtualizada;
    }

}
