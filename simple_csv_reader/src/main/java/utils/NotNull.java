package main.java.utils;

/**
 * created by Smita Hasole on 05-03-2018
 */

public class NotNull implements CellProcessor {
    @Override
    public <T> T execute(Object value) {
        if(value==null){
         throw new NullPointerException();
        }
        else {
            return (T) value;
        }

    }
}
