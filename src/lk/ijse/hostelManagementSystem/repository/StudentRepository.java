package lk.ijse.hostelManagementSystem.repository;

import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.entity.User;
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
public class StudentRepository {
    private Session session= SessionFactoryConfiguration.getInstance().getSession();
    private Transaction transaction;

    public boolean addStudent(Student student){
        transaction = session.beginTransaction();
        try {
            session.save(student);
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        return false;
    }


    public String generateStudentId() {
        Session session2= SessionFactoryConfiguration.getInstance().getSession();
        Transaction transaction2 = null;
        String studentId = "Stud-001"; // Default value for the first Student ID
        try {
            transaction2 = session2.beginTransaction();

            // Check if Student table is empty
            Criteria criteria = session2.createCriteria(Student.class);
            criteria.setProjection(Projections.rowCount());
            Long rowCount = (Long) criteria.uniqueResult();

            // If the Student table is not empty, generate the next Student ID
            if (rowCount > 0) {
                // Find the last Student ID from the Student table
                criteria = session2.createCriteria(Student.class);
                criteria.setProjection(Projections.max("id"));
                String lastStudentId = (String) criteria.uniqueResult();

                // Increment the last Student ID to generate the next Student ID
                int nextStudentId = Integer.parseInt(lastStudentId.substring(5)) + 1;
                studentId = "Stud-" + String.format("%03d", nextStudentId);
            }

            transaction2.commit();
        } catch (HibernateException e) {
            if (transaction2 != null) transaction2.rollback();
            e.printStackTrace();
        } finally {
            session2.close();
        }
        return studentId;
    }



    public List<Student> getAll(){
        try{
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
            studentCriteriaQuery.from(Student.class);

            return session.createQuery(studentCriteriaQuery).getResultList();

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
