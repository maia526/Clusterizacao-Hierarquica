package Classes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args){
		List<Ponto> listaPonto = new ArrayList<>();
		for (int i = 0; i < 500; i++) {
			Ponto p = new Ponto();
			listaPonto.add(p);
		}


		List<Cluster> listaCluster = new ArrayList<>();
		for (Ponto p : listaPonto) {
			Cluster c = new Cluster(p);
			listaCluster.add(c);
		}


		long startTime = System.currentTimeMillis();

		List<Distancia> listaDistancia = new ArrayList<>();
		while (listaCluster.size() > 1) {

			//calcula a distância entre todos os clusters
			listaDistancia = calcularDistancias(listaCluster);
			//acha a menor
			Distancia menor = acharMenorDistancia(listaDistancia);
			//cria o centroide do novo cluster
			Ponto novoCentroide = criarCentroide(menor.getC1(), menor.getC2());
			//gera o novo clusrter
			Cluster novoCluster = new Cluster(novoCentroide);
			//remove os dois clusteres juntados da lista de clusters
			removerClusteresDaLista(menor, listaCluster);
			//adiciona o cluster novo na lista de cluster
			listaCluster.add(novoCluster);


			escreverListaClusterNoTxt(listaCluster);
		}
		long endTime = System.currentTimeMillis();

		System.out.println("Tempo: " + (endTime - startTime) + " milissegundos");
	}

	public static void escreverListaClusterNoTxt(List<Cluster> listaCluster){
		for (Cluster c : listaCluster) {
			System.out.println(c);
		}
		System.out.println("");
	}
	
	public static void imprimirNovoCluster(Distancia distancia) {
		System.out.println("Novo cluster: " + distancia.getC1() + "/" + distancia.getC2());
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
	
	public static Ponto criarCentroide(Cluster c1, Cluster c2) {
		double x = (c1.getCentroide().x + c2.getCentroide().x)/2;
		double y = (c1.getCentroide().y + c2.getCentroide().y)/2;
		
		Ponto centroide = new Ponto(x, y);
		return centroide;
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
				Distancia d = new Distancia(listaCluster.get(i), listaCluster.get(j));//todo: aqui ele tá passando o cluster como null
				listaDistancia.add(d);
			}
		}
		return listaDistancia;
	}
	
}
