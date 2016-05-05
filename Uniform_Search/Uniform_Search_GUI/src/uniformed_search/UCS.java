package uniformed_search;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


public class UCS extends Base
	{
		Graphics g;
		ArrayList<Node>  unsorted_node=new ArrayList<Node>();
		public UCS(String source,String destination,JFrame main_frame,JTextArea message_display,JButton search_beginButton,JButton validate_button,MyPanel main_body,Graphics g){
			this.source=source;
			this.search_beginButton=search_beginButton;
			this.validate_button=validate_button;
			this.destination=destination;
			this.main_frame=main_frame;
			this.message_display=message_display;
			this.main_body=main_body;
			this.g=g;
		}
				public void ufs_execute() {
					//initial Visited Array
					expanded=new int[20];
					generated_nodes=new ArrayList<>();
					queue = new LinkedList<Object>();
					Space_complexity=0;Time_complexity=0;
					for(int i=0;i<20;i++){
						expanded[i]=0;
					}
					int source_index=Integer.parseInt(convert_int_string(source).toString());
					//It will exit if the Queue is empty
					visit_node(source_index,null);
				}
				public String  next_step(){
						String output_result="";
						Node expanding_node=(Node)queue.remove();
						//System.out.println("I stuck here, Pointer 1");
						//Judge if it is the destination
						if(expanding_node.getLocation_name().equals(destination)){
							System.out.println("I reach the destination!!!");
							Time_complexity=generated_nodes.size();
							output_result=output_result+"The time complexity is "+Time_complexity+"\n";
							System.out.println("The time complexity is "+Time_complexity);
							output_result=output_result+"The Space complexity is "+Space_complexity+"\n";
							System.out.println("The space complexity is "+Time_complexity);
							System.out.println("The Path from Start Point to End point is:");
							LinkedList<String> temp_path=expanding_node.path;
							temp_path.addFirst(source);
							for(int i=0;i<temp_path.size();i++){
								output_result=output_result+temp_path.get(i);
								System.out.print(temp_path.get(i));
								if(i!=temp_path.size()-1){
									output_result=output_result+"     --------->     ";
									System.out.print("     --------->     ");
								}
							}
							output_result=output_result+"\n";
							System.out.println(" ");
							output_result=output_result+"This total cost of this path is "+calculate_total_cost(temp_path, source)+"\n";
							System.out.println("This total cost of this path is "+calculate_total_cost(temp_path, source));
							message_display.setText(output_result);
							main_body.ending=1;
							main_body.draw_set=draw_set;
							main_body.draw_path=temp_path;
							main_body.repaint();
							return "ok";
						}
						if(expanded[expanding_node.getNode_index()]==0){
						//this step just inserting node into the queue, expanding
							for (int k = this.firstAdjVex(expanding_node.getNode_index());k>=0;k=this.nextAdjVex(expanding_node.getNode_index(), k)){
								//no matter the node is expanded or not, it still generated and put it into queue, when expand it we make judgement.
									visit_node(k,expanding_node);
							}
							//Now we sort the node by cost 												
							unsorted_node=Merge_sort_node(unsorted_node);
							//Now we insert the sorted node
							insert_sort_node();
							//Now we clear up the unsorted_node
							unsorted_node.clear();
						}
						expanded[expanding_node.getNode_index()]=1;
						main_body.draw_set=draw_set;
						main_body.paint(g);
						return "notok";
				}
				//This function make a node visited and create a node instance in queue and generate_node
				//When it put the node into queue , the node has been verified not visited before.
				public void visit_node(int visit_index,Object Father){
					if(Father==null){
						Node tmpNode=new Node(null, 0, convert_int_string(visit_index).toString(), visit_index);
						tmpNode.setTotal_path_metric(0);
						generated_nodes.add(tmpNode);
						queue.add(tmpNode);
						draw_set.add(tmpNode);
					}
					//It is not the source node, it has father, it need to record it , so we can print out the path
					else{
						Node tmpNode=new Node(((Node)Father).getLocation_name(),((Node)Father).getDepth()+1, convert_int_string(visit_index).toString(), visit_index);
						tmpNode.setTotal_path_metric(((Node)Father).getTotal_path_metric()+adjacent_martix[visit_index][Integer.parseInt(convert_int_string(((Node)Father).getLocation_name()).toString())]);
						generated_nodes.add(tmpNode);
						//queue.add(tmpNode);
						unsorted_node.add(tmpNode);
						draw_set.add(tmpNode);
						for(int i=0;i<((Node)Father).path.size();i++){
							tmpNode.path.add(((Node)Father).path.get(i));
						}
						tmpNode.path.add(tmpNode.getLocation_name());
					}
					if(queue.size()>Space_complexity){
						Space_complexity=queue.size();
					}
				}
				//looking for first children node
				public int firstAdjVex(int node_index) {
					for (int j = 0; j < 20; j++) 
					{
						if (adjacent_martix[node_index][j] > 0 && adjacent_martix[node_index][j]<Integer.MAX_VALUE)
							{
								//Time_complexity++;
								return j;
						}
					}
					return -1;
				}
				//looking for next children node, if available, even the expanded node
				public int nextAdjVex(int node_index,int current_index){
					for(int j=current_index+1;j<20;j++){
						if(adjacent_martix[node_index][j]>0 && adjacent_martix[node_index][j]<Integer.MAX_VALUE){
							//Time_complexity++;
							return j;
						}
					}
					return -1;
				}
				//sort the node by cost
				public ArrayList<Node> Merge_sort_node(ArrayList<Node> sorting_arraylist){
					if(sorting_arraylist.size()==1){
						return sorting_arraylist;
					}
					else{
						int half_length=sorting_arraylist.size()/2;
						ArrayList<Node> array1=new ArrayList<Node>();
						ArrayList<Node> array2=new ArrayList<Node>();
						ArrayList<Node> sorted_list=new ArrayList<Node>();
						//assign value to array1 and array2
						for(int i=0;i<half_length;i++){
							array1.add(sorting_arraylist.get(i));
						}
						for(int i=half_length;i<sorting_arraylist.size();i++){
							array2.add(sorting_arraylist.get(i));
						}
						array1=Merge_sort_node(array1);
						array2=Merge_sort_node(array2);
						//Merge
						int array1_index=0,array2_index=0;
						for(int i=0;i<sorting_arraylist.size();i++){
							//When it running out of all items in either array1 or array2
							if(array1_index>=array1.size()){
								for(;i<sorting_arraylist.size();i++){
									sorted_list.add(array2.get(array2_index));
									array2_index++;
								}
								return sorted_list;
							}
							if(array2_index>=array2.size()){
								for(;i<sorting_arraylist.size();i++){
									sorted_list.add(array1.get(array1_index));
									array1_index++;
								}
								return sorted_list;
							}
							if(array1.get(array1_index).getTotal_path_metric()<array2.get(array2_index).getTotal_path_metric())
								{
								sorted_list.add(array1.get(array1_index));
								array1_index++;
								}
							else{
								sorted_list.add(array2.get(array2_index));
								array2_index++;
							}
						}
						//
						return sorted_list;
					}
				}
		public void insert_sort_node(){
			System.out.println("AAAA");
			for(int i=0;i<unsorted_node.size();i++){
				System.out.println("I add"+unsorted_node.get(i).getLocation_name());
				queue.add(unsorted_node.get(i));
				}
			}
		public void Clear_History(){
			unsorted_node.clear();
			queue.clear();
			generated_nodes.clear();
			Space_complexity=0;
			Time_complexity=0;
		}
				
	}
