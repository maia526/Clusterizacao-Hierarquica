package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		//10, 20, 30, 40, 50, 100, 200, 500, 1.000, 5.000, 10.000, 20.000, 50.000 e 100.000
		ClusterizacaoNaive naive = new ClusterizacaoNaive(1);
		naive.clusterizacao(10);

		System.out.println("\n\n");

		ClusterizacaoHeap heap = new ClusterizacaoHeap(1);
		heap.clusterizacao(10);
	}
	
}
