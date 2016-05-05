package uniformed_search;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class DFS extends Base
	{
	
			Graphics g;
			DFS(String source,String destination,JFrame main_frame,JTextArea message_display,JButton search_beginButton,JButton validate_button,MyPanel main_body,Graphics g){
				this.source=source;
				this.search_beginButton=search_beginButton;
				this.validate_button=validate_button;
				this.destination=destination;
				this.main_frame=main_frame;
				this.message_display=message_display;
				this.main_body=main_body;
				this.g=g;
			}
				
				public void dfs_execute(){
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
					main_body.draw_set=draw_set;
					main_body.repaint();
				}
				public String next_step(){
						Node expanding_node=(Node)queue.remove();
						//Judge if it is the destination
						if(expanding_node.getLocation_name().equals(destination)){
							String output_result="";
							System.out.println("I reach the destination!!!");
							Time_complexity=generated_nodes.size();
							System.out.println("The time complexity is "+Time_complexity);//Add 1 because the source node
							output_result=output_result+"The time complexity is "+Time_complexity+"\n";
							output_result=output_result+"The Space complexity is "+Space_complexity+"\n";
							System.out.println("The space complexity is "+Space_complexity);
							System.out.println("The Path from Start Point to End point is:");
							LinkedList temp_path=expanding_node.path;
							temp_path.addFirst(source);
							System.out.print(source);
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
						//this step just inserting node into the queue, expanding
						if(expanded[expanding_node.getNode_index()]==0){
							for (int k = this.firstAdjVex(expanding_node.getNode_index());k>=0;k=this.nextAdjVex(expanding_node.getNode_index(), k)){
								//no matter the node is expanded or not, it still generated and put it into queue, when expand it we make judgement.
									visit_node(k,expanding_node);
							}
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
						generated_nodes.add(tmpNode);
						queue.addFirst(tmpNode);
						draw_set.add(tmpNode);
					}
					//It is not the source node, it has father, it need to record it , so we can print out the path
					else{
						Node tmpNode=new Node(((Node)Father).getFather_name(),((Node)Father).getDepth()+1, convert_int_string(visit_index).toString(), visit_index);
						generated_nodes.add(tmpNode);
						queue.addFirst(tmpNode);
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
					for (int j = 19; j >=0; j--) 
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
					for(int j=current_index-1;j>=0;j--){
						if(adjacent_martix[node_index][j]>0 && adjacent_martix[node_index][j]<Integer.MAX_VALUE){
							//Time_complexity++;
							return j;
						}
					}
					return -1;
				}
				public void Clear_History(){
					queue.clear();
					generated_nodes.clear();
					Space_complexity=0;
					Time_complexity=0;
				}
	}
