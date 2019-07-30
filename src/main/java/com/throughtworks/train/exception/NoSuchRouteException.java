package com.throughtworks.train.exception;

/**
 * Unchecked exception thrown to indicate that there is no path between two
 * train station
 * @author "chenyang ma"
 *
 */
public class NoSuchRouteException extends RuntimeException {

    public NoSuchRouteException() {
        super("no such route");
    }
}
