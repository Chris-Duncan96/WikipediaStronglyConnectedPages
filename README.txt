This program generates a graph of how Wikipedia pages are linked.

First, WikiCrawler crawls through a given wikipedia page for other Wiki links 
(This starts after the start of the first paragraph, to avoid the top-of-page links).
This is repeated for a given number of pages, to collect the links from those pages as well
This information is put into a specified text file.

Second, GraphProcessor generates a graph, comprised of linkData Objects. 
GraphProcessor can be used to find paths between pages (both through DFS and BFS),
it can also count edges and verticies, as well as locating strongly connected components (SCC),
check if two nodes are part of the same SCC, count the out degree of a given node,
fetch SCC's, count the total SCC's, check if two nodes are in the same SCC,
or get all nodes in the same SCC as a given node.


Example data is included for the Computer Science Wikipedia page.