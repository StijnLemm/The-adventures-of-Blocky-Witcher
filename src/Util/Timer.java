package Util;

public class Timer {

    private long startMilliseconds;
    private long timerAmount;

    public Timer(long timerAmount) {
        this.timerAmount = timerAmount;
        this.startMilliseconds = System.currentTimeMillis();
    }

    public void setTimerAmount(long timerAmount) {
        this.timerAmount = timerAmount;
    }

    public boolean timeout() {
        return System.currentTimeMillis() - this.startMilliseconds > this.timerAmount;
    }

    public void reset() {
        this.startMilliseconds = System.currentTimeMillis();
    }
}
