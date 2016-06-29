package formas;

public class Triangulo implements FormaGeometrica {

	private double base;
	private double altura;
	
	public Triangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	
	
	
	@Override
	public double calculoArea() {
		return (base * altura)/ 2.0;
	} 
	
	

}
