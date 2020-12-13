package sample.classes;

public class CPU {

    Core[] cores;

    public CPU(final int coresNumbers) {

        this.cores = new Core[coresNumbers];
        for (int i = 0; i < coresNumbers; i++) {
            this.cores[i] = new Core();
        }
    }

    public void changeStateOfCore(int inndex){
        cores[inndex].isFree = false;

    }

    public Core[] getCores() {
        return cores;
    }

    public void setCores(Core[] cores) {
        this.cores = cores;
    }

    @Override
    public String toString() {
        String result = "[";
        for (Core core :
                cores) {
            result += core.getState() + ", ";
        }
        return result + "]";
    }
}
