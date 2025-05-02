package org.example;

import org.example.Flat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlatDAOImpl implements FlatDAO {

    private final Connection conn;

    public FlatDAOImpl(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void createTable() {

    }

    @Override
    public void addFlat(String district, String address, int area, int rooms, float price){
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO flat (district,address,area,rooms,price) VALUES(?, ?, ?, ?, ?)")) {
                st.setString(1, district);
                st.setString(2, address);
                st.setInt(3, area);
                st.setInt(4, rooms);
                st.setFloat(5, price);
                st.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<org.example.Flat> sortByPrice(int from, int to) {
        List<Flat> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM flat WHERE price BETWEEN " + from + " AND " + to)) {
                    while (rs.next()) {
                        Flat flat = new Flat();

                        flat.setId(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setAddress(rs.getString(3));
                        flat.setArea(rs.getInt(4));
                        flat.setRooms(rs.getInt(5));
                        flat.setPrice(rs.getFloat(6));

                        res.add(flat);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<org.example.Flat> areaFrom(int from){
        List<Flat> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM flat WHERE area > " + from)) {
                    while (rs.next()) {
                        Flat flat = new Flat();

                        flat.setId(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setAddress(rs.getString(3));
                        flat.setArea(rs.getInt(4));
                        flat.setRooms(rs.getInt(5));
                        flat.setPrice(rs.getInt(6));

                        res.add(flat);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<org.example.Flat> getAll() {
        List<Flat> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM flat")) {
                    while (rs.next()) {
                        Flat flat = new Flat();

                        flat.setId(rs.getInt(1));
                        flat.setDistrict(rs.getString(2));
                        flat.setAddress(rs.getString(3));
                        flat.setArea(rs.getInt(4));
                        flat.setRooms(rs.getInt(5));
                        flat.setPrice(rs.getInt(6));

                        res.add(flat);
                    }
                }
            }

            return res;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
