/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.rotwsservice.exception;

/**
 *
 * @author Marco Villavicencio
 */
public class ServiceExeption extends RuntimeException {

    /**
     * 
     * <br>
     * <br>
     * 25 jul. 2019
     * @author RLPOLITO
     */
    private static final long serialVersionUID = 8723965026382651169L;

    /**
     * Instantiates a new service exeption.
     *
     * @param message the message
     * @param cause the cause
     */
    public ServiceExeption(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new service exeption.
     *
     * @param message the message
     */
    public ServiceExeption(final String message) {
        super(message);
    }
}
