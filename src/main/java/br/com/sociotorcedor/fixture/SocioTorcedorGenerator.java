package br.com.sociotorcedor.fixture;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.rest.domain.SocioTorcedorResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Massa de teste
 *
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */
public class SocioTorcedorGenerator {

    public static List<SocioTorcedor> getSocioTorcedores(){
        List<SocioTorcedor> socios = new ArrayList<SocioTorcedor>();
        socios.add(new SocioTorcedor("Maria Silva", "mariasouza@gmail.com",
                LocalDate.of(1980, 01 , 01), "Palmeiras"));

        socios.add(new SocioTorcedor("Jose Silva", "josesilva@gmaiil.com",
                LocalDate.of(1990, 02 , 02), "Corinthians"));

        return socios;
    }

    public static SocioTorcedorResource criaSocioTorcedorResource(){

        return new SocioTorcedorResource("Maria Silva", "mariasouza@gmail.com",
                LocalDate.of(1980, 01 , 01), "TimeDoCoracao");
    }

}