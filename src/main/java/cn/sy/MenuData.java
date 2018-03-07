package cn.sy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MenuData {
	private static ObjectMapper mapper = new ObjectMapper();
	public static String[] menu = {
		"HME_00_00	home	app.home	2		10_00_00",
		"MDM_00_00	gear	app.md	2		20_00_00",
		"MDM_10_00	file-text-o	app.md.station	3	MDM_00_00	20_10_00",
		"MDM_10_10	file-text-o	app.md.station.x	3	MDM_10_00	20_10_00",
		"MDM_20_00	file-text-o	app.md.location	3	MDM_00_00	20_20_00",
		"MDM_30_00	file-text-o	app.md.user	3	MDM_00_00	20_30_00",
		"MDM_40_00	file-text-o	app.md.perm	3	MDM_00_00	20_40_00",
		"MDM_50_00	file-text-o	app.md.dealer	3	MDM_00_00	20_50_00",
		"CAR_00_00	truck	app.carrier	2		30_00_00",
		"CAR_10_00	file-text-o	app.carrier.carrier	3	CAR_00_00	30_10_00",
		"CAR_20_00	file-text-o	app.carrier.conveyance	3	CAR_00_00	30_20_00",
		"CAR_30_00	file-text-o	app.carrier.driverfilemaintenance	3	CAR_00_00	30_30_00"
	};
	
	public static Map<String, String> toMap(String line) {
		Map<String, String> map = new HashMap<String, String>();
		String[] keys = {"menuId", "menuIcon", "routeState", "type", "parentId", "sortKey"};
		String[] fields = line.split("\t");
		System.out.println("fields=" + fields);
		
		for(int i=0;i<6;i++) {
			map.put(keys[i], fields[i]);
		}
//		map.put("MENU_ID", fields[0]);
//		map.put("MENU_ICON", fields[1]);
//		map.put("ROUTE_STATE", fields[2]);
//		map.put("TYPE", fields[3]);
//		map.put("PARENT_ID", fields[4]);
//		map.put("SORT_KEY", fields[5]);
		
		return map;
		
	}
	
	public static List<Map<String, String>> toMapList(String[] lines) {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Map<String, String> map=null;
		for(String line : lines) {
			map=toMap(line);
			result.add(map);
		}
		return result;
		
	}

	public static List<Map<String, String>> getItems() {
		return toMapList(menu);
	}
	
	public static void main(String[] args) {
		List<Map<String, String>> result = null;

		try {
			result = toMapList(MenuData.menu);
			String jsonStr = mapper.writeValueAsString(result);
			System.out.println(jsonStr);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
