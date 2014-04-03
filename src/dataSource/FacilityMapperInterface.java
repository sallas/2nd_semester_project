/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Facility;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin
 */
public interface FacilityMapperInterface {

    /*
     * Get facilities by type.
     */
    ArrayList<Facility> getFacilities(String type);

    /*
     * Get all facilities.
     */
    ArrayList<Facility> getFacilities();
    
    List<Facility> getFacilityByID(int ID);
    
    List<Facility> search(Object variable, String columnName);
    
}
