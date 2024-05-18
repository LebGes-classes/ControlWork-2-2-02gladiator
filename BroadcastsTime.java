public class BroadcastsTime implements Comparable<BroadcastsTime>{
    private byte hour;
    private byte minutes;





    public BroadcastsTime(byte hour, byte minutes){
        this.hour = hour;
        this.minutes = minutes;
    }
    public byte getHour() {
        return hour;
    }

    public byte getMinutes() {
        return minutes;
    }
    @Override
    public int compareTo(BroadcastsTime other) {
        if (this.hour != other.hour) {
            return Byte.compare(this.hour, other.hour);
        } else {
            return Byte.compare(this.minutes, other.minutes);
        }
    }
    public boolean after(BroadcastsTime t) {
        return this.compareTo(t) > 0;
    }

    public boolean before(BroadcastsTime t) {
        return this.compareTo(t) < 0;
    }

    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return this.after(t1) && this.before(t2);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d", hour, minutes);
    }


}
