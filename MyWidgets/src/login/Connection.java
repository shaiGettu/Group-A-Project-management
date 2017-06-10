package login;

/**
 * Created by Shai Gettu on 03-Jun-17.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Connection {

    public String register(Register register) {
        String message = "";
        try {
            Gson gsonToSend = new GsonBuilder().create();
            String jsonOutput = gsonToSend.toJson(register);
            // send as HTTP get request
            URL url = new URL("http://localhost/PHP/login/index.php?request=" + jsonOutput);
            URLConnection conn = url.openConnection();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                message = line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("The message is" + message);
        return message;
    }

    public String login(Login login) {
        String message = "";
        try {
            // make JSON string
            Gson gsonToSend = new GsonBuilder().create();
            String jsonOutput = gsonToSend.toJson(login);
            // send as HTTP get request
            URL url = new URL("http://localhost/PHP/login/index.php?request=" + jsonOutput);
            URLConnection conn = url.openConnection();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                message = line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    public String forgotPassword(ForgotPassword forgotPassword) {
        String message = "";
        try {
            // make JSON string
            Gson gsonToSend = new GsonBuilder().create();
            String jsonOutput = gsonToSend.toJson(forgotPassword);
            // send as HTTP get request
            URL url = new URL("http://localhost/PHP/login/index.php?request="
                    + jsonOutput);
            URLConnection conn = url.openConnection();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                message = line;
            }
            rd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(message);
        return message;
    }

}

