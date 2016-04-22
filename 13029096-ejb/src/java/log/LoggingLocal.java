/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

import javax.ejb.Local;

/**
 *
 * @author Enda
 */
@Local
public interface LoggingLocal {
    public void sendMessageToQueue(String message);
    
}
