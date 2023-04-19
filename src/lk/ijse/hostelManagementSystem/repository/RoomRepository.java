package lk.ijse.hostelManagementSystem.repository;

import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.util.SessionFactoryConfiguration;
import lombok.NoArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;



public class RoomRepository {
    private Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;



    public boolean addRoom(Room room) {
        Session session= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(room);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;

    }



    public String generateRoomId() {
        Session session2= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction2 = null;
        String roomId = "RM-001"; // Default value for the first room ID
        try {
            transaction2 = session2.beginTransaction();

            // Check if room table is empty
            Criteria criteria = session2.createCriteria(Room.class);
            criteria.setProjection(Projections.rowCount());
            Long rowCount = (Long) criteria.uniqueResult();

            // If the room table is not empty, generate the next room ID
            if (rowCount > 0) {
                // Find the last room ID from the room table
                criteria = session2.createCriteria(Room.class);
                criteria.setProjection(Projections.max("id"));
                String lastRoomId = (String) criteria.uniqueResult();

                // Increment the last room ID to generate the next room ID
                int nextRoomId = Integer.parseInt(lastRoomId.substring(3)) + 1;
                roomId = "RM-" + String.format("%03d", nextRoomId);
            }

            transaction2.commit();
        } catch (HibernateException e) {
            if (transaction2 != null) transaction2.rollback();
            e.printStackTrace();
        } finally {
            session2.close();
        }
        return roomId;
    }


    public List<Room> getAll(){
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Room> roomCriteriaQuery = builder.createQuery(Room.class);
            roomCriteriaQuery.from(Room.class);

            return session.createQuery(roomCriteriaQuery).getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public boolean deleteRoom(Object room) {

        Transaction transaction = session.beginTransaction();
        boolean deleted = false;
        try {
            session.delete(room);
            transaction.commit();
            deleted = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return deleted;
    }

    public boolean updateRoom(Room room) {
        Session session3= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction3 = session3.beginTransaction();
        try {
            session3.update(room);
            transaction3.commit();
            return true;
        }catch (Exception e){
            transaction3.rollback();
            e.printStackTrace();
        }finally {
            session3.close();
        }
        return false;
    }

    public Room getRoomTypes(String type){
        try{
            return session.get(Room.class, type);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public List<String> getRoomTypes(){
        String hql = "SELECT type from Room";
        Query<String> query = session.createQuery(hql);
        List<String> results = query.list();
        return results;
    }




    public Room getRoomByType(String roomType) {
        Room room = null;
        Session session = null;
        try {
            session = SessionFactoryConfiguration.getInstance().getSession();
            session.beginTransaction();
            Query<Room> query = session.createQuery("FROM Room WHERE type = :roomType", Room.class);
            query.setParameter("roomType", roomType);
            room = query.uniqueResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException("Failed to get room by type: " + e.getMessage(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return room;
    }




    public Room getRoomById(String roomId) {
        try {
            Session session = SessionFactoryConfiguration.getInstance().getSession();
            session.beginTransaction();
            Room room = session.get(Room.class,roomId);
            session.getTransaction().commit();
            session.close();//
            return room;
        } catch (Exception e) {
            throw new RuntimeException("Failed to get room by ID: " + e.getMessage(), e);
        }finally {
//            session.close();//
        }
    }



    public void saveRoom(Room room) {
        Transaction transaction = null;
        try {
            Session session = SessionFactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save room: " + e.getMessage(), e);
        }
    }




}
