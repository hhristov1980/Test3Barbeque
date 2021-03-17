package main.barbeque;

import main.connector.DBConnector;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Owner extends Thread{
    private Barbeque barbeque;

    public Owner(Barbeque barbeque){
        this.barbeque = barbeque;
    }

    @Override
    public void run() {
        while (true) {
            //TODO SLEEP
            readAndFillInDB();
            printMeatOwnShopSold();
            printNumberOfSales();
            printTotalPleskavitsi();
            printAllShopsAndSales();
            printMostSoldGarnish();
            printPalnozarnestBreadSold();
            printTheLeastGarnishOwnShopSold();
            printUsedKilosGarnishOwnShop();
            earningsOwnShop();




        }
    }

    private void readAndFillInDB(){
        File file = new File("Report "+ LocalDate.now()+".txt");
        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String [] items = line.split(", ");
//                ps.println(breadType+", "+meatType+", "+ garnituraType+", "+amountOfOrder+", "+ LocalDateTime.now());
                String breadType = items[0];
                String meatType = items[1];
                String garrnituraType = items[2];
                double price = Double.parseDouble(items[3]);
                String time = items[4];
                int bread = 2;
                int meat = 0;
                int garnitura = 0;
                if(breadType.equals("White")){
                    bread =1;
                }
                switch (meatType){
                    case "Steak":
                        meat = 3;
                        break;
                    case "Pleskavitsa":
                        meat = 2;
                        break;
                    default:
                        meat = 1;
                        break;
                }
                switch (garrnituraType){
                    case "Lyutenitsa":
                        garnitura = 2;
                        break;
                    case "SnowWhite":
                        garnitura = 3;
                        break;
                    case "CabbageCarrots":
                        garnitura = 4;
                        break;
                    case "TomatoesCucumbers":
                        garnitura = 5;
                        break;
                    default:
                        garnitura = 1;
                        break;

                }
                insertQuery(bread,meat,garnitura);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        file.delete();
    }

    private void insertQuery(int bread, int meat, int garnitura){
        String inputQuery = "INSERT INTO sales(shop_id, bread_type_id, meat_type_id, garnish_type_id, date_created) VALUES (?,?,?,?,?);";
        Connection connection = DBConnector.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(inputQuery)) {
            preparedStatement.setInt(1,26);
            preparedStatement.setInt(2,bread);
            preparedStatement.setInt(3,meat);
            preparedStatement.setInt(4,garnitura);
            preparedStatement.setDate(5, Date.valueOf(LocalDate.now()));
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void printNumberOfSales(){
        String selectQuery = "SELECT COUNT(shop_id) FROM sales WHERE shop_id = 26;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            rs.next();
            System.out.println("Total sales of shop id 26: "+rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printTotalPleskavitsi(){
        String selectQuery = "SELECT COUNT(meat_type_id) FROM sales WHERE meat_type_id = 2;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            rs.next();
            System.out.println("Total sales of pleskavitsi in all barbeque shops: "+rs.getInt(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printAllShopsAndSales(){
        String selectQuery = "SELECT sh.name, COUNT(s.shop_id) AS total_sales FROM shops sh JOIN sales s ON s.shop_id = sh.id GROUP BY s.shop_id ORDER BY total_sales DESC;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            while(rs.next()){
                System.out.println("Shop: "+rs.getString(1)+" sales: "+rs.getInt(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printMostSoldGarnish(){
        String selectQuery = "SELECT g.name, COUNT(s.garnish_type_id) AS number_garnish FROM garnish_types g LEFT JOIN sales s ON s.garnish_type_id = g.id GROUP BY g.id ORDER BY number_garnish DESC LIMIT 1;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);

            while(rs.next()){
                System.out.println("Garnish: "+rs.getString(1)+" sales: "+rs.getInt(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printPalnozarnestBreadSold(){
        String selectQuery = "SELECT b.name, COUNT(s.bread_type_id) AS number_bread FROM bread_types b LEFT JOIN sales s ON s.bread_type_id = b.id GROUP BY b.id HAVING b.id = 2;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            System.out.println("Sales of "+rs.getString(1)+": "+rs.getInt(2)+" pieces!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printMeatOwnShopSold(){
        String selectQuery = "SELECT m.name, COUNT(s.meat_type_id) AS number_meat FROM meat_types m LEFT JOIN sales s ON s.meat_type_id = m.id WHERE s.shop_id = 26 GROUP BY m.id ORDER BY number_meat DESC LIMIT 1;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            System.out.println("Most sold meat is: "+rs.getString(1)+": "+rs.getInt(2)+" pieces!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printTheLeastGarnishOwnShopSold(){
        String selectQuery = "SELECT g.name, COUNT(s.garnish_type_id) AS number_garnish FROM garnish_types g LEFT JOIN sales s ON s.garnish_type_id = g.id WHERE s.shop_id = 26 GROUP BY g.id ORDER BY number_garnish ASC LIMIT 1;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            System.out.println("The least sold garnish is: "+rs.getString(1)+": "+rs.getInt(2)+" pieces!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void printUsedKilosGarnishOwnShop(){
        String selectQuery = "SELECT g.name, COUNT(s.garnish_type_id)*0.5 AS number_garnish FROM garnish_types g LEFT JOIN sales s ON s.garnish_type_id = g.id WHERE s.shop_id = 26 GROUP BY g.id ORDER BY number_garnish;";
        Connection connection = DBConnector.getInstance().getConnection();
        try(Statement st = connection.createStatement()){
            ResultSet rs = st.executeQuery(selectQuery);
            System.out.println("The total kilos of used garnish is: "+rs.getString(1)+": "+rs.getInt(2)+" kilos!");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void earningsOwnShop(){
        String selectQuery1 = "SELECT g.name, COUNT(s.garnish_type_id), g.price AS number_garnish FROM garnish_types g LEFT JOIN sales s ON s.garnish_type_id = g.id WHERE s.shop_id = 26 GROUP BY g.id ORDER BY number_garnish;";
        String selectQuery2 = "SELECT m.name, COUNT(s.meat_type_id), m.price AS number_meat FROM meat_types m LEFT JOIN sales s ON s.meat_type_id = m.id WHERE s.shop_id = 26 GROUP BY m.id ORDER BY number_meat;";
        String selectQuery3 = "SELECT b.name, COUNT(s.bread_type_id), b.price AS number_bread FROM bread_types b LEFT JOIN sales s ON s.bread_type_id = b.id WHERE s.shop_id = 26 GROUP BY b.id ORDER BY number_bread;";
        Connection connection = DBConnector.getInstance().getConnection();
        double turnover = 0;
        try(Statement st1 = connection.createStatement();
        Statement st2 = connection.createStatement();
        Statement st3 = connection.createStatement()){
            connection.setAutoCommit(false);
            ResultSet rs1 = st1.executeQuery(selectQuery1);
            while (rs1.next()){
                turnover+=rs1.getInt(2)*rs1.getDouble(2);
            }
            ResultSet rs2 = st2.executeQuery(selectQuery2);
            while (rs2.next()){
                turnover+=rs2.getInt(2)*rs2.getDouble(2);
            }
            ResultSet rs3 = st3.executeQuery(selectQuery3);
            while (rs3.next()){
                turnover+=rs3.getInt(2)*rs3.getDouble(2);
            }
            connection.commit();
            System.out.println("Total earnigs for shop 26: "+turnover);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
