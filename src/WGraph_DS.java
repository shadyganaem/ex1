package ex1.src;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;





public class WGraph_DS implements weighted_graph{
	private  HashMap<Integer, node_info> graph = new HashMap<Integer, node_info>();
	private int size;
	private int edges;
	private int modecount;

	@Override
	public node_info getNode(int key) {
		
		return graph.get(key);
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		//checks if two nodes are connected in a graph
		node_info S=getNode(node1);
		node_info E=getNode(node2);
     if(S!=null&&E!=null)
       {
		  if(((NodeInfo) S).hasNi(node2)&&((NodeInfo)E).hasNi(node1))
			  return true;
		  else 
				 return false;
       }	
       else 
	 return false;
	}

	@Override
	public double getEdge(int node1, int node2) {
		//gets edge if it exist
		if(hasEdge(node1,node2))
		{
			node_info temp=graph.get(node1);
			HashMap<Integer, Double> edges=((NodeInfo)temp).neighbors_edges;
			return edges.get(graph.get(node2).getKey());	
		}
	
		 
		return -1 ;
	}

	@Override
	public void addNode(int key) {
		//adds node
		node_info n=new NodeInfo(key);
		if(!graph.containsKey(((NodeInfo)n).getKey()))
		{
		graph.put(n.getKey(), n);
		size++;
		modecount++;
		
	}
	}

	@Override
	public void connect(int node1, int node2, double w) 
	{
		//connect two nodes ,if already conncted then changes the wieght  o(1)
				node_info A=graph.get(node1);
				node_info B=graph.get(node2);
				if(hasEdge(node1,node2)&&node1!=node2&&A!=null&&B!=null&&w>=0&&w!=getEdge(node1,node2))
				{
				change_edge(node1,node2,w);
				return;
				}

				else if(!hasEdge(node1,node2)&&node1!=node2&&A!=null&&B!=null&&w>=0)
				{
				((NodeInfo) A).addNi(B, w);
				((NodeInfo) B).addNi(A, w);
				edges++;
				modecount++;
				}
		
	}
      public void change_edge(int node1,int node2,double w)
     {// changes edge
    	  node_info A=graph.get(node1);
		  node_info B=graph.get(node2);

		
			
			((NodeInfo) A).set_edge(node2, w);
			((NodeInfo) B).set_edge(node1, w);
			modecount++;
			
	
     }
	@Override
	public Collection<node_info> getV() {
		//returns collection of nodes
		return graph.values();
	}

	@Override
	public Collection<node_info> getV(int node_id) {
		//return collection of neighbors of spefic node
		return (Collection<node_info>)((NodeInfo) graph.get(node_id)).getNi_nodes();
	}

	@Override
	public node_info removeNode(int key) {
		//remoces node and send it back
		if(!graph.containsKey(key))
			return null;
		node_info temp=graph.get(key);
		Iterator<node_info> ite = ((NodeInfo)temp).getNi_nodes().iterator();

		while(ite.hasNext()) 
		{
			node_info temp2= ite.next();
			((NodeInfo)temp2).removeNode(temp);
			edges--;

			
		}
		((NodeInfo)temp).getNi_nodes().clear();
		((NodeInfo)temp).getNi_edges().clear();

		graph.remove(key);
		size--;
		modecount++;

		return temp;
	}

	@Override
	//removes edge
	public void removeEdge(int node1, int node2) {
		node_info A=graph.get(node1);
		node_info B=graph.get(node2);
    if(A!=null&&B!=null)
     {
 		if(hasEdge(node1,node2))
		{
			((NodeInfo)A).removeNode(B);
			((NodeInfo)B).removeNode(A);
			edges--;
			modecount++;

		}
     }
		
	}

	@Override
	public int nodeSize() {
		return size;
	}

	@Override
	public int edgeSize() {
		return edges;
	}

	@Override
	public int getMC() {
		return modecount;
	}
	public void copy(weighted_graph g)
	//copies graph and connections
	{
		if(g.edgeSize()!=0&&g!=null)
		{
		Collection<node_info> temp=g.getV();
		Iterator<node_info> iterator = temp.iterator();

		while(iterator.hasNext()) 
		{
			node_info temp2= iterator.next();
			node_info newnode=new NodeInfo(temp2);
			if(!graph.containsKey(newnode.getKey()))
			{
			graph.put(newnode.getKey(), newnode);
			}
			Iterator<node_info> neighbors_nodes = ((NodeInfo)temp2).getNi_nodes().iterator();
			HashMap<Integer, Double> neighbors_edges = ((NodeInfo)temp2).neighbors_edges();
			while(neighbors_nodes.hasNext())
			{
				node_info temp3= neighbors_nodes.next();
				node_info newnode2=new NodeInfo(temp3);
				if(!graph.containsKey(newnode2.getKey()))
						{
				graph.put(newnode2.getKey(), newnode2);
						}
				connect(newnode2.getKey(),newnode.getKey(),neighbors_edges.get(temp3.getKey()));
			}	
		}

		size=g.nodeSize();
		edges=g.edgeSize();
		modecount=g.getMC();
		}
		else if(g!=null)
			graph.clear();
		size=g.nodeSize();
		edges=g.edgeSize();
		modecount=g.getMC();

	}
	public void addNode(node_info n)
	//adds node
	{
		if(n==null)return;
		if(!graph.containsKey(((NodeInfo)n).getKey()))
		{
		graph.put(n.getKey(), n);
		size++;
		modecount++;
		
	}
		
	}
	@Override
	public boolean equals (Object copygraph)
	//equals method implementaion
	{
		if(copygraph instanceof weighted_graph)
		{
			if(size!= ((weighted_graph) copygraph).nodeSize()&&edges!=((weighted_graph) copygraph).edgeSize())
			{
				return false;
			}
			Iterator<node_info> nodes = getV().iterator();
			while(nodes.hasNext())
			{
				
				node_info original=nodes.next();
				node_info copy=((weighted_graph) copygraph).getNode(original.getKey());
				if(!original.equals(copy))
				{
					return false;
				}
				Iterator<node_info> neighbors_original = ((NodeInfo)original).getNi_nodes().iterator();
				

				
				
				while(neighbors_original.hasNext())
				{
					node_info neighbors_original_node=neighbors_original.next();
					node_info neighbors_copy_node=((weighted_graph) copygraph).getNode(neighbors_original_node.getKey());
					if(getEdge(original.getKey(),neighbors_original_node.getKey())!=((weighted_graph) copygraph).getEdge(copy.getKey(), neighbors_copy_node.getKey()))
					{
						return false;
					}
					
					
				}
				
			}
			
			
			
		}
			
		return true;
	}
	
	public static class NodeInfo implements node_info, Comparable<NodeInfo>
	{
		private String info;
		private  int key;
		private static int counter=0;
		private double tag;
		private HashMap<Integer, node_info> neighbors = new HashMap<Integer, node_info>();
		private HashMap<Integer, Double> neighbors_edges = new HashMap<Integer, Double>();
		private node_info back=null;
		public NodeInfo()
		{
			key=counter;
			counter++;
			tag=Double.MAX_VALUE;
			info="White";
			
			
		}
		public NodeInfo(int key)
		{
			this.key=key;
			tag=Double.MAX_VALUE;
			info="White";
			
		}
		public NodeInfo(node_info node)
		{
			key=node.getKey();
			info=node.getInfo();
			tag=node.getTag();
		}
		public NodeInfo(int key,double tag,String info)
		{
			this.key=key;
			this.info=info;
			this.tag=tag;
		}

		@Override
		public int getKey() {
			return key;
		}

		@Override
		public String getInfo() {
			return info;
		}

		@Override
		public void setInfo(String s) {
			info=s;
			
		}

		@Override
		public double getTag() {
			return tag;
		}

		@Override
		public void setTag(double t) {
			tag=t;
			
		}
		public Collection<node_info> getNi_nodes() {
			
			return neighbors.values();
		}
        public Collection<Double> getNi_edges() {
			
			return neighbors_edges.values();
		}
		public boolean hasNi(int key) {
			//checks if has neighbors with this key
			if( neighbors.containsKey(key))
				return true;
			else 
				return false;
				
		}
		public void addNi(node_info t,double edge)
		//adds neighbor
		{
			if(!neighbors.containsKey(t.getKey())&&t!=null)
			{
			neighbors.put(t.getKey(), t);
			neighbors_edges.put(t.getKey(), edge);
			
			}
			
		}
		public void removeNode(node_info node) {
			//remove node
			neighbors.remove(node.getKey(), node);
			neighbors_edges.remove(node.getKey());
		}
		public  HashMap<Integer, Double> neighbors_edges()
		{
			return neighbors_edges;
			
		}
		public void set_edge(int node2,double w)
		//sets edge
		{
			if(neighbors_edges.containsKey(node2)&&w>=0)
			neighbors_edges.put(node2, w);
		}
		public void set_back(node_info node)
		// back node set for shortest path list
		{
			if(node!=null)
				back=node;
			
		}
		public node_info get_back()
		{
			return back;	
		}
		public String toString()
		{
			return " [ "+key+" , "+tag+" , "+info+" ] ";
		}
		@Override
		public boolean equals(Object n)
		// equals method for oode
		{
			if(n==null)
				return false;
			if(n instanceof node_info)
			{
				if(((NodeInfo)n).getKey()==key&&((NodeInfo)n).getInfo().equals(info)&&((NodeInfo)n).getTag()==tag)
					return true;
				else return false;
			}
			
		return false;	
		}

		@Override
		//compare method for priority que from min to max
		public int compareTo(NodeInfo node) {
			if(tag<node.getTag())
			{
				return -1;
			}
			else if(tag==node.getTag())
			{
				return 0;
			}
			else
			return 1;
		}
	}
	
	
    

}

