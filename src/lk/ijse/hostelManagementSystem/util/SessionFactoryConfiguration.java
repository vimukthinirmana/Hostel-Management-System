package lk.ijse.hostelManagementSystem.util;

import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private SessionFactory sessionFactory;

    private SessionFactoryConfiguration(){
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Reservation.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Room.class)
                .addAnnotatedClass(User.class);

        sessionFactory = configuration.buildSessionFactory();
    }
    public static SessionFactoryConfiguration getInstance(){
        return (sessionFactoryConfiguration == null) ? sessionFactoryConfiguration = new SessionFactoryConfiguration()
                :sessionFactoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
