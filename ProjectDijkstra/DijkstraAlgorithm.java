import java.util.*;

public class DijkstraAlgorithm {
    private static final int Infinity = Integer.MAX_VALUE;

    public static void dijkstra(int[][] graph, int source, int[] tujuan, int numberOfDestination, String[] city, int totalDistance) {
        int vertices = graph.length;
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int shortest = Infinity, minKota = Infinity;

        Arrays.fill(distances, Infinity);
        distances[source] = 0;

        for (int i = 0; i < vertices - 1; i++) {
            int minVertex = findMinVertex(distances, visited);
            visited[minVertex] = true;

            for (int j = 0; j < vertices; j++) {
                if (graph[minVertex][j] != 0 && !visited[j] && distances[minVertex] != Infinity) {
                    int newDistance = distances[minVertex] + graph[minVertex][j];
                    if (newDistance < distances[j]) {
                        distances[j] = newDistance;
                    }
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < tujuan.length; j++) {
                if (i == tujuan[j]) {
                    if (distances[i] < shortest && distances[i] != 0) {
                        shortest = distances[i];
                        minKota = i;
                    }
                }
            }
        }
        totalDistance += shortest;

        numberOfDestination--;
        System.out.print("--->"+city[minKota]);
        if (numberOfDestination == 0){
         System.out.println("");
         System.out.println("Total jarak yang ditempuh adalah: " + totalDistance);
        }
        for (int i = 0; i < tujuan.length; i++) {
            if (tujuan[i] == minKota) {
                tujuan[i] = -1;
            }
        }
        if (minKota != source && numberOfDestination > 0) {
            dijkstra(graph, minKota, tujuan, numberOfDestination, city, totalDistance);
        }
    }

    private static int findMinVertex(int[] distances, boolean[] visited) {
        int minVertex = -1;
        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && (minVertex == -1 || distances[i] < distances[minVertex])) {
                minVertex = i;
            }
        }
        return minVertex;
    }

    private static void printDistances(int[] distances) {
        System.out.println("Vertex\t\tDistance from Source");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(i + "\t\t" + distances[i]);
        }
    }

    public static void djikstra() {
        int[][] graph = {
            { 0, 25, 75, 0, 0, 0, 0, 0 },              // Jakarta
            { 25, 0, 0, 75, 0, 0, 0, 0 },              // Serang
            { 75, 0, 0, 30, 55, 0, 0, 0 },             // Tangerang
            { 0, 75, 30, 0, 44, 0, 0, 0},              // Bekasi
            { 0, 0, 55, 0, 0, 30, 0, 45 },             // Karawang
            { 0, 0, 0, 0, 30, 0, 75, 100 },            // Pandeglang
            { 0, 0, 0, 0, 0, 75, 0, 100 },             // Purwakarta
            { 0, 0, 0, 0, 45, 100, 100, 0 }            // Bogor
        };
        
        String[] Kota = {"Jakarta", "Serang", "Tangerang", "Bekasi", "Karawang", "Pandeglang", "Purwakarta", "Bogor"};
        
        int[] tujuan = new int[0];
        int numberOfDestination = 0;
        

        for (int i = 0; i < Kota.length; i++) {
            System.out.println("Destination : " + (i+1) + "." + Kota[i]);
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Starting Point: ");
        int startingPoint = scanner.nextInt()-1;

        if (startingPoint < 1 && startingPoint > Kota.length) {
            System.out.println("Invalid starting point!");
        }

        System.out.println("");

        System.out.print("Enter the number of destinations: ");
        numberOfDestination = scanner.nextInt();
		
		System.out.println("");

        tujuan = new int[numberOfDestination];
		for(int i=0; i<numberOfDestination; i++){
			tujuan[i]=-1;
		}
        boolean[] cek = new boolean[Kota.length];
        for (int i = 0; i < numberOfDestination; i++) {
            for(int j=0; j<Kota.length; j++){
                for(int k=0; k<numberOfDestination; k++){
                    if(j==tujuan[k]||j==startingPoint){
						cek[j]=false;
						break;
					}
					else cek[j]=true;
                }
				if(cek[j]==true) System.out.println("Destination : " + (j+1) + "." + Kota[j]);
            }
            System.out.print("Enter the destination indices (1 to " + (Kota.length ) + "):");
            tujuan[i] = scanner.nextInt()-1;
			System.out.println("");
        }
		System.out.print(Kota[startingPoint]);
        dijkstra(graph, startingPoint, tujuan, tujuan.length, Kota, 0);

        scanner.close();
	}
}