package Classes;

import java.util.ArrayList;
import java.util.List;

public class Cluster implements Comparable<Cluster>{
	public List<Ponto> pontos;
	private Ponto centroide;

	public Cluster(Ponto centroide) {
		super();
		this.pontos = new ArrayList<>();
		this.centroide = centroide;
	}

	public Cluster (List<Ponto> pontos1, List<Ponto> pontos2){
		super();
		this.pontos = inicializarListaDePontos(pontos1, pontos2);
		this.centroide = gerarCentroide();
		this.centroide = gerarCentroide();
	}

	public Ponto gerarCentroide(){
		double x = 0;
		double y = 0;
		for (Ponto p : this.pontos){
			x += p.x;
			y += p.y;
		}
		x = x / pontos.size();
		y = y / pontos.size();

		return new Ponto(x, y);
	}
	public List<Ponto> inicializarListaDePontos(List<Ponto> pontos1, List<Ponto> pontos2){
		List<Ponto> listaPontos = new ArrayList<>();
		for (Ponto p : pontos1){
			listaPontos.add(p);
		}
		for (Ponto p : pontos2){
			listaPontos.add(p);
		}

		return listaPontos;
	}

	public Ponto getCentroide() {
		return centroide;
	}

	public void adicionarPonto(Ponto ponto){
		pontos.add(ponto);
	}

	public List<Ponto> getPontos() {
		return pontos;
	}

	public void setCentroide(Ponto centroide) {
		this.centroide = centroide;
	}

	@Override
	public int compareTo(Cluster o) {
		if (this.centroide.compareTo(o.getCentroide()) == 0)
			return 0;
		if (this.centroide.compareTo(o.getCentroide()) < 0)
			return -1;
		else
			return 1;
	}

	@Override
	public String toString() {
		return "X: " + centroide.x + " Y: " + centroide.y;
	}
	
	
	
	
}
