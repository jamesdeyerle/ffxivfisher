package ffxiv.fisher.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class UrlFetchService {

	private static final Logger log = Logger.getLogger(UrlFetchService.class.getName());
	
	@Inject
	public UrlFetchService() {
		
	}
	
	public String getRawData(String stringUrl) {
		log.info("Getting raw data from: " + stringUrl);
		URL url;
		try {
			url = new URL(stringUrl);			
		} catch (MalformedURLException e) {
			log.log(Level.SEVERE, "Failed to parse source URL.", e);
			return null;
		}
		
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(url.openStream()));

	        StringBuilder sb = new StringBuilder();
	        String inputLine;
	        while ((inputLine = in.readLine()) != null) {
	            sb.append(inputLine);
	        }
	        return sb.toString();
	        
		} catch (IOException e) {
			log.log(Level.SEVERE, "Failed to read data from source.", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e2) {
					log.log(Level.SEVERE, "Failed to close input stream.", e2);
				}
			}
		}
        
        return null;
	}
}
