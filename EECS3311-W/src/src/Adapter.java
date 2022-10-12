package src;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Adapter extends dataFetcher{
	
	public Adapter(int startDate, int endDate, String country) {
		super(startDate, endDate, country);
	}

	private adapteeDataFetcher adaptee = new adapteeDataFetcher();

	public JsonArray getData(String dataIndicator) {
		return adaptee.specificGetData(this.country, dataIndicator, this.startDate, this.endDate);
	}
}
