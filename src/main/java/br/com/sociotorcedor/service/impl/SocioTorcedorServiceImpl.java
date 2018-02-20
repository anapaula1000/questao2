package br.com.sociotorcedor.service.impl;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.repository.SocioTorcedorRepository;
import br.com.sociotorcedor.service.SocioTorcedorService;

/**
 * Servi�o para funcionalidades relacionadas a SocioTorcedor
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */

@Service
@Validated
public class SocioTorcedorServiceImpl implements SocioTorcedorService {

    private static final Logger logger = LoggerFactory.getLogger(SocioTorcedorService.class);

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    public SocioTorcedor cadastrarSocioTorcedor(String nome, String email, LocalDate dataNascimento, String timeCoracao)
            throws DuplicateKeyException {

        final SocioTorcedor socioTorcedor = new SocioTorcedor(nome, email, dataNascimento, timeCoracao);

        if(logger.isDebugEnabled()){
            logger.debug("Cadastrando S�cio Torcedor : {}", socioTorcedor);

        }
        return socioTorcedorRepository.save(socioTorcedor);

    }
}
