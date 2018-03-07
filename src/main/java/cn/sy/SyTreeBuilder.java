package cn.sy;

import java.util.Map;

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
		
		System.out.println("builder.append menuId=" + menuId + "  parentId=" + parentId);
		
		// 获取item的父节点
		MyComponent parentNode = findNodeById(parentId);
		if(parentNode==null) {
			throw new RuntimeException("parent not found. "
					+ " menuId=" + menuId
					+ " parentId=" + parentId);
		}
		if(parentNode instanceof MyNode) {
			MyLeaf myLeaf = mapToMyLeaf(itemMap);
			parentNode.append(myLeaf);
		}
		else {
			// 如果item的父节点是leaf，需要转换成node
			
			// 根据item新建leaf
			MyLeaf myLeaf = mapToMyLeaf(itemMap);
			// 根据父节点parentNode新建node，并加入item
			MyNode myNode = leafToNode((MyLeaf)parentNode);
			myNode.append(myLeaf);
			
			// 替换parentNode。查询parentNode的父节点，替换
			MyNode ppNode = (MyNode)findNodeById(parentNode.getParentId());
			MyComponent tempComp = null;
			for(int i=0;i<ppNode.getItems().size();i++) {
				if(parentId.equals(ppNode.getItems().get(i).getTitle())) {
					ppNode.getItems().set(i, myNode);
				}
			}
		}
		// debug show tree
		System.out.println("builder.append menuId=" + menuId + "  tree: " + tree);
		
		return this;
	}
	
	public MyComponent findNodeById(String menuId) {
		MyComponent parent = tree;
		
		System.out.println("builder.findNodeById menuId=" + menuId);

		return parent.findNodeById(menuId);
	}
	
	public void build() {
		
		System.out.println(tree);
	}
	
	

	private MyLeaf mapToMyLeaf(Map<String, String> itemMap) {
		MyLeaf myLeaf = new MyLeaf();
		myLeaf.setTitle(itemMap.get("menuId"));
		myLeaf.setIcon(itemMap.get("menuIcon"));
		myLeaf.setSref(itemMap.get("routeState"));
		myLeaf.setParentId(itemMap.get("parentId"));
		return myLeaf;
	}
	private MyNode mapToMyNode(Map<String, String> itemMap) {
		MyNode myNode = new MyNode();
		myNode.setTitle(itemMap.get("menuId"));
		myNode.setIcon(itemMap.get("menuIcon"));
		myNode.setHref("#");
		myNode.setParentId(itemMap.get("parentId"));
		return myNode;
	}
	private MyNode leafToNode(MyLeaf myLeaf) {
		MyNode myNode = new MyNode();
		myNode.setTitle(myLeaf.getTitle());
		myNode.setIcon(myLeaf.getIcon());
		myNode.setParentId(myLeaf.parentId);
		myNode.setHref("#");
		return myNode;
	}

	
	public static void main(String[] args) {
		SyTreeBuilder builder = new SyTreeBuilder();
		for(Map<String, String> itemMap : MenuData.getItems()) {
			builder.append(itemMap);
		}
		builder.build();

	}

}
