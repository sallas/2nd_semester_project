
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.HotelUser;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kaloyan
 */
public class UnitOfWorkProcess {

    private final AdministratorMapper adminMapper;
    private final ArrayList<HotelUser> dirtyHotelUsers;

    public UnitOfWorkProcess(AdministratorMapper adminMapper) {
        this.adminMapper = adminMapper;
        this.dirtyHotelUsers = new ArrayList<>();

    }

    public void registerDirtyHotelUser(HotelUser hotelUser) {
        if (!dirtyHotelUsers.contains(hotelUser) // if not all ready registered in any list
                ) {
            dirtyHotelUsers.add(hotelUser);
        }
    }

    boolean commitHotelUser(Connection con) {

        boolean status = true;  // will be set false if any part of transaction fails    
        try {
            //=== system transaction - starts
            con.setAutoCommit(false);

            status = adminMapper.updateUsername(dirtyHotelUsers);
            status = adminMapper.updatePassword(dirtyHotelUsers);
            status = adminMapper.updateStatus(dirtyHotelUsers);
            if (!status) {
                throw new Exception("Process Update  aborted");
            }
            //=== system transaction - ends with succes

            con.commit();
        } catch (Exception e) {
            //=== system transaction - ends with roll back
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            status = false;
        }
        return status;
    }
}












