package cn.sy;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyNode extends MyComponent {
	private List<MyComponent> items = new ArrayList<MyComponent>();
	private String href;
	
	public MyComponent findNodeById(String menuId) {
		if(menuId.equals(this.getTitle())) {
			return this;
		}
		else {
			MyComponent tmp = this;
			for(MyComponent comp : items) {
				tmp = comp.findNodeById(menuId);
				if(tmp!=null) {
					return tmp;
				}
			}
		}
		
		return null;
	}
	
	public void append(MyComponent myComponent) {
		items.add(myComponent);
	}
	
	
	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<MyComponent> getItems() {
		return items;
	}
	public void setItems(List<MyComponent> items) {
		this.items = items;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
}
