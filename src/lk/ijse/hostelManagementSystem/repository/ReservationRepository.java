package lk.ijse.hostelManagementSystem.repository;

import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.util.SessionFactoryConfiguration;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ReservationRepository {
    private Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;



    public String generateReservationId() {
        Session session2= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction2 = null;
        String reservationId = "Res-001"; // Default value for the first reservation Id
        try {
            transaction2 = session2.beginTransaction();

            // Check if Reservation table is empty
            Criteria criteria = session2.createCriteria(Reservation.class);
            criteria.setProjection(Projections.rowCount());
            Long rowCount = (Long) criteria.uniqueResult();

            // If the Reservation table is not empty, generate the next reservation Id
            if (rowCount > 0) {
                // Find the last Student ID from the Student table
                criteria = session2.createCriteria(Reservation.class);
                criteria.setProjection(Projections.max("id"));
                String lastReservationId = (String) criteria.uniqueResult();

                // Increment the last Reservation ID to generate the next Reservation ID
                int nextReservationId = Integer.parseInt(lastReservationId.substring(4)) + 1;
                reservationId = "Res-" + String.format("%03d", nextReservationId);
            }

            transaction2.commit();
        } catch (HibernateException e) {
            if (transaction2 != null) transaction2.rollback();
            e.printStackTrace();
        } finally {
            session2.close();
        }
        return reservationId;
    }



    public List<Reservation> getAll(){
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> reservationCriteriaQuery = builder.createQuery(Reservation.class);
            reservationCriteriaQuery.from(Reservation.class);

            return session.createQuery(reservationCriteriaQuery).getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public boolean saveReservation(Reservation reservation) {

        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(reservation);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return false;
    }



}
