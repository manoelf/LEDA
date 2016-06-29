package formas;

public class Retangulo implements FormaGeometrica{

	private double base;	
	private double altura;
	
	public Retangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	
	
	@Override
	public double calculoArea() {
		return base * altura;
	}

}
