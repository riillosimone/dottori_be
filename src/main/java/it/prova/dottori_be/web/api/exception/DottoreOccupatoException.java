package it.prova.dottori_be.web.api.exception;

public class DottoreOccupatoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DottoreOccupatoException(String message) {
		super(message);
	}
}
