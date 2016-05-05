package uniform_search;

import java.util.ArrayList;

public class Node
	{
		public String getFather_name()
		{
			return Father_name;
		}
	public void setFather_name(String father_name)
		{
			Father_name = father_name;
		}
	public int getDepth()
		{
			return depth;
		}
	public void setDepth(int depth)
		{
			this.depth = depth;
		}
	public String getLocation_name()
		{
			return location_name;
		}
	public void setLocation_name(String location_name)
		{
			this.location_name = location_name;
		}
	public int getNode_index()
		{
			return node_index;
		}
	public void setNode_index(int node_index)
		{
			this.node_index = node_index;
		}
		private String Father_name;
		private int depth;
		private String location_name;
		private int node_index;
		private int total_path_metric;
		public int getTotal_path_metric()
			{
				return total_path_metric;
			}
		public void setTotal_path_metric(int total_path_metric)
			{
				this.total_path_metric = total_path_metric;
			}
		public ArrayList<String> path=new ArrayList<>();
		//Parametered Constructor
		public Node(String Father_name,int depth,String location_name,int node_index){
			this.Father_name=Father_name;
			this.depth=depth;
			this.location_name=location_name;
			this.node_index=node_index;
		}
	}
