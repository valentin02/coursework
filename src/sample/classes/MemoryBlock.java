package sample.classes;

import java.util.Comparator;

public class MemoryBlock {

    public static Comparator<MemoryBlock> byEnd = new Comparator<MemoryBlock>() {
        @Override
        public int compare(MemoryBlock o1, MemoryBlock o2) {
            return o1.end - o2.end;
        }
    };

    int start;
    int end;

    public MemoryBlock(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" + start +
                ", " + end +
                '}';
    }
}
