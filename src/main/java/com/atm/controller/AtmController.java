package com.atm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {
	private String atmJsonString = null;

	@GetMapping("atm")
	public String getAtm(@RequestParam(value = "q") String text, @RequestParam(value = "fields") String fields)
			throws MalformedURLException, IOException {
		String[] textArr = text.split(",");
		String[] fieldsArr = fields.split(",");
		if (textArr.length != fieldsArr.length) {
			return null;
		} else {
			if (atmJsonString == null)
				atmJsonString = getAtmJson();
			return applyFiltersToJson(new JSONArray(atmJsonString), fieldsArr, textArr).toString();
		}
	}

	private String getAtmJson() throws MalformedURLException, IOException {
		String url = "https://www.dropbox.com/s/6fg0k2wxwrheyqk/ATMs?dl=1";

		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
		httpClient.setRequestMethod("GET");

		try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String line;

			while ((line = in.readLine()) != null) {
				response.append(line);
			}

			return response.toString();
		} catch (Exception e) {
			return e.toString();
		}
	}

	private JSONArray applyFiltersToJson(JSONArray atm, String[] fieldsArr, String[] textArr) {
		for (int i = 0; i < fieldsArr.length; i++) {
			if (fieldsArr[i].equals("street") && !atm.isEmpty())
				atm = applyStreetFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("housenumber") && !atm.isEmpty())
				atm = applyHouseNumberFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("postalcode") && !atm.isEmpty())
				atm = applyPostalCodeFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("city") && !atm.isEmpty())
				atm = applyCityFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("lat") && !atm.isEmpty())
				atm = applyLatFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("lng") && !atm.isEmpty())
				atm = applyLngFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("distance") && !atm.isEmpty())
				atm = applyDistanceFilter(atm, textArr[i].toLowerCase());
			else if (fieldsArr[i].equals("type") && !atm.isEmpty())
				atm = applyTypeFilter(atm, textArr[i].toLowerCase());
		}

		return atm;
	}

	private JSONArray applyStreetFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			obj = (JSONObject) obj.get("address");
			String street = (String) obj.get("street");
			if (!street.toLowerCase().contains(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyHouseNumberFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			obj = (JSONObject) obj.get("address");
			String housenumber = (String) obj.get("housenumber");
			if (!housenumber.equals(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyPostalCodeFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			obj = (JSONObject) obj.get("address");
			String postalcode = (String) obj.get("postalcode");
			if (!postalcode.toLowerCase().contains(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyCityFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			obj = (JSONObject) obj.get("address");
			String city = (String) obj.get("city");
			if (!city.toLowerCase().contains(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyLatFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			obj = (JSONObject) obj.get("address");
			obj = (JSONObject) obj.get("geoLocation");

			String lat = "";
			try {
				lat = (String) obj.get("lat");
			} catch (JSONException e) {
				atm.remove(i);
				i--;
				continue;
			}

			if (!lat.startsWith(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyLngFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			obj = (JSONObject) obj.get("address");
			obj = (JSONObject) obj.get("geoLocation");

			String lng = "";
			try {
				lng = (String) obj.get("lng");
			} catch (JSONException e) {
				atm.remove(i);
				i--;
				continue;
			}

			if (!lng.startsWith(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyDistanceFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			String distance = ((Integer) obj.get("distance")).toString();
			if (!distance.equals(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}

	private JSONArray applyTypeFilter(JSONArray atm, String text) {
		for (int i = 0; i < atm.length(); i++) {
			JSONObject obj = (JSONObject) atm.get(i);
			String type = (String) obj.get("type");
			if (!type.toLowerCase().equals(text)) {
				atm.remove(i);
				i--;
			}
		}
		return atm;
	}
}
