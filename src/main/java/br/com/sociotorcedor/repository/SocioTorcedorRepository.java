package br.com.sociotorcedor.repository;

import br.com.sociotorcedor.domain.SocioTorcedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */

@Repository
public interface SocioTorcedorRepository extends MongoRepository<SocioTorcedor, String> {

    Optional<SocioTorcedor> buscaPorNome(String nomeCompleto);

}
