/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1_cse_try2;

/**
 *
 * @author Karim
 */
public class Client extends Thread {

    private volatile static int chairs = 50;
    public int id;
    public int soldTickets = 0;
    public static int turn = 1;
    public static boolean[] flag = new boolean[2];

    private void entry() {
        turn = 1 - this.id;
        flag[this.id] = true;
        while (flag[1 - this.id] == true && turn == 1 - this.id) {
            //System.out.println(this.id + " is waiting..");
        }
    }

    private void critical() {
        chairs--;
        soldTickets++;
        flag[this.id] = false;
    }

    private void exit() {
        System.out.println("ID : '" + this.id + "' - soldTickets: " + this.soldTickets);
    }

    @Override
    public void run() {
        while (chairs > 0) {
            entry();
            while (turn == this.id && flag[this.id]) {
                critical();
            }
        }
        exit();
    }

    public void pause() throws InterruptedException {
        double duration = Math.round(Math.random() * 10);
        Thread.sleep((int) duration);
    }

    public Client(int id) {
        this.id = id;
        flag[this.id] = true;
        turn = 1 - this.id;
    }

}
