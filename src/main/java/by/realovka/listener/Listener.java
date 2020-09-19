package by.realovka.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class Listener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        try {
            Class.forName("org.postgresql.Driver");
            se.getSession().setAttribute("connection", DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
                    "Vorobei55"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

    }
}
