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

	public void register(Register register) {
		try {	
			Gson gsonToSend = new GsonBuilder().create();
			String jsonOutput = gsonToSend.toJson(register);
			System.out.println(jsonOutput);
			// send as HTTP get request
			URL url = new URL("http://localhost/myfiles/login/register.php?registration=" + jsonOutput);
			URLConnection conn = url.openConnection();			 
			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void login(Login login) {
		try {
			// make JSON string
			Gson gsonToSend = new GsonBuilder().create();
			String jsonOutput = gsonToSend.toJson(login);
			
			// send as HTTP get request
			URL url = new URL("http://localhost/myfiles/login/login.php?login=" + jsonOutput);
			URLConnection conn = url.openConnection();

			// Get the response
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
