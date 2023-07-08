package Classes;

public class Cluster implements Comparable<Cluster>{

	private Ponto coord;
	private Arvbin<Cluster> raiz;
	private Ponto acumulado;
	private int qtdPontos;

	public Cluster(Ponto coord) {
		this.coord = coord;
		this.acumulado = new Ponto(coord.getX(), coord.getY());
		this.qtdPontos = 1;

		this.raiz = new Arvbin<>(this);
	}

	public Cluster(Distancia menor){

		Cluster c1 = menor.getC1();
		Cluster c2 = menor.getC2();

		this.acumulado = Ponto.soma(c1.acumulado, c2.acumulado);

		this.qtdPontos = c1.qtdPontos + c2.qtdPontos;
		this.coord = new Ponto(acumulado.getX() / qtdPontos, acumulado.getY() / qtdPontos);

		this.raiz = new Arvbin<>(this, c1.getRaiz(), c2.getRaiz());
	}

	public Arvbin<Cluster> getRaiz() {
		return raiz;
	}


	public Ponto getCoord() {
		return coord;
	}

	public Ponto getAcumulado() {
		return acumulado;
	}

	public int getQtdPontos() {
		return qtdPontos;
	}

	@Override
	public int compareTo(Cluster o) {
		if (this.coord.getX() == o.coord.getX() && this.coord.getY() == o.coord.getY())
			return 0;
		if (this.coord.getX() < o.coord.getX() && this.coord.getY() < o.coord.getY())
			return -1;
		else
			return 1;
	}

	@Override
	public String toString() {
		return "X: " + this.coord.getX() + " Y: " + this.coord.getY();
	}

}
