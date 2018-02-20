package br.com.sociotorcedor.exception;

/**
 * Faz a validação pelo email
 * @author : Ana Paula anapaulasilva1000@gmail.com
 */
public class SocioTorcedorJaCadastradoException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public SocioTorcedorJaCadastradoException() {
        super("Usuário já cadastrado");
    }
}