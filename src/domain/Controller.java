package domain;

import dataSource.DBFacade;
import java.sql.Date;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Controller {
        DBFacade instance = DBFacade.getInstance();

    public List<String> getRooms() {
        List<String> roomList = new ArrayList();
        List<Room> tempRoomList = instance.getAllRooms();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
        for (Room r : tempRoomList) {
            Date date = instance.getRoomAvailabilityDate(r.getID());
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String s = formatter.format(date);
            if (date.compareTo(currentDate) < 0) {
                s = "NOW";
            }
            roomList.add(Integer.toString(r.getID()) + "_" + r.getType() + "_" + s);
        }
        return roomList;
    }
}
