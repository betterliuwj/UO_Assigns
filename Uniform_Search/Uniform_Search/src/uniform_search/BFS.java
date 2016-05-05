package uniform_search;
import java.util.*;
import java.io.*;

public class BFS extends Base
	{
		
		//No Parameter Constructor
		//To record which node is expanded, visit make no meaning, when it is visited but not expanded ,it should still generate
		int[] expanded;
		//To record the searching queue now, is the exactly space representation, it contains node type object
		Queue<Object> queue = new LinkedList<Object>();
		//To record all the node it has generated, it contains Node type Object
		ArrayList<Object> generated_nodes=new ArrayList<>();
		//Record the queue maximum size as space complexity
		//For the Time_complexity node, if i run Time_complxity=generated_node.size(),it will create node when it is not visited. If put it in
		//the FirstAdj and SecondAdj it will generated no matter where it is visited.
		int Space_complexity,Time_complexity;
		BFS(){

		}
		public void run_search() throws Exception{
			System.out.println("Welcome to Breath First Search Area");
			BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
			while(true){
				System.out.println("Please selection an option:");
				System.out.println("1: Lauch an new Breath First Search");
				System.out.println("2: Display Current World Adjacent Martix(Hard-Corded)");
				System.out.println("3: Retrivial Location Name to Adjacent Matrix index");
				System.out.println("Type Back to Mainmenu");
				String option=bReader.readLine();
				if(option.equals("1")){
					String source,destination;
					System.out.println("Please type the source(E.g Arad)");
					source=bReader.readLine();
					System.out.println("Please type the destination(E.g Bucharest");
					destination=bReader.readLine();
					if(validate_place(source, destination)){
						Clear_History();
						bfs_execute(source, destination);
					}
					else{
						System.out.println("Source or Destination is undefined in my world!");
					}
				}
				else if(option.equals("2")){
					display_all();
				}
				else if(option.equals("Back")){
					break;
				}
				else if(option.equals("3")){
					System.out.println("Insert a location name or index");
					Object result=convert_int_string(bReader.readLine());
					if(result!=null){
						System.out.println(result.toString());
					}
					else{
						System.out.println("City Not Existed!!!");
					}
				}
				else{
					System.out.println("This is not a lawful input");
				}
			}
		}
		//Display Current World Status
		public void display_all(){
			System.out.printf("%12s"," ");
			for(int i=0;i<20;i++){
				System.out.printf("%12s",convert_int_string(i));
			}				
			System.out.println(" ");
			for(int i=0;i<20;i++){
				System.out.printf("%12s",convert_int_string(i));
				for(int j=0;j<20;j++){
					if(adjacent_martix[i][j]<Integer.MAX_VALUE){
						System.out.printf("%12d",adjacent_martix[i][j]);
					}
					else{
						System.out.printf("%12s","N/A");
					}
				}
				System.out.println(" ");
			}	
		}
		public Boolean validate_place(String source,String destination){
			if(convert_int_string(source)!=null && convert_int_string(destination)!=null){
				return true;
			}
			else{
				return false;
			}
		}
		public void bfs_execute(String source,String destination) throws Exception{
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
			while(!queue.isEmpty()){
				Node expanding_node=(Node)queue.remove();
				
				//System.out.println("I stuck here, Pointer 1");
				//Judge if it is the destination
				if(expanding_node.getLocation_name().equals(destination)){
					System.out.println("I reach the destination!!!");
					Time_complexity=generated_nodes.size();
					System.out.println("The time complexity is "+Time_complexity);//Add 1 because the source node
					System.out.println("The space complexity is "+Time_complexity);
					System.out.println("The Path from Start Point to End point is:");
					ArrayList<String> temp_path=expanding_node.path;
					System.out.print(source);
					for(int i=0;i<temp_path.size();i++){
						System.out.print("     --------->     ");
						System.out.print(temp_path.get(i));
					}
					System.out.println(" ");
					System.out.println("This total cost of this path is "+calculate_total_cost(temp_path, source));
					break;
				}
				if(expanded[expanding_node.getNode_index()]==0){
				//this step just inserting node into the queue, expanding
					for (int k = this.firstAdjVex(expanding_node.getNode_index());k>=0;k=this.nextAdjVex(expanding_node.getNode_index(), k)){
						//no matter the node is expanded or not, it still generated and put it into queue, when expand it we make judgement.
							visit_node(k,expanding_node);
					}
				}
				expanded[expanding_node.getNode_index()]=1;
			}
		}
		//This function make a node visited and create a node instance in queue and generate_node
		//When it put the node into queue , the node has been verified not visited before.
		public void visit_node(int visit_index,Object Father){
			if(Father==null){
				Node tmpNode=new Node(null, 0, convert_int_string(visit_index).toString(), visit_index);
				generated_nodes.add(tmpNode);
				queue.add(tmpNode);
			}
			//It is not the source node, it has father, it need to record it , so we can print out the path
			else{
				Node tmpNode=new Node(((Node)Father).getFather_name(),((Node)Father).getDepth()+1, convert_int_string(visit_index).toString(), visit_index);
				generated_nodes.add(tmpNode);
				queue.add(tmpNode);
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
		public void Clear_History(){
			queue.clear();
			generated_nodes.clear();
			Space_complexity=0;
			Time_complexity=0;
		}
	}
