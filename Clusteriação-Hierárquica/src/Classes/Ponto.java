package Classes;

import java.util.Random;

public class Ponto implements Comparable<Ponto>{
	public double x;
	public double y;
	
	public Ponto() {
		super();
		this.x = gerarNumeroAleatorio();
		this.y = gerarNumeroAleatorio();
	}
	
	public Ponto(double x, double y) {
		super();
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

	public void setX(int x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
