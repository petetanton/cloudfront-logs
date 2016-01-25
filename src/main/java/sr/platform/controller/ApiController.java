package sr.platform.controller;

import sr.platform.cloudfront.CloudfrontLogLineDao;
import sr.platform.stats.CloudfrontStatsAnalysis;
import sr.platform.stats.TimedStats;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.zip.GZIPInputStream;

/**
 * Created by tantop01 on 18/01/16.
 */
@Path("/api")
public class ApiController {

    @GET
    @Path("/gunzip")
    public Response uncompressAllFiles() {
        File folder = new File("/etc/sr-lbp/cloudfront-logs");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    Parser.parseFile(gunzipFile(file));
                    gunzipFile(file).delete();
                    file.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    return Response.serverError().entity(e.getMessage()).build();
                }
            }
        }
        return Response.ok().build();
    }

    @GET
    @Path("/avg")
    public Response getAverate() {
        CloudfrontLogLineDao dao = new CloudfrontLogLineDao();
        dao.openSession();
        CloudfrontStatsAnalysis stats = new CloudfrontStatsAnalysis(dao.getAllUsers());
        dao.close();
        long totalBytes = 0;

        for (TimedStats timedStat : stats.getNumberOfRequestsBySecond()) {
            System.out.println("At " + timedStat.getDate().toString() + " there were  " + timedStat.getRequests() + " requests");
        }
        System.out.println("Total bytes: " + totalBytes);
        return Response.ok().build();
    }


    private File gunzipFile(File file) throws Exception {
        byte[] buffer = new byte[1024];
        File outputFile = null;
        try {
            outputFile = new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf(".gz")));
        } catch (Exception e) {
            throw new Exception("No file exists with extension gz");
        }
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream(file));
            FileOutputStream out = new FileOutputStream(outputFile);

            int length;
            while ((length = gzipInputStream.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            gzipInputStream.close();
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return outputFile;
    }

}
