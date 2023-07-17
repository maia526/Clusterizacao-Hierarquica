package Classes;

import java.util.Random;

public class Ponto implements Comparable<Ponto>{
	private double x;
	private double y;

	private static Random random = new Random();
	
	public Ponto(int limite) {
		this.x = gerarNumeroAleatorio(limite);
		this.y = gerarNumeroAleatorio(limite);
	}
	
	public Ponto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public int gerarNumeroAleatorio(int limite) {
		return random.nextInt(limite);
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

	public Ponto soma(Ponto p2) {
		return new Ponto(this.x + p2.x, this.y + p2.y);
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
