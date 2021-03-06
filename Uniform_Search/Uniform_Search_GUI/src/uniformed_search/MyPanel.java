package uniformed_search;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Timer;

import javax.swing.JPanel;

import java.util.Iterator;
public class MyPanel extends JPanel
	{
		Image loaded_image;
		int ending=0;
		HashMap<String,ArrayList> city_coordinateHashMap;
		HashSet<Node> draw_set=new HashSet<>();
		LinkedList<String> draw_path=new LinkedList<String>();
		int previous_set_size=0;
		public MyPanel(){
			loaded_image=(Toolkit.getDefaultToolkit().createImage("map.jpg"));
			construct_city_coordinateHaspMap();
		}
		@Override
		public void paint(Graphics g){
			super.paint(g);
			g.drawImage(loaded_image, 0, 0, 1000, 450, this);
			g.setColor(Color.RED);
			Iterator it=draw_set.iterator();
			/*
			if(previous_set_size==draw_set.size()&previous_set_size!=0&&ending!=1){
				messageDialog tmp=new messageDialog("I come across a node which has been expanded before!So no any City would be circled this time, neither expand it again!!!");
				tmp.setSize(800,100);
				tmp.setLocationRelativeTo(null);
				ending=0;
			}
			*/
			//previous_set_size=draw_set.size();
			while(it.hasNext()){
				Node tmpNode=(Node)it.next();
				ArrayList location=(ArrayList)city_coordinateHashMap.get(tmpNode.getLocation_name());
				g.setColor(Color.red);
				g.drawOval(Integer.parseInt(location.get(0).toString()), Integer.parseInt(location.get(1).toString()), 30, 30);
			}
			if(ending==1){
				String point1="",point2="";
				int firsttime=0;
				Iterator it2=draw_path.iterator();
				while(it2.hasNext()){
					if(firsttime==0){
						point1=it2.next().toString();
						point2=it2.next().toString();
						firsttime=1;
					}
					else{
						point2=it2.next().toString();
					}
					((Graphics2D)g).setStroke(new BasicStroke(5));
					g.setColor(Color.RED);
					g.drawLine(Integer.parseInt(city_coordinateHashMap.get(point1).get(0).toString())-5, Integer.parseInt(city_coordinateHashMap.get(point1).get(1).toString())-5, Integer.parseInt(city_coordinateHashMap.get(point2).get(0).toString())-5, Integer.parseInt(city_coordinateHashMap.get(point2).get(1).toString())-5);
					point1=point2;
				}
				ending=0;
			}
			System.out.println("I Draw");
		}
		public void construct_city_coordinateHaspMap(){
			city_coordinateHashMap=new HashMap<>();
			ArrayList<Integer> tmp=new ArrayList<>();
			tmp.add(130);
			tmp.add(4);
			city_coordinateHashMap.put("Oradea",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(95);
			tmp.add(55);
			city_coordinateHashMap.put("Zerind",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(61);
			tmp.add(117);
			city_coordinateHashMap.put("Arad",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(65);
			tmp.add(220);
			city_coordinateHashMap.put("Timisoara",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(191);
			tmp.add(270);
			city_coordinateHashMap.put("Lugoj",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(192);
			tmp.add(315);
			city_coordinateHashMap.put("Mehadia",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(190);
			tmp.add(376);
			city_coordinateHashMap.put("Drobeta",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(265);
			tmp.add(160);
			city_coordinateHashMap.put("Sibiu",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(310);
			tmp.add(225);
			city_coordinateHashMap.put("R.Vilcea",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(346);
			tmp.add(390);
			city_coordinateHashMap.put("Craiova",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(441);
			tmp.add(170);
			city_coordinateHashMap.put("Fagaras",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(465);
			tmp.add(285);
			city_coordinateHashMap.put("Pitesti",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(605);
			tmp.add(334);
			city_coordinateHashMap.put("Bucharest",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(560);
			tmp.add(418);
			city_coordinateHashMap.put("Giurgiu",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(614);
			tmp.add(55);
			city_coordinateHashMap.put("Neamt",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(735);
			tmp.add(95);
			city_coordinateHashMap.put("Iasi",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(798);
			tmp.add(182);
			city_coordinateHashMap.put("Vaslui",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(703);
			tmp.add(303);
			city_coordinateHashMap.put("Urziceni",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(840);
			tmp.add(305);
			city_coordinateHashMap.put("Hirsova",tmp);
			tmp=null;
			tmp=new ArrayList<>();
			tmp.add(890);
			tmp.add(381);
			city_coordinateHashMap.put("Eforie",tmp);
		}
		
	}
