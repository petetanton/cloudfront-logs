package sr.platform.controller;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import sr.platform.cloudfront.CloudfrontLogLine;
import sr.platform.cloudfront.CloudfrontLogLineDao;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/stats")
public class ApiStats {
    private DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateHourMinuteSecond();
    private CloudfrontLogLineDao dao = new CloudfrontLogLineDao();
    @Path("/no-of-requests")
    @GET
    public Response getNoOfRequests(@QueryParam("since") String sinceStr) {
        Date sinceDate = dateTimeFormatter.parseDateTime(sinceStr).toDate();
        List<CloudfrontLogLine> logs;
        dao.openSession();
        logs = dao.getRequestsSince(sinceStr);
        dao.close();
        HashMap<Date, Integer> responsesPerMin = new HashMap<Date, Integer>();
        for (CloudfrontLogLine log : logs) {
            if (responsesPerMin.containsKey(log.getDate())) {
                responsesPerMin.put(log.getDate(), responsesPerMin.get(log.getDate()) + 1);
            } else {
                responsesPerMin.put(log.getDate(), 1);
            }
        }
        String output = "\"dates\" : [";
        for (Map.Entry<Date, Integer> entry : responsesPerMin.entrySet()) {
            if (output.length() > 11) output += ",";
            output += "{\"date\":\"" + entry.getKey().toString() + "\",\"responseCount\":" + entry.getValue() + "}";
        }
        output += "]";
        return Response.ok().entity(output).header("Content-Type", "application/json").build();
    }
}
