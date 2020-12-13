package sample.classes;

public class Process {

    int id;
    String name;
    int priority;
    int time;
    int memory;
    int memoryBlockIndex;
    int coreIndex;
    int timeIn;
    int burstTime;
    State state;

    public Process(int id) {
        this.id = id;
        this.memory = Utils.getRandomInteger(Configuration.minLimit, Configuration.memoryVolume / 2);
        this.priority = Utils.getRandomInteger(Configuration.maxPriority);
        this.time = Utils.getRandomInteger(Configuration.minLimit, Configuration.maxLimit);
        this.timeIn = ClockGenerator.getTime();
        this.burstTime = 0;
        this.name = "p" + this.id + ".exe";
        this.state = State.Ready;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTime() {
        return time;
    }

    public int getMemory() {
        return memory;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String result;

        return id +
                " { name='" + name + '\'' +
                " priority=" + priority +
                " time=" + time +
                " memory=" + memory +
                " timeIn=" + timeIn +
                " burstTime=" + burstTime +
                " state=" + state +
                "}\n";
    }
}
