package com.todo.gitapp.exceptions;

public class CustomeExceptions extends RuntimeException {
    /**
     * @implNote Empty Constructor
     */
    public CustomeExceptions() {
    }

    /**
     * @param message : message
     * @implNote Constructor One Parameter Pass
     */
    public CustomeExceptions(String message) {
        super(message);
    }

    /**
     * @param message   : message
     * @param throwable : throwable
     * @implNote Two parameter pass constructor
     */
    public CustomeExceptions(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * @param throwable : throwable
     * @implNote Constructor pass in Throwable class object
     */
    public CustomeExceptions(Throwable throwable) {
        super(throwable);
    }

}

