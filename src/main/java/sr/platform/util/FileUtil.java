package sr.platform.util;

import javax.ws.rs.core.Response;
import java.io.*;

/**
 * Created by tantop01 on 19/01/16.
 */
public class FileUtil {

    public static String readStream(InputStream is) {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    public static String readFile(String filepath) throws IOException {
        File file = new File(filepath);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        String str = new String(data, "UTF-8");
        return str;
    }

    public Response returnFileAsResponse(String resource, String contentType) {
        try {
            return Response.ok().entity(readFile(getClass().getResource(resource).getPath())).header("Content-Type", contentType).build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(404).entity("{\"status\":404,\"message\":\"Cannot find resource\",\"resource\":\"" + resource + "\"}").header("Content-Type", "application/json").build();
        }

    }
}
