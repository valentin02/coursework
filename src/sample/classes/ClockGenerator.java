package sample.classes;

import sample.Controller;

import java.util.Date;
import java.util.TimerTask;

public class ClockGenerator extends TimerTask {
    private static int time;
    static Scheduler scheduler = new Scheduler(4, 4096);

    public static int getTime() {
        return time;
    }

    public static void incTime(int tact) {
        time += tact;
    }

    public static void incTime() {
        time++;
    }

    public static String printQ() {
        return scheduler.queue.toString();
    }
    public static String printP() {
        return scheduler.processQueue.toString();
    }
    public static String printR() {
        return scheduler.processQueue.toString();
    }

    @Override
    public void run() {
        // System.out.println(MemoryScheduler.print());
        scheduler.queue.add(10);
        //System.out.println(scheduler.toString());
        while (true){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println("_______________________________"+getTime());
            scheduler.algorithm();
            scheduler.incTimeProcess();
            if (getTime()%10 == 0){
                scheduler.queue.add(2);
                //System.out.println(scheduler.toString());
            }
            incTime();
            scheduler.interruptProcess();
        }
    }
}
