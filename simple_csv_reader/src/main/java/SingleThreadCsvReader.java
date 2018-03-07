package main.java;

import main.java.model.AncientAncestry;
import main.java.utils.CellProcessor;
import main.java.utils.NotNull;
import main.java.utils.ParseValueIntoDouble;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * created by Smita Hasole on 02-03-2018
 */

public class SingleThreadCsvReader {

    private static List<Object> temp = new ArrayList<>();

    public static void main(String args[]) {
        BufferedReader br = null;
        boolean isHeader = true;
        String[] headers = null;
        SingleThreadCsvReader singleThreadCsvReader = new SingleThreadCsvReader();
        List<AncientAncestry> ancientAncestries = new ArrayList<>();

        {
            try {
                br = new BufferedReader(new FileReader(new File("D:/simple_csv_reader/src/data/GA000001/GA000001.AncientAncestry.txt")));
                String buffer = null;

                while ((buffer = br.readLine()) != null) {
                    // it will avoid header line(first line)
                    if (!isHeader) {
                        System.out.println("m in singlereader : " + buffer);
                        String[] token = buffer.split("\t");
                        singleThreadCsvReader.process(token, headers);
                        System.out.println("datatype of 2nd variable : " + temp.get(1).getClass().getSimpleName());
                        System.out.println("datatype of 3rd variable : " + temp.get(2).getClass().getSimpleName());
                        System.out.println("datatype of 4th variable: " + temp.get(3).getClass().getSimpleName());

                        System.out.println("m in get string function : " + temp.get(0) + "  : " + temp.get(1) + "  : " + temp.get(2) + "  : " + temp.get(3));
                    } else {
                        headers = buffer.split("\t");
                        isHeader = false;
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Sets up the processors used for the examples.
     */
    private static CellProcessor[] getProcessors() {

        return new CellProcessor[]{
                new NotNull(), // SampleId
                new NotNull(), // SuperPop_CODE
                new ParseValueIntoDouble(), // neanderthal_percent
                new ParseValueIntoDouble() //Denisovan_percent
        };
    }

    AncientAncestry process(String[] tokens, String[] columns) {
        AncientAncestry ancientAncestry = new AncientAncestry();
        if (tokens.length != columns.length) {
            try {
                throw new Exception("not allowed");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ancientAncestry = executeCellProcessor(tokens, tokens, columns, ancientAncestry);
        }
        return ancientAncestry;
    }

    private AncientAncestry executeCellProcessor(String[] tokens, Object[] objects, String[] columns, AncientAncestry ancientAncestry) {


        if (tokens == null) {
            throw new NullPointerException();
        } else {

            temp = Arrays.stream(tokens)
                    .map(token -> checkDatatype(token)).collect(Collectors.toList());
        }
        return ancientAncestry;
    }

    private Object checkDatatype(String token) {

        Object changedToken;
        if (isInteger(token)) {
            changedToken = (Integer.parseInt(token));
        } else if (isDouble(token)) {
            changedToken = Double.parseDouble(token);
        } else {
            changedToken = token;
        }
        return changedToken;
    }

    private Boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {

            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    private Boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}

