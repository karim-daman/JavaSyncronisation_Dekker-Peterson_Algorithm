/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1_cse_try2;

import static java.lang.Thread.sleep;

/**
 *
 * @author Karim
 */
public class TP1_CSE_try2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Client c1 = new Client(0);
        Client c2 = new Client(1);
        c1.start();
        c2.start();

        sleep(100);

        System.out.println("--------------------------------------------------> TOTAL = " + (c1.soldTickets + c2.soldTickets));
        if (c1.soldTickets + c2.soldTickets > 50) {
            throw new Exception(" SOLD TICKETS CANNOT BE > 50 ");
        }

    }

}
