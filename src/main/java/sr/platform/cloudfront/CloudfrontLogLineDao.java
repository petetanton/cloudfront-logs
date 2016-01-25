package sr.platform.cloudfront;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sr.platform.util.HibernateUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tantop01 on 18/01/16.
 */
public class CloudfrontLogLineDao {
    Transaction transaction = null;
    Session session;
    public void openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }

    public void commit() {
        session.getTransaction().commit();
    }

    public void close() {
        session.flush();
        session.close();
        HibernateUtil.getSessionFactory().close();
    }
    public void addCloudFrontLogLine(CloudfrontLogLine cloudfrontLogLine) {
        session.save(cloudfrontLogLine);
    }

    public List<CloudfrontLogLine> getAllUsers() {
        List<CloudfrontLogLine> logLines = new ArrayList<CloudfrontLogLine>();
        try {
            logLines = session.createQuery("from CloudfrontLogLine ").list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return logLines;
    }

    public List<CloudfrontLogLine> getRequestsSince(String since) {
        String queryString = "from CloudfrontLogLine where date >= '" + since + "' order by date asc";
        Query query = session.createQuery(queryString);
//        query.setDate("dateSince", since);
        List<CloudfrontLogLine> logLines = query.list();

        return logLines;
    }

    public CloudfrontLogLine getUserById(int userid) {
        CloudfrontLogLine logLine = null;
        try {
            String queryString = "from cloudfront_logs where id = :id";
            Query query = session.createQuery(queryString);
            query.setInteger("id", userid);
            logLine = (CloudfrontLogLine) query.uniqueResult();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return logLine;
    }

}
