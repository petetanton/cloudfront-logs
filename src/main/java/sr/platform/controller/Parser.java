package sr.platform.controller;

import sr.platform.cloudfront.CloudfrontLogLine;
import sr.platform.cloudfront.CloudfrontLogLineDao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tantop01 on 18/01/16.
 */
public class Parser {
    private static CloudfrontLogLineDao dao = new CloudfrontLogLineDao();

    public static void parseFile(File logFile) throws IOException {
        dao.openSession();
        long count = 0;
//        Set<String> xEdgeLocation = new HashSet<String>();
        Set<String> xEdgeLocation = new HashSet<String>();
        BufferedReader br = new BufferedReader(new FileReader(logFile));
        List<CloudfrontLogLine> logLines = new ArrayList<CloudfrontLogLine>();
//        List<String[]> logLines = new ArrayList<String[]>();
        for (String line; (line = br.readLine()) != null; ) {
//            logLines.add(line.split("\\t", -1));
            String[] split = line.split("\\t", -1);
            try {
                logLines.add(new CloudfrontLogLine(split[0],
                        split[1],
                        split[2],
                        split[3],
                        split[4],
                        split[5],
                        split[6],
                        split[7],
                        split[8],
                        split[9],
                        split[10],
                        split[11],
                        split[12],
                        split[13],
                        split[14],
                        split[15],
                        split[16],
                        split[17],
                        split[18]
                ));
            } catch (ArrayIndexOutOfBoundsException e) {
//                e.printStackTrace();
                System.out.println("ArrayIndexOutOfBounds");
            }
            for (CloudfrontLogLine logLine : logLines) {
//                System.out.println(logLine.getDate().toString());
                count += logLine.getScBytes();
                dao.addCloudFrontLogLine(logLine);
                try {
                    xEdgeLocation.add(logLine.getxEdgeLocation());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("scBytes total: " + count);
        for (String location : xEdgeLocation) {
            System.out.println(location);
        }

        dao.commit();
        dao.close();



    }
}
