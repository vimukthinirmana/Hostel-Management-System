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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@NoArgsConstructor

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
        Transaction transaction = null;
        boolean deleted = false;
        try {
            transaction = session.beginTransaction();
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
}
