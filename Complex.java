package ex1;

public class Complex {
	int a;
	int b;
	Complex(){
		a = 0; 
		b = 0;
	}
	Complex(int a, int b){
		this.a = a;
		this.b = b;
	}
	
	public Complex sum(Complex b) {
		Complex result = new Complex();
		result.a = this.a + b.a;
		result.b = this.b + b.b;
		return result;
	}
	
	public Complex product(Complex b) {
		Complex result = new Complex();
		result.a = this.a * b.a - this.b*b.b;
		result.b = this.a * b.b + this.b * b.a;
		return result;
	}
	
	public void printComplex() {
		System.out.println(a +"+"+ b+"i");
	}
	
	
	public static void main(String[] args) {
		Complex nr1 = new Complex(2, 5);
		Complex nr2 = new Complex(4, -1);
		nr1.printComplex();
		nr2.printComplex();
		System.out.println("The sum of these complex numbers is:");
		Complex sum = new Complex();
		sum = nr1.sum(nr2);
		sum.printComplex();
		System.out.println("The product of these complex numbers is:");
		Complex prod = new Complex();
		prod = nr1.product(nr2);
		prod.printComplex();



	}

}
