/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.FacilityBooking;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author martin
 */
public interface FacilityBookingMapperInterface {

    boolean checkAvailableFacilityBooking(FacilityBooking fb);

    List<FacilityBooking> getAllBookings();

    List<FacilityBooking> getAllBookingsOfSpecificDate(Date date);

    boolean saveFacilityBooking(FacilityBooking fb);

    List<FacilityBooking> getAllBookingsOfSpecificDateAndUser(Date date, int userID);

    List<FacilityBooking> getAllBookingsOfSpecificDateTimeslotUser(
            Date date, int userID, int timeslot);

    public List<FacilityBooking> getAllBookingsOfSpecificDateTimeslotFacility(
            Date date, int timeslot, int facilityID);
}
