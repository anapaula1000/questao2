package br.com.sociotorcedor.fixture;

import br.com.sociotorcedor.rest.domain.CampanhaResource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Massa de teste
 *
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */
public class CampanhaGenerator {

    public static List<CampanhaResource> getCampanhasResource(){
        List<CampanhaResource> campanhas = new ArrayList<CampanhaResource>();
        campanhas.add(new CampanhaResource("Campanha 2",  "TimeDoCoracao",
                LocalDate.of(2018,02,10),LocalDate.of(2018,02,20)));

        campanhas.add(new CampanhaResource("Campanha 4",  "TIME-1004",
                LocalDate.of(2018,02,21),LocalDate.of(2018,02,27)));

        return campanhas;
    }

}