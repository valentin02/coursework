package sample.classes;

import java.util.ArrayList;

public class MemoryScheduler {

    private static ArrayList<MemoryBlock> memoryBlocks = new ArrayList<>();

    public static MemoryBlock getMemoryBlocks(int index) {
        return memoryBlocks.get(index);
    }

    private static int findFreeBlock(int size){
        int index = -1;
        memoryBlocks.sort(MemoryBlock.byEnd);

        for (int i = 0; i < memoryBlocks.size()-1; i++) {
            if (memoryBlocks.get(i+1).start-memoryBlocks.get(i).end > size){
                index = i;
             break;
            }
        }
        return index;
    }

    public static int fillMemoryBlock(int size){
        return findFreeBlock(size);
    }

    public static void plusMemory(int index, int size){
        memoryBlocks.get(index).end+=size;
    }

    public static void releaseMemoryBlock(MemoryBlock memoryBlock){
        memoryBlocks.remove(memoryBlock);
    }

    public static void add(MemoryBlock memoryBlock){
        memoryBlocks.add(memoryBlock);
    }

    public static String print() {
        memoryBlocks.sort(MemoryBlock.byEnd);
        String result = "[ ";
        for (MemoryBlock memoryBlock : memoryBlocks){
            result+=memoryBlock+" ";
        }
        return result+ " ]";
    }

    @Override
    public String toString() {
        String result = "[";
        for (int i = 0; i < memoryBlocks.size(); i++) {
            result+=memoryBlocks.get(i) + " ";
        }
        return result + "]";
    }
}

