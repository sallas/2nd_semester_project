package dataSource;

import domain.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            System.out.println("Fail in ReservationMapper - getReservation");
            System.out.println(e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
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
            statement.setInt(5, r.getNumberNights());
            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fail in ReservationMapper - saveReservation");
            System.out.println(e.getMessage());
        } finally // must close statement
        {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fail in ReservationMapper - saveReservation");
                System.out.println(e.getMessage());
            }
        }
        return rowsInserted == 1;
    }

}
