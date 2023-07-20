package Classes;

public class Distancia implements Comparable<Distancia>{
	private Cluster c1;
	private Cluster c2;
	private double distancia;

	public Distancia(Cluster c1, Cluster c2) {
		this.c1 = c1;
		this.c2 = c2;
		this.distancia = calcularDistancia();
	}
	
	public double calcularDistancia() {
		// sqrt (x1 - x2) + (y1 - y2)
		double x = Math.pow(c1.getCoord().getX() - c2.getCoord().getX(), 2);
		double y = Math.pow(c1.getCoord().getY() - c2.getCoord().getY() ,2);
		double soma = x + y;
		double raiz = Math.sqrt(soma);
		return raiz;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public Cluster getC1() {
		return c1;
	}

	public void setC1(Cluster c1) {
		this.c1 = c1;
	}

	public Cluster getC2() {
		return c2;
	}

	public void setC2(Cluster c2) {
		this.c2 = c2;
	}

	public boolean possuiCluster(Distancia d) {
		return d.c1 == this.c1 || d.c1 == this.c2 ||
				d.c2 == this.c1 || d.c2 == this.c2;
	}
	
	public int compareTo(Distancia o) {
		if (this.distancia == o.distancia)
			return 0;
		if (this.distancia < o.distancia)
			return -1;
		else
			return 1;
	}

	@Override
	public String toString() {
		return "C1: " + c1 + " C2: " + c2;
	}
	
	
}
