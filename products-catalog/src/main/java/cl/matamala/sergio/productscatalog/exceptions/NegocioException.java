package cl.matamala.sergio.productscatalog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase que representa las excepciones de negocio en el sistema.
 * 
 * @author jorgevaldessandoval
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 5468648030304240638L;

	/**
	 * Constructor de la clase.
	 */
	public NegocioException() {
		super();
	}

	/**
	 * Constructor de la clase. Permite setear mensaje.
	 * 
	 * @param message mensaje a setear
	 */
	public NegocioException(final String message) {
		super(message);
	}
}
