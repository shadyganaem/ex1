NodeData class:


1.added a deep copy constructor for the copy method in graph_algo
2.key is asssured to be exculize as i was using a static counter for all nodes and when ever i create a new node i say that the key is equal to current counter then increment it
3.added a hashmap for edgs

Graph_DS class:

1. i have four traits size of nodes ,edges , mc and a hashmap for all nodes in the graph
2. has edges checksif two nodes in a graph are connected o(1);
3. add node checks if graph has anode if not it adds it
4.connect checks if two nodes are already connected if not then they get connected
5. remove node accesses the node's neighbors deletes edge with them one by one while updating mode count and num of edges then removes the node from the graph and returns the node
6. copy method is O(n^2) it copies each node from the old graph then after each copy it enters its neighbors adds them to graph as long as they are not thjere then connects the new nodes to the new father node 

Graph_Algo class:

1. graph algo has two traits graph ds type of graph and an integer by the name of connectivity
2. O(nodes+edges) is connected method is based on BFS search where i config my graph with tags equalt to -1 and trait of White in info, i start with a random node in the graph and then
   breadth through the neighbors simiultaesly while updating incrementing the tags by 1 and coloring visited nodes by black and updated tag 
   nodes by gray and for each visited node i increment connecitivy by one then at the end i check if connectivity is equal to the nodes size in graph
3.O(nodes+edges)shotest path between two nodes is basicly using djekstra as aswell but instead of checking the number of nodes i visted at the end, i return the id for the end node if 
  such path is available if not then i return -1 
4.O(nodes+edges+disance)shortest path list ' i run shortest path at the start to get the distancce, if its not equal to -1 then i create a previously set size array with the size of distance +1, i get the end node 
  and traverse through back through back nodes like a linked list and add tot he list  to the LIST from end to start ( revrse adding ) till i reach start where it has an id equal to 0
5. O(nodes)It colors methd configs the graph with info equal to white and tag equal to double max value  so the methods above work 
