package br.com.sociotorcedor.rest.api;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.exception.SocioTorcedorJaCadastradoException;
import br.com.sociotorcedor.rest.domain.CampanhaResource;
import br.com.sociotorcedor.rest.domain.ErrorInfo;
import br.com.sociotorcedor.rest.domain.SocioTorcedorResource;
import br.com.sociotorcedor.service.CampanhaService;
import br.com.sociotorcedor.service.SocioTorcedorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;
import java.util.List;

/**
 *  @author : Ana Paula anapaulasilva1000@gmail.com
 */
@RestController
@RequestMapping("/v1/socios")
@Api(value = "S�cio Torcedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
        tags = {"Endpoints do S�cio Torcedor"}, description = "Lida com todas as requis��es da API Rest de s�cio torcedor", basePath = "/api/v1/socios")
public class SocioTorcedorController {

    private static final Logger logger = LoggerFactory.getLogger(SocioTorcedorController.class);

    @Autowired
    private SocioTorcedorService socioTorcedorService;

    @Autowired
    private CampanhaService campanhaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod = "retornaStatusCreated")
    @ApiOperation(value = "Cria um novo s�cio torcedor com base nos parametros passados",
            notes = "Cria um novo s�cio torcedor e retorna a lista de campanhas associadas ao time do torcedor" +
                    ",caso o servi�o de campanhas esteja indispon�vel o servi�o cria o s�cio torcedor e retorna o status " +
                    "201 criado",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400 , message = "Conflict"),
            @ApiResponse(code = 409 , message = "Bad Request"),
            @ApiResponse(code = 500 , message = "Internal Server Error")})
    public ResponseEntity<List<CampanhaResource>> cadastrarSocioTorcedor(@Valid @RequestBody SocioTorcedorResource socioTorcedorResource){

        try {

            final List<CampanhaResource> campanhasByTimeCoracao =
                    campanhaService.getCampanhasByTimeCoracao(socioTorcedorResource.getTimeCoracao());

            final SocioTorcedor socioTorcedor =
                    socioTorcedorService.cadastrarSocioTorcedor(socioTorcedorResource.getNomeCompleto(), socioTorcedorResource.getEmail(),
                    socioTorcedorResource.getDataNascimento(), socioTorcedorResource.getTimeCoracao());
            if(logger.isDebugEnabled()){
                logger.debug("S�cio Torcedor : {} cadastrado com sucesso", socioTorcedor);
            }
            return new ResponseEntity<List<CampanhaResource>>(campanhasByTimeCoracao,
                        HttpStatus.CREATED);

        }catch (DuplicateKeyException ex){
            if(logger.isDebugEnabled()){
                logger.debug("S�cio Torcedor com e-mail: {} j� cadastrado", socioTorcedorResource.getEmail());
            }
            throw new SocioTorcedorJaCadastradoException();
        }
    }


    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SocioTorcedorJaCadastradoException.class)
    @ResponseBody ErrorInfo
    handleSocioTorcedorJaCadastradoException( SocioTorcedorJaCadastradoException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    ErrorInfo handleInternalServerError(Exception ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() , ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody ErrorInfo
    handleHttpMessageNotReadableException( HttpMessageNotReadableException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody ErrorInfo
    handleValidationException( MethodArgumentNotValidException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }

}