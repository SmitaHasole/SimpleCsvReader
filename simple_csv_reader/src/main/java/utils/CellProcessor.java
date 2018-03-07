package main.java.utils;

/**
 * created by Smita Hasole on 05-03-2018
 */

public interface CellProcessor {

    <T> T execute(final Object value);
}
