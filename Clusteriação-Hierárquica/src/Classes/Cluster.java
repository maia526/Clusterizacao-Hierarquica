package Classes;

public class Cluster implements Comparable<Cluster>{
	private Ponto centroide;

	public Cluster(Ponto centroide) {
		super();
		this.centroide = centroide;
	}

	public Ponto getCentroide() {
		return centroide;
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
