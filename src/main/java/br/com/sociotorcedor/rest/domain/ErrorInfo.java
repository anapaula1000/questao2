package br.com.sociotorcedor.rest.domain;

import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Representa informa��es de erros que ser�o retornadas pela API
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */
@ApiModel(value="ErrorInfo", description="Representa informa��es de erros que ser�o retornadas pela API")
public class ErrorInfo {

    @ApiModelProperty(value = "API URL",dataType = "string", required = true)
    public final String url;

    @ApiModelProperty(value = "Mensagem de exce��o",dataType = "string", required = true)
    public final String ex;

    public ErrorInfo(String url, Exception ex) {
        this.url = url;
        this.ex = ex.getLocalizedMessage();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("url", url)
                .add("ex", ex)
                .toString();
    }
}
