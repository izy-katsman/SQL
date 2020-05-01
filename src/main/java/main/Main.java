package main;

import product.Product;
import sql.Sql;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Product product1 = new Product("un4", 10, 2000);
        Product product2 = new Product("un5", 1, 1991);
        Product product3 = new Product("un16", 20, 1900);

        Sql sql = new Sql();
        sql.connectionDB();
        sql.createDB();

        sql.deleteAll();

        sql.addProduct(product1);
        sql.addProduct(product2);
        sql.addProduct(product3);
        sql.filterByPrice(1, 5000);

        boolean flag = sql.addProduct(product1);// должны получить ошибку добавления
        if(flag)
            System.out.println("Успешно добавили");
        else
            System.out.println("Не смогли добавить");

        sql.filterByPrice(1991, 5000);

        int price = sql.getPriceByName("un4");

        sql.deleteByName("un4");

        sql.getPriceByName("un4");

        sql.changePrice("un16", 5000);

        sql.getPriceByName("un16");

        sql.closeDB();
    }
}
