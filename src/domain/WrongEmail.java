/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author martin
 */
public class WrongEmail extends Exception {
    public WrongEmail(String message) {
        super(message);
    }
}