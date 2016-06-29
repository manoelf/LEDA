package formas;

public class Main {
	
	public static void main(String[] args) {

		FormaGeometrica forma;
		
		forma = new Circulo(5);
		System.out.println(forma.calculoArea());
		

		forma = new Quadrado(4);
		System.out.println(forma.calculoArea());

		forma = new Retangulo(5, 2.5);
		System.out.println(forma.calculoArea());

		forma = new Triangulo(3, 5.4);
		System.out.println(forma.calculoArea());
	
	} 	
}
