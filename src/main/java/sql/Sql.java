package sql;


import product.Product;

import java.sql.*;

public class Sql {
    private static final String DB_URL = "jdbc:h2:/E:/Tests/SQL/src/main/java/sql/Products";
    private static final String DB_Driver = "org.h2.Driver";
    private static  Connection connection;
    private static Statement statement;

    public void connectionDB(){
        try {
            Class.forName(DB_Driver); //Проверяем наличие JDBC драйвера для работы с БД
             connection = DriverManager.getConnection(DB_URL);//соединениесБД без пароля и логина, чистое подключение на локалке
             } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void createDB() throws SQLException {
        String sqlCreateDB = "CREATE TABLE IF NOT EXISTS products (Id INT PRIMARY KEY AUTO_INCREMENT, Prodid INT, title VARCHAR(100) UNIQUE, cost INT)";
        statement = connection.createStatement();
        // создание таблицы
        statement.executeUpdate(sqlCreateDB);
    }


    public void deleteAll(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while(resultSet.next()) {

                int id = resultSet.getInt("id");
                connection.createStatement().executeUpdate("DELETE FROM products WHERE Id = " + id);
               // statement.executeUpdate("DELETE FROM products WHERE Id = " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //собираем все записи и по очереди удаляем
    }


    public boolean deleteByName(String name){
        //если нет такого имени, то возвращаем false, если удалили то возвращаем true
        try {
            statement.executeUpdate("DELETE FROM products WHERE title = '" + name + "'" );
        } catch (SQLException e) {
            return false;
        }
        return true;
    }
    public void closeDB() throws SQLException {
        connection.close();
    }

    public boolean addProduct(Product product){
        try {
            statement.executeUpdate("INSERT into products(prodid, title, cost) VALUES (" + product.getProdid() + ",'" +
                    product.getTitle() + "'," + product.getCost() + ")");
        }catch (SQLException e){
            return false;
        }
        return true;
    }

    public void filterByPrice(int fist, int last) throws SQLException {//это твое d
        ResultSet resultSet = statement.executeQuery("SELECT * FROM products where cost >=" + fist + "AND cost <=" + last);
        System.out.println();

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            int prodid= resultSet.getInt("prodid");
            String title =  resultSet.getString("title");
            int cost = resultSet.getInt("cost");
            System.out.printf("%d %d %s %d\n", id, prodid, title, cost);
        }
    }

    public int getPriceByName(String title){
        int cost = -1;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products where title ='" + title + "'");

            while(resultSet.next()) {
                cost = resultSet.getInt("cost");// ошибка хз почему
                System.out.printf("%s %d \n", "Цена товара " + title + " ", cost);
            }
        } catch (SQLException e) {
            System.out.println("Нет такого товара");
            return cost;
        }
        return cost;
    }

    public boolean changePrice(String title,  int cost){
        try {
            statement.executeUpdate("UPDATE Products SET cost =" + cost + " where title ='" + title + "'");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void showAll(){
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            boolean flag = true;
            while(resultSet.next()) {
                flag = false;
                int id = resultSet.getInt("id");
                int prodid= resultSet.getInt("prodid");
                String title =  resultSet.getString("title");
                int cost = resultSet.getInt("cost");
                System.out.printf("%d %d %s %d\n", id, prodid, title, cost);
            }
            if(flag)
                System.out.println("Товаров еще нет");
        } catch (SQLException e) {
            System.out.println("Нет такого товара");
        }
    }
}
