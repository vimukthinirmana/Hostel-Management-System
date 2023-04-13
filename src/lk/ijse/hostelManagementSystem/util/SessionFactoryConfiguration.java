package lk.ijse.hostelManagementSystem.util;

import lk.ijse.hostelManagementSystem.entity.Reservation;
import lk.ijse.hostelManagementSystem.entity.Room;
import lk.ijse.hostelManagementSystem.entity.Student;
import lk.ijse.hostelManagementSystem.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionFactoryConfiguration {
    private static SessionFactoryConfiguration sessionFactoryConfiguration;
    private SessionFactory sessionFactory;

    private SessionFactoryConfiguration(){
        Properties properties = new Properties();

        try{
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("hibernate.properties"));
        }catch (Exception e) {
            e.printStackTrace();
        }

        Configuration configuration = new Configuration();//.configure()
        configuration .addAnnotatedClass(Reservation.class);
        configuration .addAnnotatedClass(Student.class);
        configuration .addAnnotatedClass(Room.class);
        configuration .addAnnotatedClass(User.class);

        sessionFactory = configuration.mergeProperties(properties).buildSessionFactory();
    }
    public static SessionFactoryConfiguration getInstance(){
        return (sessionFactoryConfiguration == null) ? sessionFactoryConfiguration = new SessionFactoryConfiguration()
                :sessionFactoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
