package Classes;

import java.util.Random;

public class Ponto implements Comparable<Ponto>{
	private double x;
	private double y;
	
	public Ponto() {
		this.x = gerarNumeroAleatorio();
		this.y = gerarNumeroAleatorio();
	}
	
	public Ponto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public int gerarNumeroAleatorio() {
		Random random = new Random();
		
		return random.nextInt(100);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	static public Ponto soma(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
		return new Ponto(p1.x + p2.x + p3.x + p4.x, p1.y + p2.y + p3.y + p4.y);
	}

	@Override
	public int compareTo(Ponto o) {
		if (this.x == o.x && this.y == o.y)
			return 0;
		else
			return -1;
	}

	@Override
	public String toString() {
		return "Ponto [x=" + x + ", y=" + y + "]";
	}
	
	
}
