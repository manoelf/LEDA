package formas;

public class Circulo implements FormaGeometrica{
	
	private final double PI = Math.PI;
	
	private double raio;
	
	public Circulo(double raio) {
		this.raio = raio;
	}

	@Override
	public double calculoArea() {
		return PI * raio * raio;
	}
	

}
