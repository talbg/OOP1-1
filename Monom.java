package myMath;
import java.util.Comparator;
import java.util.Iterator;

import myMath.LinkedList.Node;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	public double get_coefficient() {
		return this._coefficient;
	}
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	public boolean isZero() {return this.get_coefficient() == 0;}
	// ***************** add your code below **********************
	public Monom(String s) {
		if(valid_Monom(s)) {
			this._coefficient = parse_coefficient(s);
			this._power = parse_power(s);}
		else throw new ExceptionInInitializerError("Not a valid Monom String");
	}
	double parse_coefficient(String s){ //parsing the coefficient from the monom String
		boolean isNegative =false;
		double coefficient=0;
		double Decimalindex = 0;
		if(s.charAt(0)== '-') {
			isNegative = true;
			if(s.charAt(1)=='x')
				return -1;}
		if(s.charAt(0)== 'x')
			return 1.0;
		if ((Character.isDigit(s.charAt(0))||Character.isDigit(s.charAt(1))) && !s.contains("x")) {
			return Double.parseDouble(s);
		}
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) !='-' && s.charAt(i) !='.' && s.charAt(i) !='x' )
				coefficient = coefficient*10 + (double)Character.getNumericValue(s.charAt(i));
			else if (s.charAt(i)=='.')
				Decimalindex = i;
			else if (s.charAt(i) == 'x') { 
				if (Decimalindex!= 0) {
					Decimalindex = i-Decimalindex-1;}
				if(isNegative==true) {
					return (coefficient/(Math.pow(10, Decimalindex))*-1);}
				else {
					return (coefficient/(Math.pow(10, Decimalindex)));}			}
		}
		return 0;}

	int parse_power(String s){ //parsing the power from the monom String
		int i=0;
		int exponent = 0;
		if(s.contains("^")) {
			while(s.charAt(i)!= '^' && i<s.length()) {
				i++;

			}
			while(i<s.length()-1) {
				i++;
				exponent = exponent*10+Character.getNumericValue(s.charAt(i));
			}
			return exponent;
		}
		else if (s.contains("x"))
		{
			return 1;
		}
		else return 0;
	}

	public void add(Monom m) {
		if(this._power == m._power) {
			this._coefficient+=m._coefficient;
		}
	}

	public void multiply(Monom d) {
		this._coefficient *= d._coefficient;
		this._power+=d._power;

	}

	public String toString() {
		String ans ="";
		if(isZero())
			ans = "0.0";
		else {
			if (this._power == 0)
				ans = ""+this._coefficient;
			else if (this._power == 1) 
				ans = this._coefficient + "x";
			else
				ans = this._coefficient + "x^"+ this._power;
		}
		return ans;
	}
	public boolean valid_Monom(String monom) {
		if (monom.matches("^[+-]?(\\d+(\\.\\d+)?)?(x(\\^\\d+)?)?$") && monom.length()>0)
			return true;
		return false;

	}

	public boolean equals(Monom p1) {
		if(this.get_coefficient() == p1.get_coefficient() && this.get_power() == p1.get_power())
			return true;
		return false;

	}
	//****************** Private Methods and Data *****************


	void set_coefficient(double a){
		this._coefficient = a;
	}
	void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}

	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;



}
