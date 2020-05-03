package main;

import product.Product;
import sql.Sql;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        boolean flag1 = true;
        boolean tmp;
        Product product1 = new Product("un4", 10, 2000);
        Product product2 = new Product("un5", 1, 1991);
        Product product3 = new Product("un16", 20, 1900);
        
        Scanner in = new Scanner(System.in);

        Sql sql = new Sql();
        sql.connectionDB();
        sql.createDB();

        sql.deleteAll();

        sql.addProduct(product1);


        while(flag1) {
            try {
                System.out.println("ведите команду");
                String str = in.nextLine();
                String[] splitStr = str.split(" ");

                switch (splitStr[0]){
                    case "add":
                        Product product = new Product(splitStr[1], Integer.parseInt(splitStr[2]), Integer.parseInt(splitStr[3]));
                        tmp = sql.addProduct(product);
                        if(tmp)
                            System.out.println("Товар добавлен");
                        else
                            System.out.println("Товар с таким именем уже есть");
                        break;
                    case"deleteAll":
                        sql.deleteAll();
                        break;
                    case"filterByPrice":
                        sql.filterByPrice(Integer.parseInt(splitStr[1]), Integer.parseInt(splitStr[2]));
                        break;

                    case"getPriceByName":
                        sql.getPriceByName(splitStr[1]);
                        break;

                    case"deleteByName":
                        tmp = sql.deleteByName(splitStr[1]);
                        if(tmp)
                            System.out.println("Товар удален");
                        else
                            System.out.println("Товарв с таким именем нет");
                        break;
                    case"changePrice":
                        tmp = sql.changePrice(splitStr[1], Integer.parseInt(splitStr[2]));
                        if(tmp)
                            System.out.println("Цена изменена");
                        else
                            System.out.println("Нет такого товара");
                        break;
                    case "showAll":
                        sql.showAll();
                        break;
                    case"q":
                        flag1 = false;
                        break;
                    default:
                        System.out.println("Команда не распознана");
                        break;
                }
            }catch (Exception e){
                System.out.println("Введены не верные параметры в функции");
            }

        }
        sql.closeDB();
    }
}
