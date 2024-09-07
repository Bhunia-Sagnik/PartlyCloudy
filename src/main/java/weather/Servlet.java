package weather;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.sendRedirect("index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city = request.getParameter("city");
		String apiKey = "0a9e27f130e003f53233cba057600ad0";
		String appURL = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;
		
		try {
			URL url = new URL(appURL);
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			
			Scanner sc = new Scanner(reader);
			StringBuilder sb = new StringBuilder();
			
			while(sc.hasNext()) {
				sb.append(sc.nextLine());
			}
			
			sc.close();
			
			Gson gson = new Gson();
			JsonObject json = gson.fromJson(sb.toString(), JsonObject.class); 
			
			Date now = new Date();
	        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, dd-MM-yyyy");
	        String date = dateFormat.format(now);
			
			double temperatureKelvin = json.getAsJsonObject("main").get("temp").getAsDouble();
            String temperature = String.format("%.2f", (temperatureKelvin - 273.15));
            
            double temperatureFeel = json.getAsJsonObject("main").get("feels_like").getAsDouble();
            String feelLike = String.format("%.2f", (temperatureFeel - 273.15));
            
            int humidity = json.getAsJsonObject("main").get("humidity").getAsInt();
            
            double windSpeed = json.getAsJsonObject("wind").get("speed").getAsDouble();
            
            String weatherCondition = json.getAsJsonArray("weather").get(0).getAsJsonObject().get("main").getAsString();
            
            request.setAttribute("date", date);
            request.setAttribute("city", city);
            request.setAttribute("temperature", temperature);
            request.setAttribute("feelLike", feelLike);
            request.setAttribute("weatherCondition", weatherCondition); 
            request.setAttribute("humidity", humidity);    
            request.setAttribute("windSpeed", windSpeed);
            request.setAttribute("weatherData", sb.toString());
            
            connection.disconnect();
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}
