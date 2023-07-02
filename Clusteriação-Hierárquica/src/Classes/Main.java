package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		//10, 20, 30, 40, 50, 100, 200, 500, 1.000, 5.000, 10.000, 20.000, 50.000 e 100.000
		int qtdEntradas =320;
		List tempos = new ArrayList<>();
		int iteracoes = 0;
		while(iteracoes != 10){
			List<Ponto> listaPonto = new ArrayList<>();
			for (int i = 0; i < qtdEntradas; i++) {
				Ponto p = new Ponto();
				listaPonto.add(p);
			}

			List<Cluster> listaCluster = new ArrayList<>();
			for (Ponto p : listaPonto) {
				Cluster c = new Cluster(p);
				listaCluster.add(c);
			}


			long startTime = System.currentTimeMillis();

			List<Distancia> listaDistancia;
			while (listaCluster.size() > 1) {
				listaDistancia = calcularDistancias(listaCluster);
				Distancia menor = acharMenorDistancia(listaDistancia);
				Cluster novoCluster = new Cluster(menor.getC1().getPontos(), menor.getC2().getPontos());
				removerClusteresDaLista(menor, listaCluster);
				listaCluster.add(novoCluster);
			}
			long endTime = System.currentTimeMillis();
			tempos.add(endTime-startTime);
			FileWriter fileWriter = new FileWriter("registro.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("Entradas: " + qtdEntradas + "\nTempo: " + (endTime - startTime) + " milissegundos\n");
			bufferedWriter.close();

			iteracoes++;
		}
		FileWriter fileWriter = new FileWriter("registro.txt", true);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.write("\nMédia: " + fazMedia(tempos) + "\n\n");
		bufferedWriter.close();
	}

	public static long fazMedia(List<Long> tempos){
		long soma = 0;
		for (long tempo : tempos){
			soma+= tempo;
		}
		return soma / 10;

	}
	
	public static void removerClusteresDaLista(Distancia distancia, List<Cluster> listaCluster){
		List<Cluster> toRemove = new ArrayList<>();
		for (Cluster c : listaCluster) {
			if (c.equals(distancia.getC1()) || c.equals(distancia.getC2())) {
				toRemove.add(c);
			}
		}
		listaCluster.removeAll(toRemove);
	}
	
	public static Distancia acharMenorDistancia(List<Distancia> listaDistancia) {
		Distancia menor = listaDistancia.get(0);
		for (Distancia d : listaDistancia) {
			if (d.compareTo(menor) < 0)
				menor = d;
		}
		return menor;
	}
	
	public static List<Distancia> calcularDistancias(List<Cluster> listaCluster) {
		List<Distancia> listaDistancia = new ArrayList<>();
		for (int i = 0; i < listaCluster.size()-1; i++) {
			for (int j = i + 1; j < listaCluster.size(); j++) {
				Distancia d = new Distancia(listaCluster.get(i), listaCluster.get(j));
				listaDistancia.add(d);
			}
		}
		return listaDistancia;
	}
	
}
