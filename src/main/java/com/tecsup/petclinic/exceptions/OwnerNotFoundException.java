package com.tecsup.petclinic.exceptions;

/**
 * Se lanza cuando no se encuentra un dueño (owner) en la base de datos.
 */
public class OwnerNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public OwnerNotFoundException(String message) {
		super(message);
	}
}
