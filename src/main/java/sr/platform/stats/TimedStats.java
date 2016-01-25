package sr.platform.stats;

import java.util.Date;

/**
 * Created by tantop01 on 19/01/16.
 */
public class TimedStats {

    private Date date;
    private int requests;

    public TimedStats(Date date, int requests) {
        this.date = date;
        this.requests = requests;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRequests() {
        return requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public void incrementRequests() {
        this.requests++;
    }
}
