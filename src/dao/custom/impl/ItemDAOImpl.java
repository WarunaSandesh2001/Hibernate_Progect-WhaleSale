package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Customer;
import entity.Item;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    @Override
    public String setItemIDS() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT itemCode FROM Item ORDER BY itemCode DESC LIMIT 1");
        String id = (String) sqlQuery.uniqueResult();
        transaction.commit();
        session.close();

        if (id!=null){
            int tempId = Integer.
                    parseInt(id.split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "I-00"+tempId;
            }else if(tempId<=99){
                return "I-0"+tempId;
            }else{
                return "I-"+tempId;
            }
        }else{
            return "I-001";
        }
    }
    @Override
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(item);
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
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT * FROM Item");
        sqlQuery.addEntity(Item.class);
        List<Item> result = sqlQuery.list();

        transaction.commit();
        session.close();
        ArrayList<Item> items = (ArrayList<Item>) result;
        return items;
    }

    @Override
    public boolean update(Item c) {
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
    public boolean delete(Item c) throws SQLException, ClassNotFoundException {
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

    @Override
    public ArrayList<String> getAllItemNames() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery sqlQuery = session.createSQLQuery("SELECT name FROM Item");
        sqlQuery.addEntity(Item.class);
        List<String> result = sqlQuery.list();

        transaction.commit();
        session.close();
        ArrayList<String> items = (ArrayList<String>) result;
        return items;
    }
}
