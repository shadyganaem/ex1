package ex1.tests;
import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;
import ex1.src.WGraph_DS.NodeInfo;
import ex1.src.node_info;
import ex1.src.weighted_graph;
import ex1.src.weighted_graph_algorithms;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class test_weighted_graph {
	@Test
	void one_mill_nodes_10_mill_edges_runtime_test_and_connected() {
		long start = new Date().getTime();
		weighted_graph g0=new WGraph_DS();
		for(int i=0;i<1000000;i++)
		{
		g0.addNode(i);
		}
		for(int j=0;j<11;j++)
		{
			for(int i=0;i<1000000;i++)
			{
				g0.connect(j, i, 0);
			}
		}
		
		long end = new Date().getTime();
        double dt = (end-start)/1000.0;
        boolean t = dt<10;
        assertTrue(t, "runtime test is longer than 10sec to make non-random graph of 1m verts+1m, time taken in seconds: " + dt);
		weighted_graph_algorithms graph=new WGraph_Algo();
		graph.init(g0);
        assertTrue(graph.isConnected(),"graph should be connected");
	
	}

	@Test
	void check_one_node_size() {
		weighted_graph g0=new WGraph_DS();
		node_info N1=new NodeInfo();
		weighted_graph_algorithms graph=new WGraph_Algo();
		g0.addNode(N1.getKey());
		graph.init(g0);
		boolean x =g0.nodeSize()==1;
        assertTrue(x, "size is not accurate it should be one, actual size is equal to  "+g0.nodeSize() );
	
	}
	@Test
	void check_two_nodes_edge() {
		weighted_graph g0=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		weighted_graph_algorithms graph=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.connect(N1.getKey(), N2.getKey(), 5);
		graph.init(g0);
		boolean x =g0.getEdge(N1.getKey(), N2.getKey())==5;
        assertTrue(x, "edge is not accurate it should be one, actual size is equal to  " +g0.getEdge(N1.getKey(), N2.getKey()));
	
	}
	@Test
	void check_two_nodes_connection() {
		weighted_graph g0=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		weighted_graph_algorithms graph=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.connect(N1.getKey(), N2.getKey(), 5);
		graph.init(g0);
		boolean x =g0.hasEdge(N1.getKey(), N2.getKey());
        assertTrue(x, "nodes  should be connected");
	
	}
	@Test
	void check_connectevity_smallgraph() {
		weighted_graph g0=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		node_info N3=new NodeInfo();
		node_info N4=new NodeInfo();
		node_info N5=new NodeInfo();
		node_info N6=new NodeInfo();
		node_info N7=new NodeInfo();

		weighted_graph_algorithms graph=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.addNode(N3.getKey());
		g0.addNode(N4.getKey());
		g0.addNode(N5.getKey());
		g0.addNode(N6.getKey());
		g0.addNode(N7.getKey());
		g0.connect(N1.getKey(), N2.getKey(),0.2);
		g0.connect(N1.getKey(), N3.getKey(), 9.2);
		g0.connect(N1.getKey(), N4.getKey(), 3.1);
		g0.connect(N1.getKey(), N5.getKey(), 4.01);
		g0.connect(N1.getKey(), N6.getKey(), 7.2);
		g0.connect(N1.getKey(), N7.getKey(), 3);
		graph.init(g0);
		boolean x =graph.isConnected();
        assertTrue(x, "nodes  should be connected");
        g0.removeNode(N1.getKey());
		 x =graph.isConnected();
        assertFalse(x, "nodes  should not be connected");

	
	}
	@Test
	void check_shortestpath_smallgraph() {
		weighted_graph G=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		node_info N3=new NodeInfo();
		node_info N4=new NodeInfo();
	   weighted_graph_algorithms graph1=new WGraph_Algo();
		G.addNode(N1.getKey());
		G.addNode(N2.getKey());
		G.addNode(N3.getKey());
		G.addNode(N4.getKey());
		G.connect(N1.getKey(), N3.getKey(), 4);
		G.connect(N1.getKey(), N2.getKey(), 2);
		G.connect(N2.getKey(), N3.getKey(), 0.25);
		G.connect(N3.getKey(), N4.getKey(), 9);
		G.connect(N2.getKey(), N4.getKey(), 3);

		graph1.init(G);
       double x=graph1.shortestPathDist(N1.getKey(), N4.getKey());
       boolean b=x==5;
      assertTrue(b,"should be 5");

	
	}
	@Test
	void check_shortestpath_smallgraph_2() {
		weighted_graph g0=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		node_info N3=new NodeInfo();
		node_info N4=new NodeInfo();
		node_info N5=new NodeInfo();
		node_info N6=new NodeInfo();
		node_info N7=new NodeInfo();
		node_info N8=new NodeInfo();
		node_info N9=new NodeInfo();
		node_info N10=new NodeInfo();
		node_info N11=new NodeInfo();
	   weighted_graph_algorithms graph2=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.addNode(N3.getKey());
		g0.addNode(N4.getKey());
		g0.addNode(N5.getKey());
		g0.addNode(N6.getKey());
		g0.addNode(N7.getKey());
		g0.addNode(N8.getKey());
		g0.addNode(N9.getKey());
		g0.addNode(N10.getKey());
		g0.addNode(N11.getKey());
		g0.connect(N1.getKey(),N2.getKey(),1);
        g0.connect(N1.getKey(),N3.getKey(),2);
        g0.connect(N1.getKey(),N4.getKey(),3);
        g0.connect(N2.getKey(),N5.getKey(),17);
        g0.connect(N2.getKey(),N6.getKey(),1);
        g0.connect(N3.getKey(),N7.getKey(),1);
        g0.connect(N4.getKey(), N6.getKey(),10);
        g0.connect(N4.getKey(),N7.getKey(),100);
        g0.connect(N6.getKey(),N8.getKey(),1.1);
        g0.connect(N7.getKey(),N8.getKey(),10);
        g0.connect(N8.getKey(),N11.getKey(),2);
        g0.connect(N7.getKey(),N9.getKey(),30);
        g0.connect(N9.getKey(),N11.getKey(),10);
        g0.connect(N5.getKey(),N11.getKey(),30);
        g0.connect(N4.getKey(),N10.getKey(),10);
       

		graph2.init(g0);
       double x=graph2.shortestPathDist(N1.getKey(), N11.getKey());
       boolean b=x==5.1;
      assertTrue(b,"should be 5.1");

	
	}
	@Test
	void check_shortestpath_smallgraph_lengh() {
		weighted_graph g0=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		node_info N3=new NodeInfo();
		node_info N4=new NodeInfo();
		node_info N5=new NodeInfo();
		node_info N6=new NodeInfo();
		node_info N7=new NodeInfo();
		node_info N8=new NodeInfo();
		node_info N9=new NodeInfo();
		node_info N10=new NodeInfo();
		node_info N11=new NodeInfo();
	   weighted_graph_algorithms graph2=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.addNode(N3.getKey());
		g0.addNode(N4.getKey());
		g0.addNode(N5.getKey());
		g0.addNode(N6.getKey());
		g0.addNode(N7.getKey());
		g0.addNode(N8.getKey());
		g0.addNode(N9.getKey());
		g0.addNode(N10.getKey());
		g0.addNode(N11.getKey());
		g0.connect(N1.getKey(),N2.getKey(),1);
        g0.connect(N1.getKey(),N3.getKey(),2);
        g0.connect(N1.getKey(),N4.getKey(),3);
        g0.connect(N2.getKey(),N5.getKey(),17);
        g0.connect(N2.getKey(),N6.getKey(),1);
        g0.connect(N3.getKey(),N7.getKey(),1);
        g0.connect(N4.getKey(), N6.getKey(),10);
        g0.connect(N4.getKey(),N7.getKey(),100);
        g0.connect(N6.getKey(),N8.getKey(),1.1);
        g0.connect(N7.getKey(),N8.getKey(),10);
        g0.connect(N8.getKey(),N11.getKey(),2);
        g0.connect(N7.getKey(),N9.getKey(),30);
        g0.connect(N9.getKey(),N11.getKey(),10);
        g0.connect(N5.getKey(),N11.getKey(),30);
        g0.connect(N4.getKey(),N10.getKey(),10);
		graph2.init(g0);
       double x=graph2.shortestPath(N1.getKey(), N11.getKey()).size();
      boolean b=x==5;
      assertTrue(b,"should be 5");

	
	}
	@Test
	void check_copy_smallgraph_and_check_equals() {
		weighted_graph g0=new WGraph_DS();
		weighted_graph g1=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		node_info N3=new NodeInfo();
		node_info N4=new NodeInfo();
		node_info N5=new NodeInfo();
		node_info N6=new NodeInfo();
		node_info N7=new NodeInfo();
		node_info N8=new NodeInfo();
		node_info N9=new NodeInfo();
		node_info N10=new NodeInfo();
		node_info N11=new NodeInfo();
	   weighted_graph_algorithms graph2=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.addNode(N3.getKey());
		g0.addNode(N4.getKey());
		g0.addNode(N5.getKey());
		g0.addNode(N6.getKey());
		g0.addNode(N7.getKey());
		g0.addNode(N8.getKey());
		g0.addNode(N9.getKey());
		g0.addNode(N10.getKey());
		g0.addNode(N11.getKey());
		g0.connect(N1.getKey(),N2.getKey(),1);
        g0.connect(N1.getKey(),N3.getKey(),2);
        g0.connect(N1.getKey(),N4.getKey(),3);
        g0.connect(N2.getKey(),N5.getKey(),17);
        g0.connect(N2.getKey(),N6.getKey(),1);
        g0.connect(N3.getKey(),N7.getKey(),1);
        g0.connect(N4.getKey(), N6.getKey(),10);
        g0.connect(N4.getKey(),N7.getKey(),100);
        g0.connect(N6.getKey(),N8.getKey(),1.1);
        g0.connect(N7.getKey(),N8.getKey(),10);
        g0.connect(N8.getKey(),N11.getKey(),2);
        g0.connect(N7.getKey(),N9.getKey(),30);
        g0.connect(N9.getKey(),N11.getKey(),10);
        g0.connect(N5.getKey(),N11.getKey(),30);
        g0.connect(N4.getKey(),N10.getKey(),10);
		graph2.init(g0);
		weighted_graph_algorithms graph3=new WGraph_Algo();
		g1=graph2.copy();
		graph3.init(g1);
      boolean b=g1.equals(g0);
      assertTrue(b,"should be equal");
	
	}
	@Test
	void save_load_graph_check_if_equal() {
		weighted_graph g0=new WGraph_DS();
		weighted_graph g1=new WGraph_DS();
		node_info N1=new NodeInfo();
		node_info N2=new NodeInfo();
		node_info N3=new NodeInfo();
		node_info N4=new NodeInfo();
		node_info N5=new NodeInfo();
		node_info N6=new NodeInfo();
		node_info N7=new NodeInfo();
		node_info N8=new NodeInfo();
		node_info N9=new NodeInfo();
		node_info N10=new NodeInfo();
		node_info N11=new NodeInfo();
	   weighted_graph_algorithms graph2=new WGraph_Algo();
		g0.addNode(N1.getKey());
		g0.addNode(N2.getKey());
		g0.addNode(N3.getKey());
		g0.addNode(N4.getKey());
		g0.addNode(N5.getKey());
		g0.addNode(N6.getKey());
		g0.addNode(N7.getKey());
		g0.addNode(N8.getKey());
		g0.addNode(N9.getKey());
		g0.addNode(N10.getKey());
		g0.addNode(N11.getKey());
		g0.connect(N1.getKey(),N2.getKey(),1);
        g0.connect(N1.getKey(),N3.getKey(),2);
        g0.connect(N1.getKey(),N4.getKey(),3);
        g0.connect(N2.getKey(),N5.getKey(),17);
        g0.connect(N2.getKey(),N6.getKey(),1);
        g0.connect(N3.getKey(),N7.getKey(),1);
        g0.connect(N4.getKey(), N6.getKey(),10);
        g0.connect(N4.getKey(),N7.getKey(),100);
        g0.connect(N6.getKey(),N8.getKey(),1.1);
        g0.connect(N7.getKey(),N8.getKey(),10);
        g0.connect(N8.getKey(),N11.getKey(),2);
        g0.connect(N7.getKey(),N9.getKey(),30);
        g0.connect(N9.getKey(),N11.getKey(),10);
        g0.connect(N5.getKey(),N11.getKey(),30);
        g0.connect(N4.getKey(),N10.getKey(),10);
		graph2.init(g0);
		graph2.save("graph.text");
		weighted_graph_algorithms graph3=new WGraph_Algo();
	graph3.load("graph.text");
      boolean b=graph2.equals(graph3);
      assertTrue(b,"should be equal");
	
	}
	
	
	
	

}
