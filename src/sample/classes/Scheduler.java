package sample.classes;

public class Scheduler {
    Queue queue;
    RejectsQueue rejectsQueue;
    ProcessQueue processQueue;
    CPU cpu;
    MemoryScheduler memoryScheduler;
    int indexOfBlock = -3;
    private int tempindex = 0;

    public Scheduler(final int cpuCoresNumbers, int memoryVolume) {
        this.queue = new Queue();
        this.processQueue = new ProcessQueue();
        this.rejectsQueue = new RejectsQueue();
        this.cpu = new CPU(cpuCoresNumbers);
        this.memoryScheduler = new MemoryScheduler();
        Configuration.memoryVolume = memoryVolume;
        this.init();
    }

    public void init() {
        memoryScheduler.add(new MemoryBlock(0, 100));
        memoryScheduler.add(new MemoryBlock(2100, 2100));
        memoryScheduler.add(new MemoryBlock(3100, 3100));
        memoryScheduler.add(new MemoryBlock(1100, 1100));
        memoryScheduler.add(new MemoryBlock(4000, Configuration.memoryVolume));
    }

    public void algorithm() {
        queue.sortQueue();
        for (int i = 0; i < cpu.cores.length; i++) {
            if (cpu.cores[i].isFree) {
                while (cpu.cores[i].isFree) {
                    if (queue.queue.size() == 0) {
                        break;
                    }
                    indexOfBlock = MemoryScheduler.fillMemoryBlock(queue.queue.get(tempindex).memory);
                    if (indexOfBlock != -1 && indexOfBlock != -3) {
                        MemoryScheduler.plusMemory(indexOfBlock, queue.queue.get(tempindex).memory);
                        cpu.cores[i].isFree = false;
                        queue.queue.get(tempindex).memoryBlockIndex = indexOfBlock;
                        queue.queue.get(tempindex).state = State.Waiting;
                        queue.queue.get(tempindex).coreIndex = i;
                        processQueue.queue.add(queue.queue.get(tempindex));
                        queue.deleteProcess(queue.queue.get(tempindex));
                    } else {
                        rejectsQueue.add(queue.queue.get(tempindex));
                        queue.deleteProcess(queue.queue.get(tempindex));
                    }
                }
            }
        }
    }

    public void releaseMemory() {
        for (int i = 0; i < processQueue.queue.size(); i++) {
            if ((processQueue.queue.get(i).state.equals(State.Finished) || processQueue.queue.get(i).state.equals(State.Interrupted)) && processQueue.queue.get(i).memoryBlockIndex != -1) {
                MemoryScheduler.plusMemory(processQueue.queue.get(i).memoryBlockIndex, (processQueue.queue.get(i).memory * -1));
                processQueue.queue.get(i).memoryBlockIndex = -1;
            }
        }
    }

    public void incTimeProcess() {
        for (int i = 0; i < processQueue.queue.size(); i++) {

            if (processQueue.queue.get(i).state.equals(State.Waiting)) {
                processQueue.queue.get(i).state = State.Running;
            } else if (!(processQueue.queue.get(i).state.equals(State.Interrupted) || processQueue.queue.get(i).state.equals(State.Finished))) {
                processQueue.queue.get(i).burstTime += ClockGenerator.getTime() - (ClockGenerator.getTime() - 1);
            }
            if (processQueue.queue.get(i).burstTime == processQueue.queue.get(i).time) {
                processQueue.queue.get(i).state = State.Finished;
                cpu.cores[processQueue.queue.get(i).coreIndex].isFree = true;
                this.releaseMemory();
            }
        }

    }

    public void interruptProcess() {
        for (int i = 0; i < processQueue.queue.size(); i++) {
            if (queue.queue.size() == 0) {
                break;
            }
            if (processQueue.queue.get(i).state.equals(State.Running)) {
                this.processQueue.sortQueue();
                if (processQueue.queue.get(i).priority > queue.queue.get(tempindex).priority) {
                    processQueue.queue.get(i).state = State.Interrupted;
                    this.releaseMemory();
                    indexOfBlock = MemoryScheduler.fillMemoryBlock(queue.queue.get(tempindex).memory);
                    cpu.cores[processQueue.queue.get(i).coreIndex].isFree = true;
                    if (indexOfBlock != -1 && indexOfBlock != -3) {
                        MemoryScheduler.plusMemory(indexOfBlock, queue.queue.get(tempindex).memory);
                        queue.queue.get(tempindex).memoryBlockIndex = indexOfBlock;
                        queue.queue.get(tempindex).state = State.Waiting;
                        queue.queue.get(tempindex).coreIndex = processQueue.queue.get(i).coreIndex;
                        processQueue.queue.add(queue.queue.get(tempindex));
                        queue.deleteProcess(queue.queue.get(tempindex));
                        cpu.cores[processQueue.queue.get(i).coreIndex].isFree = false;
                    }
                }
            }
        }
    }


    public String toString() {
        queue.sortQueue();

        String result = "Queue : \n" + queue.toString() + "Process: \n" + processQueue.toString() + "\nRejects: \n" + rejectsQueue.toString();
        return result +
                "\ncpu=" + cpu +
                "\nmemoryScheduler=" + memoryScheduler +
                '}';
    }
}
