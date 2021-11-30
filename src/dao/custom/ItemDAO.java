package dao.custom;

import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO {
    String setItemIDS() throws SQLException, ClassNotFoundException;
    boolean add(Item c) throws SQLException, ClassNotFoundException;
    ArrayList<Item> getAll() throws SQLException, ClassNotFoundException;
    boolean update(Item c) throws SQLException, ClassNotFoundException;
    boolean delete(Item c) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemNames() throws SQLException, ClassNotFoundException;
}
