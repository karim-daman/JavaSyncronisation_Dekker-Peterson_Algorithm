/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp2_cse_baker;

/**
 *
 * @author Karim
 */
public class TP2_CSE_Baker extends Thread {

    /**
     * @param args the command line arguments
     */
    private volatile static int chairs = 50;
    public int id;
    private int soldTickets = 0;
    public static final int numberOfThreads = 11;
    private static volatile boolean[] choosing = new boolean[numberOfThreads];
    private static volatile int[] priority = new int[numberOfThreads];

    @Override
    public void run() {
        lock(id);
        while (chairs > 0) {
            chairs--;
            unlock(id);
            soldTickets++;
            try {
                this.pause();
            } catch (InterruptedException ex) {

            }
            lock(id);
        }
        unlock(id);
        System.out.println("ID: '" + id + "' - baught Tickets: " + soldTickets + " Remaining Chairs: " + chairs);
    }

    public void lock(int id) {
        choosing[id] = true;
        priority[id] = findMax() + 1;
        choosing[id] = false;
        for (int j = 0; j < numberOfThreads; j++) {
            if (j == id) {
                continue;
            }
            while (choosing[j]) { /* nothing */ }
            while (priority[j] != 0 && (priority[id] > priority[j] || (priority[id] == priority[j] && id > j))) { /* nothing */ }
        }
    }

    private void unlock(int id) {
        priority[id] = 0;
    }

    private int findMax() {
        int m = priority[0];
        for (int i = 1; i < priority.length; i++) {
            if (priority[i] > m) {
                m = priority[i];
            }
        }
        return m;
    }

    public void pause() throws InterruptedException {
        int duration = (int) Math.round(Math.random() * 10);
        Thread.sleep(duration);
    }

    public TP2_CSE_Baker(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        for (int i = 1; i < numberOfThreads; i++) {
            (new TP2_CSE_Baker(i)).start();
        }
    }
}
