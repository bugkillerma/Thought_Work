package com.throughtworks.train.exception;

/**
 * Unchecked exception thrown to indicate that there is no such
 * train station
 * @author "chenyang ma"
 *
 */
public class NoSuchStationException extends RuntimeException {

    public NoSuchStationException() {
        super("no such station");
    }

}
