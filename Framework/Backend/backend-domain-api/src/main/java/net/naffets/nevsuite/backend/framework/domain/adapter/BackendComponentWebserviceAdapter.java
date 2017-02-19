package net.naffets.nevsuite.backend.framework.domain.adapter;

import javax.enterprise.context.RequestScoped;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author br4sk4
 * created on 25.12.2016
 */
@RequestScoped
public class BackendComponentWebserviceAdapter {

    public String respond() {
        try {
            URL url = new URL("http://localhost:8080/backend-webservice/ComponentService/respond");
            URLConnection con = url.openConnection();

            String line, message = "";
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while ((line = in.readLine()) != null) message += line;
            in.close();

            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
