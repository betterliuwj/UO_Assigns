package uniform_search;
import java.util.*;
import java.io.*;
public class Execute
	{

		public static void main(String[] args) throws Exception
			{
				// TODO Auto-generated method stub
				System.out.println("***********JAVA Program for Displaying Uniformed Search in Romania***********");
				BufferedReader bReader=new BufferedReader(new InputStreamReader(System.in));
				while(true){
					System.out.println("Please selection an option:");
					System.out.println("1: Breath First Search");
					System.out.println("2: Depth First Search");
					System.out.println("3: Uniformed Cost Search");
					System.out.println("4: Type Exit to Exit");
					String selection=bReader.readLine();
					//Execute Breath First Search
					if(selection.equals("1")){
						BFS bfs_search=new BFS();
						bfs_search.run_search();
					}
					//Execute Depth First Search
					else if(selection.equals("2")){
						DFS dfs_search=new DFS();
						dfs_search.run_search();
					}
					//Execute Uniformed Cost Search
					else if(selection.equals("3")){
						UCS ucs_seach=new UCS();
						ucs_seach.run_search();
					}
					else if(selection.equals("Exit")){
						break;
					}
					else{
						System.out.println("This is not a lawful input,please re-select!!!");
					}
				}
			}

	}

