package com.codecool.shop.config;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Consumer;

@WebListener
public class Initializer implements ServletContextListener {

    public static ServletContext servletContext;

    private JSONObject loadDataFromJSONResource(String fileName) {
        fileName = "/data/" + fileName + ".json";

        File jsonFile = new File(servletContext.getRealPath("/") + fileName);
        StringBuilder fileContent = new StringBuilder();

        try (Scanner scanner = new Scanner(jsonFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                fileContent.append(line).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        try {
            return (JSONObject) (new JSONParser()).parse(fileContent.toString());
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    private void processJSONDataFile(
            String fileName,
            Consumer<JSONObject> processorFunction
    ) {
        JSONArray dataArray = (JSONArray) Objects.requireNonNull(loadDataFromJSONResource(fileName)).get("data");

        for (Object data : dataArray) {
            processorFunction.accept((JSONObject) data);
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servletContext = sce.getServletContext();

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        processJSONDataFile(
                "suppliers",
                (JSONObject currentObject) -> {
                    supplierDataStore.add(new Supplier(
                            (String) currentObject.get("name"),
                            (String) currentObject.get("description")
                    ));
                }
        );

        processJSONDataFile(
                "product-categories",
                (JSONObject currentObject) -> {
                    productCategoryDataStore.add(new ProductCategory(
                            Math.toIntExact((Long) currentObject.get("id")),
                            (String) currentObject.get("name"),
                            (String) currentObject.get("department"),
                            (String) currentObject.get("description")
                    ));
                }
        );

        processJSONDataFile(
                "products",
                (JSONObject currentObject) -> {
                    productDataStore.add(new Product(
                            (String) currentObject.get("name"),
                            ((Long) currentObject.get("defaultPrice")),
                            (String) currentObject.get("currencyString"),
                            (String) currentObject.get("description"),
                            productCategoryDataStore.find(((Long) currentObject.get("categoryId")).intValue()),
                            supplierDataStore.find(((Long) currentObject.get("supplierId")).intValue()),
                            (String) currentObject.get("size"),
                            (String) currentObject.get("color")
                    ));
                }
        );
    }
}
