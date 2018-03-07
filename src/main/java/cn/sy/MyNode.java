package cn.sy;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MyNode extends MyComponent {
	private List<MyComponent> items = new ArrayList<MyComponent>();
	private String href;
	
	public void append(MyComponent myComponent) {
		String menuId = myComponent.getTitle();
		String parentId = myComponent.getParentId();
		
//		System.out.println("MyNode.append menuId=" + menuId + "  parentId=" + parentId);
		
		if(parentId.equals(this.getTitle())) {
			// 当前节点是item的父节点，则加入list
			items.add(myComponent);
		}
		else {
			// 当前节点不是item的父节点
			MyComponent comp = null;
			for(int i=0;i<items.size();i++) {
				comp = items.get(i);
				// 在子节点中查找item的父节点
				if(parentId.equals(comp.getTitle())) {
					// 如果item的父节点是node，在直接添加
					if(comp instanceof MyNode) {
						comp.append(myComponent);
					}
					// 如果item的父节点是leaf，则把leaf转换成node，再添加
					else if(comp instanceof MyLeaf) {
						MyNode myNode = leafToNode((MyLeaf)comp);
						myNode.append(myComponent);
						items.set(i, myNode);
					}
				}
				else {
					// 如果不是item的父节点，则向下委托
					comp.append(myComponent);
				}
			}
		}
		
	}
	
	private MyNode leafToNode(MyLeaf myLeaf) {
		MyNode myNode = new MyNode();
		myNode.setTitle(myLeaf.getTitle());
		myNode.setIcon(myLeaf.getIcon());
		myNode.setParentId(myLeaf.parentId);
		myNode.setHref("#");
		return myNode;
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
