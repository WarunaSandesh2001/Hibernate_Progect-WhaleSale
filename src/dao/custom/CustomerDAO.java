package dao.custom;

import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    String setCustomerIDS() throws SQLException, ClassNotFoundException;
    boolean add(Customer c) throws SQLException, ClassNotFoundException;
    ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException;
    boolean update(Customer c) throws SQLException, ClassNotFoundException;
    boolean delete(Customer c) throws SQLException, ClassNotFoundException;
}
