package Classes;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Ponto> listaPonto = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			Ponto p = new Ponto();
			listaPonto.add(p);
		}
		
		
		List<Cluster> listaCluster = new ArrayList<>();
		for (Ponto p : listaPonto) {
			Cluster c = new Cluster(p);
			listaCluster.add(c);
		}
		
		List<Distancia> listaDistancia = new ArrayList<>();
		while (listaCluster.size()+1 > 1) {
			listaDistancia = calcularDistancias(listaCluster);
			Distancia menor = acharMenorDistancia(listaDistancia);
			
			Ponto novoCentroide = criarCentroide(menor.getC1(), menor.getC2());
			Cluster novoCluster = new Cluster(novoCentroide);
			
			removerClusteresDaLista(menor, listaCluster);
			listaCluster.add(novoCluster);
		}

	}
	
	public static void imprimirNovoCluster(Distancia distancia) {
		System.out.println("Novo cluster: " + distancia.getC1() + "/" + distancia.getC2());
	}
	
	public static void removerClusteresDaLista(Distancia distancia, List<Cluster> listaCluster){
		for (Cluster c : listaCluster) {
			if (c.equals(distancia.getC1()) || c.equals(distancia.getC2())) {
				listaCluster.remove(c);
			}
		}
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
			for (int j = i + 1; j < listaCluster.size()-1; i++) {
				Distancia d = new Distancia(listaCluster.get(i), listaCluster.get(j));//aqui ele tá passando o cluster como null
				listaDistancia.add(d);
			}
		}
		return listaDistancia;
	}
	
}
