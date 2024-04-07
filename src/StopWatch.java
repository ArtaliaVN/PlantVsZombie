public final class StopWatch {

    private long startMillis = System.currentTimeMillis();

    public double getElapsedTimeInSeconds() {
        return (System.currentTimeMillis() - this.startMillis) / 1000.0;
    }

    public void reset() {
        this.startMillis = System.currentTimeMillis();
    }

    public int intValue(){
        double temp =getElapsedTimeInSeconds();
        int value = (int)temp;
        return value;
    }
}