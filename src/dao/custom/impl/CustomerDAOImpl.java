package dao.custom.impl;

import dao.custom.CustomerDAO;
import entity.Customer;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public String setCustomerIDS() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT customerID FROM Customer ORDER BY customerID DESC LIMIT 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null){
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "C-00"+tempId;
            }else if(tempId<=99){
                return "C-0"+tempId;
            }else{
                return "C-"+tempId;
            }
        }else{
            return "C-001";
        }
    }

    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(customer);
        }catch (Exception e){
            transaction.commit();
            session.close();
            return false;
        }
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM Customer");
        sqlQuery.addEntity(Customer.class);
        List<Customer> result = sqlQuery.list();

        transaction.commit();
        session.close();
        ArrayList<Customer> customers = (ArrayList<Customer>) result;
        return customers;
    }

    @Override
    public boolean update(Customer c) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(c);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.commit();
            session.close();
            return false;
        }
    }

    @Override
    public boolean delete(Customer c) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(c);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e) {
            transaction.commit();
            session.close();
            return false;
        }
    }
}
