package cn.sy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyLeaf extends MyComponent {
	private String sref;

	public void append(MyComponent myComponent) {
//		throw new RuntimeException("unsupport method append");
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
	
	public String getSref() {
		return sref;
	}

	public void setSref(String sref) {
		this.sref = sref;
	}
}
