package huang.bling.hackathon.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsonParseUtil {

	public static <T> List<T> fromJsonArray(String json, Class<T> clazz) {
		List<T> lst = new ArrayList<T>();
		try {
			JsonArray array = new JsonParser().parse(json).getAsJsonArray();
			for (final JsonElement elem : array) {
				lst.add(new Gson().fromJson(elem, clazz));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lst;
	}

	public static List<Map<String, Object>> listKeyMaps(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Gson gson = new Gson();
			list = gson.fromJson(jsonString,
					new TypeToken<List<Map<String, Object>>>() {
					}.getType());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	
	public static <T> T fromJsonObject(String jsonString, Class<T> cls) {
        T t = null;
         try {
             Gson gson = new Gson();
             t = gson.fromJson(jsonString, cls);
         } catch (Exception e) {
             // TODO: handle exception
         }
         return t;
     }
}
