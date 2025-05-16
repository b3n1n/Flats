package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// DB > JDBC (H) > DAO > App

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             Scanner sc = new Scanner(System.in))
        {
            FlatDAOImpl dao = new FlatDAOImpl(conn, "flats");

            while (true) {
                System.out.println("1: view flats");
                System.out.println("2: sort flats by price");
                System.out.println("3: sort flats by area");
                System.out.println("4: add flat");
                System.out.print("-> ");

                String s = sc.nextLine();
                switch (s) {
                    case "1":
                        List<Flat> list = dao.getAll();
                        for (Flat flat : list) {
                            System.out.println(flat);
                        }
                        break;
                    case "2":
                        System.out.println("Set price FROM: ");
                        int number = sc.nextInt();
                        System.out.println("Set price TO: ");
                        int number2 = sc.nextInt();
                        List<Flat> list2 = dao.sortByPrice(number, number2);
                        for (Flat flat : list2) {
                            System.out.println(flat);
                        }
                        break;
                    case "3":
                        System.out.println("Set area FROM: ");
                        int area = sc.nextInt();
                        List<Flat> list3 = dao.areaFrom(area);
                        for (Flat flat : list3) {
                            System.out.println(flat);
                        }
                    case "4":
                        System.out.println("Set district: ");
                        String district = sc.nextLine();
                        System.out.println("Set address: ");
                        String address = sc.nextLine();
                        System.out.println("Set area: ");
                        int areaCreate = sc.nextInt();
                        System.out.println("Set rooms: ");
                        int rooms = sc.nextInt();
                        System.out.println("Set price: ");
                        int price = sc.nextInt();
                        dao.addFlat(district, address, areaCreate, rooms, price);
                        break;
                    default:
                        return;
                }
            }
        }
    }
}
