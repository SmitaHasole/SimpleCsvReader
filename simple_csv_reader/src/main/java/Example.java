package main.java;

import main.java.model.AncientAncestry;
import main.java.utils.CellProcessor;
import main.java.utils.NotNull;
import main.java.utils.ParseValueIntoDouble;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * created by Smita Hasole on 06-03-2018
 */

public class Example {

        private static List<Object> temp = new ArrayList<>();

        public static void main(String args[]) {
            BufferedReader br = null;
            boolean isHeader = true;
            String[] headers = null;
           Example Example = new Example();
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
                            AncientAncestry ancientAncestry = Example.process(token, headers);
                            System.out.println("m in get string function : " + ancientAncestry.toString());
//                        ancientAncestries.add(ancientAncestry);
//                     ancientAncestry = new AncientAncestry( temp.get(0),temp.get(0), temp.get(0), temp.get(0));

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
                ancientAncestry = executeCellProcessor(tokens, columns, ancientAncestry);
            }
            return ancientAncestry;
        }

        private AncientAncestry executeCellProcessor(String[] tokens, String[] columns, AncientAncestry ancientAncestry) {

            if (tokens == null) {
                throw new NullPointerException();
            } else {

                Method[] methods = ancientAncestry.getClass().getDeclaredMethods();
                Method m = null;
                Class cls = null;
                Object obj = null;

                //String parameter
                Class[] paramString = new Class[1];
                try {
                    cls = Class.forName("main.java.model.AncientAncestry");
                    obj = cls.newInstance();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < tokens.length; i++) {

                    try (Scanner scanner = new Scanner(tokens[i])) {

                        if (scanner.hasNextInt()) {
                            if (methods[i].getName().contains("set" + columns[i])) {
                                paramString[0] = Integer.class;
                                m = cls.getDeclaredMethod(methods[i].getName(), paramString);
                                System.out.println("getDeclaredMethod(methods[i].getName());" + m.getName() + "and :" + columns[i] + "token is: " + tokens[i]);
                                m.invoke(obj,Integer.parseInt(tokens[i]));
                                scanner.close();
                            }
                        } else if (scanner.hasNextDouble()) {
                            if (methods[i].getName().contains("set" + columns[i])) {
                                paramString[0] = Double.class;
                                m = cls.getDeclaredMethod(methods[i].getName(), paramString);
                                System.out.println("getDeclaredMethod(methods[i].getName());" + m.getName() + "and :" + columns[i] + "token is: " + tokens[i]);
                                m.invoke(obj,Double.parseDouble(tokens[i]));
                                scanner.close();

                            }
                        } else if (scanner.hasNextBoolean()) {
                            paramString[0] = Boolean.class;
                            if (methods[i].getName().contains("set" + columns[i])) {
                                m = cls.getDeclaredMethod(methods[i].getName(), paramString);
                                m.invoke(obj,Boolean.parseBoolean(tokens[i]));
                                scanner.close();

                            }
                        } else {
                            if (methods[i].getName().contains("set" + columns[i])) {
                                paramString[0] = String.class;
                                m = cls.getDeclaredMethod(methods[i].getName(), paramString);
                                System.out.println("getDeclaredMethod(methods[i].getName());" + m.getName() + "and :" + columns[i] + "token is: " + tokens[i]);
                                m.invoke(obj,tokens[i]);
                                scanner.close();

                            }
                        }

                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                ancientAncestry = (AncientAncestry) obj;
            }
            return ancientAncestry;
        }
    }



