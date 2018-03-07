package cn.sy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SyTreeBuilder {
	public MyNode tree;
	public SyTreeBuilder() {
		tree = new MyNode();
		tree.setTitle("");
		tree.setParentId("root");
		tree.setIcon(null);
		tree.setHref("#");
	}
	
	public SyTreeBuilder append(Map<String, String> itemMap) {
		String menuId = itemMap.get("menuId");
		String parentId = itemMap.get("parentId");
		
//		System.out.println("builder.append menuId=" + menuId + "  parentId=" + parentId);
		
		MyLeaf myLeaf = mapToMyLeaf(itemMap);
		tree.append(myLeaf);
		
		return this;
	}
	
	public MyComponent build() {
		
		for(Map<String, String> itemMap : MenuData.getItems()) {
			append(itemMap);
		}

		return tree;
	}
		

	private MyLeaf mapToMyLeaf(Map<String, String> itemMap) {
		MyLeaf myLeaf = new MyLeaf();
		myLeaf.setTitle(itemMap.get("menuId"));
		myLeaf.setIcon(itemMap.get("menuIcon"));
		myLeaf.setSref(itemMap.get("routeState"));
		myLeaf.setParentId(itemMap.get("parentId"));
		return myLeaf;
	}

	
	public static void main(String[] args) {
		SyTreeBuilder builder = new SyTreeBuilder();
		MyNode c = (MyNode)builder.build();
//		System.out.println(c);
		
		Map<String, List<MyComponent>> map = new HashMap<String, List<MyComponent>>();
		map.put("items", c.getItems());
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonStr = mapper.writeValueAsString(map);
			System.out.println("main result");
			System.out.println(jsonStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

	}

}
