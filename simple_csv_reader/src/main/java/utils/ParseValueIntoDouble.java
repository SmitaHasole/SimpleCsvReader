package main.java.utils;

/**
 * created by Smita Hasole on 05-03-2018
 */

public class ParseValueIntoDouble implements CellProcessor {

    @Override
    public <T> T execute(Object value) {
         Double result =0.0;
        if (value instanceof Double) {
            result = (Double) value;
        } else if (value instanceof String) {
            try {
                result = new Double((String) value);
            } catch (final NumberFormatException e) {

            }
        } else {
            final String actualClassName = value.getClass().getName();
        }
return (T) result;
    }
}
