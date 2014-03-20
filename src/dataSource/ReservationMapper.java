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

    @Override
    public boolean saveReservation(Reservation r) {
        
    }

}
