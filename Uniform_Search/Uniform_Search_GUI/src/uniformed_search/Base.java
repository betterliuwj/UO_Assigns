package uniformed_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Base
	{
		HashSet<Node> draw_set=new HashSet<Node>();
		int[][] adjacent_martix;
		HashMap<Object, Object> convert_table;
		int[] expanded;
		JFrame main_frame;
		MyPanel main_body;
		JButton search_beginButton;
		JButton validate_button;
		JTextArea message_display;
		String source,destination;
		//To record the searching queue now, is the exactly space representation, it contains node type object
		LinkedList<Object> queue = new LinkedList<Object>();
		//To record all the node it has generated, it contains Node type Object
		ArrayList<Object> generated_nodes=new ArrayList<>();
		//Record the queue maximum size as space complexity
		//For the Time_complexity node, if i run Time_complxity=generated_node.size(),it will create node when it is not visited. If put it in
		//the FirstAdj and SecondAdj it will generated no matter where it is visited.
		int Space_complexity,Time_complexity;
		Base(){
			initial_martix();
			initial_hashmap();
		}
		private void initial_martix(){
			adjacent_martix=new int[20][20];
			for(int i=0;i<20;i++){
				for(int j=0;j<20;j++){
					adjacent_martix[i][j]=Integer.MAX_VALUE;
				}
			}
			for(int i=0;i<20;i++){
				adjacent_martix[i][i]=0;
			}
			adjacent_martix[0][1]=71;
			adjacent_martix[1][0]=71;
			adjacent_martix[1][2]=75;
			adjacent_martix[2][1]=75;
			adjacent_martix[0][7]=151;
			adjacent_martix[7][0]=151;
			adjacent_martix[2][7]=140;
			adjacent_martix[7][2]=140;
			adjacent_martix[2][3]=118;
			adjacent_martix[3][2]=118;
			adjacent_martix[7][10]=99;
			adjacent_martix[10][7]=99;
			adjacent_martix[7][8]=80;
			adjacent_martix[8][7]=80;
			adjacent_martix[3][4]=111;
			adjacent_martix[4][3]=111;
			adjacent_martix[4][5]=70;
			adjacent_martix[5][4]=70;
			adjacent_martix[5][6]=75;
			adjacent_martix[6][5]=75;
			adjacent_martix[6][9]=120;
			adjacent_martix[9][6]=120;
			adjacent_martix[10][12]=211;
			adjacent_martix[12][10]=211;
			adjacent_martix[8][11]=97;
			adjacent_martix[11][8]=97;
			adjacent_martix[8][9]=146;
			adjacent_martix[9][8]=146;
			adjacent_martix[9][11]=138;
			adjacent_martix[11][9]=138;
			adjacent_martix[11][12]=101;
			adjacent_martix[12][11]=101;
			adjacent_martix[12][13]=90;
			adjacent_martix[13][12]=90;
			adjacent_martix[12][14]=85;
			adjacent_martix[14][12]=85;
			adjacent_martix[18][19]=87;
			adjacent_martix[19][18]=87;
			adjacent_martix[17][18]=92;
			adjacent_martix[18][17]=92;
			adjacent_martix[14][17]=142;
			adjacent_martix[17][14]=142;
			adjacent_martix[14][15]=98;
			adjacent_martix[15][14]=98;
			adjacent_martix[15][16]=86;
			adjacent_martix[16][15]=86;
		}
		private void initial_hashmap(){
			convert_table=new HashMap<>();
			convert_table.put("Oradea",0);
			convert_table.put("Zerind",1);
			convert_table.put("Arad",2);
			convert_table.put("Timisoara",3);
			convert_table.put("Lugoj",4);
			convert_table.put("Mehadia",5);
			convert_table.put("Drobeta",6);
			convert_table.put("Sibiu",7);
			convert_table.put("R.Vilcea",8);
			convert_table.put("Craiova",9);
			convert_table.put("Fagaras",10);
			convert_table.put("Pitesti",11);
			convert_table.put("Bucharest",12);
			convert_table.put("Giurgiu",13);
			convert_table.put("Urziceni",14);
			convert_table.put("Hirsova",15);
			convert_table.put("Eforie",16);
			convert_table.put("Vaslui",17);
			convert_table.put("Iasi",18);
			convert_table.put("Neamt",19);
			Iterator iter=convert_table.entrySet().iterator();
			Object[] temp=new Object[convert_table.size()*2];
			int i=0;
			while(iter.hasNext()){
				Map.Entry entry=(Map.Entry)iter.next();
				Object key=entry.getKey();
				Object value=entry.getValue();
				temp[i]=value;
				i++;
				temp[i]=key;
				i++;
			}
			for(i=0;i<temp.length-1;i=i+2){
				convert_table.put(temp[i], temp[i+1]);
			}
		}
		public Object convert_int_string(Object variable){
			return (Object)convert_table.get(variable);
		}
		public int calculate_total_cost(LinkedList path,String source){
			int total_cost=0;
			total_cost=total_cost+adjacent_martix[Integer.parseInt(convert_int_string(source).toString())][Integer.parseInt(convert_int_string(path.get(0)).toString())];
			for(int i=1;i<path.size();i++){
				total_cost=total_cost+adjacent_martix[Integer.parseInt(convert_int_string(path.get(i-1)).toString())][Integer.parseInt(convert_int_string(path.get(i)).toString())];
			}
			return total_cost;
		}
	}
