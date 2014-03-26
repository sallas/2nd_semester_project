package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationMapper implements ReservationMapperInterface {

    private final Connection con;

    public ReservationMapper(Connection con) {
        this.con = con;
    }

//    This method returns a reservation object based on the ID parameter.
//    It uses a select sql query from the reservation table.
    @Override
    public Reservation getReservation(int ID) {
        Reservation r = null;
        String SQLString = "select * "
                + "from reservation "
                + "where id = ?";
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                r = new Reservation(ID,
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5));
            }
        }
        catch (SQLException e) {
            System.out.println("Fail in ReservationMapper - getReservation");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                System.out.println("Fail in ReservationMapper - getReservation");
                System.out.println(e.getMessage());
            }
        }
        return r;
    }

    //This method saves a reservation into the database.
    //It gets the reservation object as a parameter.
    //The first SQL query is meant to create a unique ID for the reservation.
    //The second query simply inserts the data into the database table.
    @Override
    public boolean saveReservation(Reservation r) {
        int rowsInserted = 0;
        String SQLString1
                = "select reservationseq.nextval  "
                + "from dual";
        String SQLString2
                = "insert into reservation "
                + "values (?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString1);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                r.setID(rs.getInt(1));
            }

            statement = con.prepareStatement(SQLString2);
            statement.setInt(1, r.getID());
            statement.setInt(2, r.getRoomID());
            statement.setInt(3, r.getCustomerID());
            statement.setDate(4, r.getCheckinDate());
            statement.setDate(5, r.getDepartureDate());
            rowsInserted = statement.executeUpdate();
        }
        catch (SQLException e) {
            System.out.println("Fail in ReservationMapper - saveReservation");
            System.out.println(e.getMessage());
        }
        finally // must close statement
        {
            try {
                statement.close();
            }
            catch (SQLException e) {
                System.out.println("Fail in ReservationMapper - saveReservation");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

    //This method returns a list of all the reservation objects of specified type
    @Override
    public List<Reservation> getAllReservationsOfSpecificType(String type) {
        List<Reservation> allSpecificReservations = new ArrayList();
        String SQLString = "select *"
                + "from reservation where room_id in "
                + " (select id from room where type = ?)";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString);
            statement.setString(1, type);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allSpecificReservations.add(
                        new Reservation(rs.getInt(1), rs.getInt(2),
                                rs.getInt(3), rs.getDate(4), rs.getDate(5)));
            }
        }
        catch (SQLException e) {
            System.out.println("Fail in ReservationMapper -"
                    + " getAllReservationsOfSpecificType");
            System.out.println(e.getMessage());
        }
        finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            }
            catch (SQLException e) {
                System.out.println("Fail in ReservationMapper -"
                        + " getAllReservationsOfSpecificType");
                System.out.println(e.getMessage());
            }
        }
        return allSpecificReservations;
    }
    
    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> allReservations = new ArrayList();
        String SQLString = "select * "
                + "from reservation";
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                allReservations.add(
                        new Reservation(rs.getInt(1), rs.getInt(2),
                        rs.getInt(3), rs.getDate(4), rs.getDate(5)));
            }
        } catch (SQLException e) {
            System.out.println("Fail in ReservationMapper - getAllReservations");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println("Fail in ReservationMapper - getAllReservations");
                System.out.println(e.getMessage());
            }
        }
        return allReservations;
    }
}
