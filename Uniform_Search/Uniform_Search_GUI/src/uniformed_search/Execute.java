package uniformed_search;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
public class Execute extends JFrame implements ActionListener
	{
		int[][] adjacent_martix;
		//convert_table store all Name to Index;
		HashMap<Object, Object> convert_table;
		CheckboxGroup checkboxGroup;
		JMenuBar menuBar;
		JButton search_beginButton;
		JButton validate_button;
		JPanel   right_body,option_body;
		MyPanel main_body;
		JTable   display_location_to_index;
		public JTextArea message_display,source_textarea,destination_textarea;
		JLabel source_label,destination_label;
		int searchoption=1;
		BFS bfs;
		DFS dfs;
		UCS ucs;
		public static void main(String[] args)
			{
				// TODO Auto-generated method stub
				Execute main_Frame=new Execute();
			}
		public Execute(){
			this.setSize(1200,600);
			this.setLayout(null);
			this.setLocationRelativeTo(null);
			menuBar=new JMenuBar();
			menuBar.setSize(1200,20);
			menuBar.setLocation(0,0);
			this.setJMenuBar(menuBar);
			JMenu operationJMenu=new JMenu("Operation");
			JMenuItem display_Pointting=new JMenuItem("Location Table");
			display_Pointting.setActionCommand("Display_Index");
			display_Pointting.addActionListener(this);
			JMenuItem exit=new JMenuItem("Exit");
			exit.addActionListener(this);
			exit.setActionCommand("Press_Exit");
			operationJMenu.add(display_Pointting);
			operationJMenu.add(exit);
			menuBar.add(operationJMenu);
			JMenu operationJMenu_help=new JMenu("Help");
			JMenuItem about_message=new JMenuItem("About");
			about_message.addActionListener(this);
			about_message.setActionCommand("Press_About");
			operationJMenu_help.add(about_message);
			menuBar.add(operationJMenu_help);
			main_body=new MyPanel();
			main_body.setLayout(null);
			main_body.setBorder(BorderFactory.createTitledBorder("Main Body"));
			main_body.setSize(1000,450);
			main_body.setLocation(0,0);
			this.add(main_body);
			right_body=new JPanel();
			right_body.setBorder(BorderFactory.createTitledBorder("Control Panel"));
			source_label=new JLabel("Source:");
			destination_label=new JLabel("Destination:");
			source_label.setSize(70,20);
			destination_label.setSize(70,20);
			source_label.setLocation(10,76);
			destination_label.setLocation(10,126);
			source_textarea=new JTextArea();
			source_textarea.setSize(100,20);
			source_textarea.setLocation(60,76);
			destination_textarea=new JTextArea();
			destination_textarea.setSize(100,20);
			destination_textarea.setLocation(80,125);
			right_body.add(destination_textarea);
			right_body.add(source_textarea);
			right_body.add(source_label);
			right_body.add(destination_label);
			right_body.setLayout(null);
			right_body.setSize(190,450);
			right_body.setLocation(1000,0);
			this.add(right_body);
			option_body=new JPanel();
			checkboxGroup=new CheckboxGroup();
			Checkbox bfs_cheCheckbox=new Checkbox("BFS",checkboxGroup,true);
			Checkbox dfs_cheCheckbox=new Checkbox("DFS",checkboxGroup,false);
			Checkbox ucs_cheCheckbox=new Checkbox("UCS",checkboxGroup,false);
			option_body.add(bfs_cheCheckbox);
			option_body.add(dfs_cheCheckbox);
			option_body.add(ucs_cheCheckbox);
			option_body.setSize(150,50);
			option_body.setLocation(10,30);
			right_body.add(option_body);
			search_beginButton=new JButton("Start Search");
			validate_button=new JButton("Check S & D");
			validate_button.setEnabled(true);
			search_beginButton.setLocation(20, 300);
			search_beginButton.setSize(150,40);
			search_beginButton.setActionCommand("Start_Search");
			search_beginButton.addActionListener(this);
			search_beginButton.setEnabled(false);
			validate_button.setLocation(20, 200);
			validate_button.setSize(150,40);
			validate_button.addActionListener(this);
			validate_button.setActionCommand("validate");
			right_body.add(search_beginButton);
			right_body.add(validate_button);
			//
			message_display=new JTextArea();
			message_display.setSize(1195,100);
			message_display.setLocation(0,450);
			JScrollPane scrollPane=new JScrollPane(message_display);
			scrollPane.setBorder(BorderFactory.createTitledBorder("Output Area"));
			scrollPane.setSize(1195,100);
			scrollPane.setLocation(0, 450);
			//
			this.add(scrollPane);
			this.setVisible(true);		
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setTitle("Uniformed Search Display");
			this.setResizable(false);
			//
			initial_hashmap();
		}
		@Override
		public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				//When you press the About button
				if(e.getActionCommand().equals("Press_About")){
					JDialog tmpDialog=new JDialog(this, "About");
					tmpDialog.setSize(470,177);
					tmpDialog.setLocationRelativeTo(null);
					JLabel tmpLabel=new JLabel("This programme is to display BFS DFS UCS on Romanina Map, Authour: HUI JIN");
					tmpDialog.add(tmpLabel);
					tmpDialog.setVisible(true);
				}
				if(e.getActionCommand().equals("Press_Exit")){
					this.dispose();
				}
				if(e.getActionCommand().equals("Display_Index")){
					JDialog tmpDialog=new JDialog(this, "Index_Table");
					generate_JTable_location_to_index();
					tmpDialog.setVisible(true);
					tmpDialog.setSize(400,385);
					JScrollPane tmpJScrollPane=new JScrollPane(display_location_to_index);
					tmpDialog.add(tmpJScrollPane);
					tmpDialog.setLocationRelativeTo(null);
				}
				if(e.getActionCommand().equals("Start_Search")){
					//search_beginButton.setEnabled(false);
					message_display.setText(" ");
					//next_setupButton.setEnabled(true);
					if(validate_button.isEnabled()==false)
						{
							if(checkboxGroup.getSelectedCheckbox().getLabel().equals("BFS")){
								bfs=new BFS(source_textarea.getText(), destination_textarea.getText(),this,this.message_display,this.search_beginButton,this.validate_button,this.main_body,this.main_body.getGraphics());
								bfs.bfs_execute();
								String status="";						
								while(true){
									status=bfs.next_step();
									if(status.equals("ok")){
										break;
									}
									try
										{
											Thread.sleep(500);
										} catch (InterruptedException e1)
										{
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
								}
								search_beginButton.setEnabled(false);
								validate_button.setEnabled(true);
								source_textarea.setEnabled(true);
								destination_textarea.setEnabled(true);
							}//if we select BFS
							if(checkboxGroup.getSelectedCheckbox().getLabel().equals("DFS")){
								dfs=new DFS(source_textarea.getText(), destination_textarea.getText(),this,this.message_display,this.search_beginButton,this.validate_button,this.main_body,this.main_body.getGraphics());
								dfs.dfs_execute();
								String status="";						
								while(true){
									status=dfs.next_step();
									if(status.equals("ok")){
										break;
									}
									try
										{
											Thread.sleep(500);
										} catch (InterruptedException e1)
										{
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
								}
								search_beginButton.setEnabled(false);
								validate_button.setEnabled(true);
								source_textarea.setEnabled(true);
								destination_textarea.setEnabled(true);
							}//if we select DFS
							if(checkboxGroup.getSelectedCheckbox().getLabel().equals("UCS")){
								ucs=new UCS(source_textarea.getText(), destination_textarea.getText(),this,this.message_display,this.search_beginButton,this.validate_button,this.main_body,this.main_body.getGraphics());
								ucs.ufs_execute();
								String status="";						
								while(true){
									status=ucs.next_step();
									if(status.equals("ok")){
										break;
									}
									try
										{
											Thread.sleep(500);
										} catch (InterruptedException e1)
										{
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
								}
								search_beginButton.setEnabled(false);
								validate_button.setEnabled(true);
								source_textarea.setEnabled(true);
								destination_textarea.setEnabled(true);
							}//if we select UCS
						}
				}
				if(e.getActionCommand().equals("validate")){
					if(validate_place(source_textarea.getText(), destination_textarea.getText())){
						validate_button.setEnabled(false);
						search_beginButton.setEnabled(true);
						source_textarea.setEnabled(false);
						destination_textarea.setEnabled(false);
						message_display.setText("");
						
					}
					else{
						messageDialog tmp=new messageDialog("Source or Destination doesn't exist!!!");
						tmp.setSize(260,80);
						//put it in the center spot
						tmp.setLocationRelativeTo(null);
						validate_button.setEnabled(true);
					}
		}
}
		public void generate_JTable_location_to_index(){
	        String[] columnNames={"Location_Name","Adjacent_Martrix_index"};
	        String[][] content=new String[20][2];
	        content[0][0]="Oradea";
	        content[0][1]="0";
	        content[1][0]="Zerind";
	        content[1][1]="1";
	        content[2][0]="Arad";
	        content[2][1]="2";
	        content[3][0]="Timisoara";
	        content[3][1]="3";
	        content[4][0]="Lugoj";
	        content[4][1]="4";
	        content[5][0]="Mehadia";
	        content[5][1]="5";
	        content[6][0]="Dobreta";
	        content[6][1]="6";
	        content[7][0]="Sibiu";
	        content[7][1]="7";
	        content[8][0]="R.Vilcea";
	        content[8][1]="8";
	        content[9][0]="Craivoa";
	        content[9][1]="9";
	        content[10][0]="Fagaras";
	        content[10][1]="10";
	        content[11][0]="Pitesti";
	        content[11][1]="11";
	        content[12][0]="Bucharest";
	        content[12][1]="12";
	        content[13][0]="Giurgiu";
	        content[13][1]="13";
	        content[14][0]="Urziceni";
	        content[14][1]="14";
	        content[15][0]="Hirsova";
	        content[15][1]="15";
	        content[16][0]="Eforie";
	        content[16][1]="16";
	        content[17][0]="Vaslui";
	        content[17][1]="17";
	        content[18][0]="Iasi";
	        content[18][1]="18";
	        content[19][0]="Neamt";
	        content[19][1]="19";
	        display_location_to_index=new JTable(content,columnNames);
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
		public Boolean validate_place(String source,String destination){
			if(convert_int_string(source)!=null && convert_int_string(destination)!=null){
				return true;
			}
			else{
				return false;
			}
		}

	}
