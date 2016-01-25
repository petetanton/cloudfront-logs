package sr.platform.stats;

import sr.platform.cloudfront.CloudfrontLogLine;

import java.util.*;

public class CloudfrontStatsAnalysis {
    private List<CloudfrontLogLine> logList;

    public CloudfrontStatsAnalysis(List<CloudfrontLogLine> logList) {
        this.logList = logList;
    }

    public Set<TimedStats> getNumberOfRequestsBySecond() {
        Set<TimedStats> timedStats = new HashSet<TimedStats>();
        boolean hasBeenPut = false;
        for (CloudfrontLogLine log : logList) {
            timestatsLoop:
            for (TimedStats stat : timedStats) {
                if (stat.getDate().equals(log.getDate())) {
                    stat.incrementRequests();
                    hasBeenPut = true;
                    break timestatsLoop;
                }
            }
            if (!hasBeenPut) {
                timedStats.add(new TimedStats(log.getDate(), 1));
            }
            hasBeenPut = false;
        }
        return timedStats;
    }
}
