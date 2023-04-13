package lk.ijse.hostelManagementSystem.repository;

import javafx.scene.control.Alert;
import lk.ijse.hostelManagementSystem.entity.User;
import lk.ijse.hostelManagementSystem.util.SessionFactoryConfiguration;
import org.hibernate.*;
import org.hibernate.criterion.Projections;


public class UserRepository {
    private Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public UserRepository() {
    }

    public boolean addUser(User user){
        transaction = session.beginTransaction();
        try {
            session.save(user);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }

    public String generateUserId() {
//        SessionFactoryConfiguration sessionFactoryConfig = SessionFactoryConfiguration.getInstance();
//        SessionFactory sessionFactory = sessionFactoryConfig.getSessionFactory();
//        Session session = sessionFactory.openSession();
        Session session2= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction2 = null;
        String userId = null;
        try {
            transaction2 = session2.beginTransaction();

            // Find the last user ID from the User table
            Criteria criteria = session2.createCriteria(User.class);
            criteria.setProjection(Projections.max("id"));
            String lastUserId = (String) criteria.uniqueResult();

            // Increment the last user ID to generate the next user ID
            int nextUserId = Integer.parseInt(lastUserId.substring(5)) + 1;
            userId = "User-" + String.format("%03d", nextUserId);

            transaction2.commit();
        } catch (HibernateException e) {
            if (transaction2 != null) transaction2.rollback();
            e.printStackTrace();
        } finally {
            session2.close();

        }
        return userId;
    }







}
