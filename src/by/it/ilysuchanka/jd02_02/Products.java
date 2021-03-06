package by.it.ilysuchanka.jd02_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

class Products {
    static HashMap<String, String> productList() {
        HashMap<String, String> products = new HashMap<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getPath(Products.class)
                + "ProductList.txt"))) {
            while ((line = bufferedReader.readLine())!=null) {
                String[] newLine = line.split(":");
                String name = newLine[0].trim();
                String cost = newLine[1].trim();
                products.put(name, cost);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    private static String getPath(Class<Products> Market) {
        String rootProject = System.getProperty("user.dir");
        String relativePath = Market.getName()
                .replace(Market.getSimpleName(), "")
                .replace(".", File.separator);
        return rootProject + File.separator + "src" + File.separator + relativePath;
    }

}
