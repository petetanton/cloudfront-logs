package sr.platform.cloudfront;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Date;

/**
 * Created by tantop01 on 17/01/16.
 */
public class CloudfrontLogLine {

//    date          time        x-edge-location sc-bytes    c-ip            cs-method   cs(Host)                        cs-uri-stem                                         sc-status
//    2015-02-05	11:26:18	LHR50	        572         194.82.96.254	GET	        d1n3qd835lrhmu.cloudfront.net	/test/testStream/playlist_b380074_w1577396706.abst	200

//    cs(Referer)                                                                                           cs(User-Agent)
//    http://streamingrocket.com/player/GetStreamingPlayer.php?partnercode=tap&streamid=4&type=live&pro=hds	Mozilla/5.0%2520(Macintosh;%2520Intel%2520Mac%2520OS%2520X%252010_8_5)%2520AppleWebKit/600.1.17%2520(KHTML,%2520like%2520Gecko)%2520Version/6.2%2520Safari/537.85.10

//    cs-uri-query  cs(Cookie)                                                                                  x-edge-result-type  x-edge-request-id
//    -	            _pk_id.3.bf8f=74419095992153be.1422963731.4.1423135507.1422988697.;%2520_pk_ses.3.bf8f=*	Miss	            InF7l5n_tRFy_WftOHMPTvzDp98yIzsLGOkLOXcw6sPuYnSDi0tHog==

//    x-host-header                 cs-protocol     cs-bytes    time-taken
//    cdn100.streamingrocket.com	http	        530	        0.099

    private int id;
    private Date date;
    private String xEdgeLocation;
    private long scBytes;
    private int clientIp;
    private String httpMethod;
    private String cloudfrontDomain;
    private String requestStem;
    private int httpStatus;
    private String referrer;
    private String clientUserAgent;
    private String queryString;
    private String clientCookie;
    private String xEdgeResultType;
    private String xEdgeRequestId;
    private String xHostHeader;
    private String clientProtocol;
    private long csBytes;
    private Double timeTaken;

    private DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateHourMinuteSecond();

    public CloudfrontLogLine() {
    }

    public CloudfrontLogLine(String date, String time, String xEdgeLocation, String scBytes, String clientIp, String httpMethod, String cloudfrontDomain, String requestStem, String httpStatus, String referrer, String clientUserAgent, String queryString, String clientCookie, String xEdgeResultType, String xEdgeRequestId, String xHostHeader, String clientProtocol, String csBytes, String timeTaken) {
        this.date = dateTimeFormatter.parseDateTime(date + "T" + time).toDate();
        this.xEdgeLocation = xEdgeLocation;
        this.scBytes = Long.parseLong(scBytes);
//        this.clientIp = Integer.parseInt(clientIp);
        this.clientIp = 0;
        this.httpMethod = httpMethod;
        this.cloudfrontDomain = cloudfrontDomain;
        this.requestStem = requestStem;
        this.httpStatus = Integer.parseInt(httpStatus);
        this.referrer = referrer;
        this.clientUserAgent = clientUserAgent;
        this.queryString = queryString;
        this.clientCookie = clientCookie;
        this.xEdgeResultType = xEdgeResultType;
        this.xEdgeRequestId = xEdgeRequestId;
        this.xHostHeader = xHostHeader;
        this.clientProtocol = clientProtocol;
        this.csBytes = Long.parseLong(csBytes);
        this.timeTaken = Double.parseDouble(timeTaken);
    }

    public void setClientCookie(String clientCookie) {
        this.clientCookie = clientCookie;
    }

    public void setClientIp(int clientIp) {
        this.clientIp = clientIp;
    }

    public void setClientProtocol(String clientProtocol) {
        this.clientProtocol = clientProtocol;
    }

    public void setClientUserAgent(String clientUserAgent) {
        this.clientUserAgent = clientUserAgent;
    }

    public void setCloudfrontDomain(String cloudfrontDomain) {
        this.cloudfrontDomain = cloudfrontDomain;
    }

    public void setCsBytes(long csBytes) {
        this.csBytes = csBytes;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDateTimeFormatter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public void setRequestStem(String requestStem) {
        this.requestStem = requestStem;
    }

    public void setScBytes(long scBytes) {
        this.scBytes = scBytes;
    }

    public void setTimeTaken(Double timeTaken) {
        this.timeTaken = timeTaken;
    }

    public void setxEdgeLocation(String xEdgeLocation) {
        this.xEdgeLocation = xEdgeLocation;
    }

    public void setxEdgeRequestId(String xEdgeRequestId) {
        this.xEdgeRequestId = xEdgeRequestId;
    }

    public void setxEdgeResultType(String xEdgeResultType) {
        this.xEdgeResultType = xEdgeResultType;
    }

    public void setxHostHeader(String xHostHeader) {
        this.xHostHeader = xHostHeader;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientCookie() {
        return clientCookie;
    }

    public int getClientIp() {
        return clientIp;
    }

    public String getClientProtocol() {
        return clientProtocol;
    }

    public String getClientUserAgent() {
        return clientUserAgent;
    }

    public String getCloudfrontDomain() {
        return cloudfrontDomain;
    }

    public long getCsBytes() {
        return csBytes;
    }

    public Date getDate() {
        return date;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getQueryString() {
        return queryString;
    }

    public String getReferrer() {
        return referrer;
    }

    public String getRequestStem() {
        return requestStem;
    }

    public long getScBytes() {
        return scBytes;
    }

    public Double getTimeTaken() {
        return timeTaken;
    }

    public String getxEdgeLocation() {
        return xEdgeLocation;
    }

    public String getxEdgeRequestId() {
        return xEdgeRequestId;
    }

    public String getxEdgeResultType() {
        return xEdgeResultType;
    }

    public String getxHostHeader() {
        return xHostHeader;
    }
}
