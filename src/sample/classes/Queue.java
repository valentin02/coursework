package sample.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Queue {

    ArrayList<Process> queue;
    private int lastID;

    public  static Comparator<Process> byPriority = new Comparator<Process>() {
        @Override
        public int compare(Process o1, Process o2) {
            return o1.priority - o2.priority;
        }
    };

    public Queue() {
        this.queue = new ArrayList<>();
        this.lastID = 1;
    }

    public void add(Process process) {
        this.queue.add(process);
    }

    public boolean add() {
        Process process = new Process(this.lastID++);
        this.add(process);
        return false;
    }

    public void add(final int N) {
        for (int i = 0; i < N; i++) {
            this.add();
        }
    }

    public void sortQueue(){
        queue.sort(byPriority);
    }

    public void deleteProcess(Process process){
        queue.remove(process);
    }

    @Override
    public String toString() {
        String result = "";
        for (Process process :
                queue) {
            result += process.toString();
        }
        return result;
    }
}
