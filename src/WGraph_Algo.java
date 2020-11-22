package ex1.src;


import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;




import ex1.src.WGraph_DS.NodeInfo;

public class WGraph_Algo implements weighted_graph_algorithms {
private weighted_graph g= new WGraph_DS();
private int connectivity;
private Formatter x;
	@Override
	public void init(weighted_graph g) {
		// TODO Auto-generated method stub
		this.g=g;
	}

	@Override
	public weighted_graph getGraph() {
		// TODO Auto-generated method stub
		return g;
	}

	@Override
	public weighted_graph copy() {
		 WGraph_DS newgraph=new  WGraph_DS();
		 newgraph.copy(g);
		return newgraph;
	}

	@Override
	public boolean isConnected()
	//checks if graph is connected
	{
		if((g.edgeSize()+1)<g.nodeSize())return false;
		else if(g.getV().size()==0)
			return true;
		else if(g.getV().size()==1)
			return true;
		else if (g==null)
			return false;
		
		else 
		{
			it_colors();
			Iterator<node_info> firstnode=g.getV().iterator(); 
		    if(firstnode.hasNext())
		    	{
		    		node_info temp=firstnode.next();
		    		temp.setTag(0);
		    		Queue<node_info> vector = new LinkedList<node_info>();
		    		temp.setInfo("Black");
		    		vector.add(temp);
		    		connectivity++; 
		    		while(!vector.isEmpty())
		    		{
		    			node_info pop=vector.poll();
		    			pop.setInfo("Black");
		    			Iterator<node_info> ite1 = ((NodeInfo)pop).getNi_nodes().iterator();
		    			while(ite1.hasNext())
		    			{
		    				node_info Temp2=ite1.next();
		    				if(Temp2.getInfo().equals("White"))
		    				{
		    					Temp2.setInfo("Gray");
		    					vector.add(Temp2);
		    					connectivity++;
		    				}
		    			}
		    		}	
		    	}
		     if(connectivity==g.nodeSize())return true;
		     else return false;
		}
	}

	@Override
	// returns shortest path using djekstra 
	public double shortestPathDist(int src, int dest) {
		node_info start=g.getNode(src);
		node_info end=g.getNode(dest);
		NodeInfo S=(NodeInfo)start;
		 if((start!=null&&end!=null)&&start.equals(end))return 0;
		else if(start!=null&&end!=null) 
		{
			it_colors();
    		start.setTag(0);
    		PriorityQueue<NodeInfo> vector = new PriorityQueue<NodeInfo>();
    		start.setInfo("Black");
    		vector.add((NodeInfo) start);
    		while(!vector.isEmpty() )
    		{
    			node_info pop=vector.poll();
    			pop.setInfo("Black");
    			Iterator<node_info> neighbors_nodes=((NodeInfo)pop).getNi_nodes().iterator();
    			while(neighbors_nodes.hasNext())
    			{
    				node_info neighbor=neighbors_nodes.next();
    				if(neighbor.getTag()>(pop.getTag()+((WGraph_DS)g).getEdge(neighbor.getKey(), pop.getKey())))
					{
    					neighbor.setTag(pop.getTag()+((WGraph_DS)g).getEdge(neighbor.getKey(), pop.getKey()));
    					((NodeInfo)neighbor).set_back(pop);

					}
    				if(neighbor.getInfo().equals("White"))
    				{
    					
    					neighbor.setInfo("Gray");
    					vector.add((NodeInfo) neighbor);
    				}
    			}
    		}
    		return end.getTag();
    	}
		
    		
    	
		
			
		else 
		return -1;

	}

	@Override
	// returns a list of nodes of the path for shortest path
	public List<node_info> shortestPath(int src, int dest) {
		double distance=shortestPathDist(src, dest);
		if(distance==-1)
		{

			return null;
		}
		else if(distance==0)
		{
			List<node_info> L=new ArrayList<node_info>(1);
			L.add(g.getNode(src));
			return L;
		}
			
		
		List<node_info> L=new ArrayList<node_info>();
	
			
		node_info end=g.getNode(dest);
	
			L.add(end);
			node_info prev=((NodeInfo)end).get_back();
			while(prev!=null)
			{
				L.add(prev);
				prev=((NodeInfo)prev).get_back();
			}
		L=fliplist(L);

			
		return L;
	}
	public List<node_info> fliplist( List<node_info> original) 
	{
		List<node_info> copy=new ArrayList<node_info>();
		for(int i=original.size()-1;i>=0;i--)
		{
			copy.add(original.get(i));
			
		}

		return copy;
	}

	@Override
	// save method implementaion 
	public boolean save(String file) {
		try 
		{
			x=new Formatter(file);
			
		}
		catch(Exception e)
		{
			System.err.println(" couldn't create file");
			return false;
		}
		x.format("%s%s%s%s%s%s", " node size: ",g.nodeSize()," edge size: ", g.edgeSize()," mode count: ",g.getMC());
		x.format("%s", "\n");
		Iterator<node_info> nodes = g.getV().iterator();
		x.format("%s", " list of nodes : ");
		x.format("%s", "\n");

		while(nodes.hasNext())
		{
			node_info original=nodes.next();
			x.format("%s",original.toString());
			x.format("%s", "\n");
			
		}
		nodes=g.getV().iterator();
		x.format("%s", " connections : ");
		x.format("%s", "\n");

		while(nodes.hasNext())
		{
			node_info original=nodes.next();

			Iterator<node_info> sons = ((NodeInfo)original).getNi_nodes().iterator();
			while(sons.hasNext())
			{
				node_info son=sons.next();
				x.format("%s%s%s",original.toString(),g.getEdge(original.getKey(),son.getKey()),son.toString());
				x.format("%s", "\n");

				
			}


		}
		closeFile();
		return true;
	}

	@Override
	// load graph implementaion
	public boolean load(String file) {
		ArrayList<String> list=file_to_arraylist(file);
		if(list==null)
		{
			System.err.println("couldnt load file");
			return false;
			
		}
		weighted_graph G=new WGraph_DS();
		ArrayList<String> lines=new ArrayList<String>();
		String first_line=list.get(0);
		StringTokenizer tokenizer = new StringTokenizer(first_line);
		while(tokenizer.hasMoreTokens()) lines.add(tokenizer.nextToken());
		int nodesize=Integer.parseInt(lines.get(2));
		int edgesize=Integer.parseInt(lines.get(5));
		int modecount=Integer.parseInt(lines.get(8));
		for(int i=2;i<(nodesize+2);i++)
		{
			first_line=list.get(i);
			ArrayList<String> lines1=new ArrayList<String>();
			StringTokenizer tokenizer1 = new StringTokenizer(first_line);
			while(tokenizer1.hasMoreTokens()) lines1.add(tokenizer1.nextToken());
			node_info n=new NodeInfo(Integer.parseInt(lines1.get(1)),Double.parseDouble(lines1.get(3)),lines1.get(5));
			((WGraph_DS)G).addNode(n);
		}
		for(int i=(nodesize+3);i<list.size();i++)
		{
			first_line=list.get(i);
			ArrayList<String> lines1=new ArrayList<String>();
			StringTokenizer tokenizer1 = new StringTokenizer(first_line);
			while(tokenizer1.hasMoreTokens()) lines1.add(tokenizer1.nextToken());
			G.connect(Integer.parseInt(lines1.get(1)), Integer.parseInt(lines1.get(9)), Double.parseDouble(lines1.get(7)));
			
		}
		init(G);
		if(G.nodeSize()!=nodesize&&G.edgeSize()!=edgesize)
			return false;
		
		

		
		
		
		return true;
	}
	public ArrayList<String> file_to_arraylist(String file)
	{
		ArrayList<String> W=new ArrayList<>();
		Scanner FR;
		try {FR=new Scanner(new File(file));}
		catch(Exception e)
		{
			System.err.println("cant open file");
			return null;
		}
		while(FR.hasNextLine())
		{
			String S=FR.nextLine();
			W.add(S);
		}
		FR.close();
		return W;
	}
	// configs traits of nodes for algo
	public void it_colors()
	{
		connectivity=0;
		Collection<node_info> temp=g.getV();
		Iterator<node_info> iterator = temp.iterator();

		while(iterator.hasNext()) 
		{
			node_info temp2= iterator.next();
			temp2.setInfo("White");	
			temp2.setTag(Double.MAX_VALUE);
			((NodeInfo)temp2).set_back(null);
		}
	}
	
	public void closeFile()
	{
		x.close();
	}
	public boolean equals (Object copygraph)
	//equals mplentaion for graph algo
	{
		if(copygraph instanceof weighted_graph_algorithms)
		{
			return g.equals(((weighted_graph_algorithms) copygraph).getGraph());
		}
		return false;
	 
	 }
}


