package org.example;

import org.example.Flat;

import java.util.List;

// CRUD
public interface FlatDAO {
    void createTable();
    List<org.example.Flat> sortByPrice(int from, int to);
    List<org.example.Flat> getAll();
    List<org.example.Flat> areaFrom(int from);
    void addFlat(String district, String address, int area, int rooms, float price);
}
