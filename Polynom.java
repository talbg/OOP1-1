
package myMath;
import java.util.Iterator;
import myMath.LinkedList;
import myMath.Monom;
import myMath.LinkedList.Node;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{
	public LinkedList PolynomList = new LinkedList();

	public Polynom() {
		Monom moni = new Monom(0,0);
		this.PolynomList.head = null;
	}

	public Polynom(String s) {
		/**
		 * init a Polynom from a String such as:
		 *  {"x", "3+1.4X^3-34x", "(2x^2-4)*(-1.2x-7.1)", "(3-3.4x+1)*((3.1x-1.2)-(3X^2-3.1))"};
		 * @param s: is a string represents a Polynom
		 */
		s = s.replaceAll("\\-0.0","0.0");
		s = s.replaceAll("\\--","+");			
		s = s.replaceAll("\\-+","-");
		
		if(valid_Polynom(s)) //check if the string is valid
		{

			if(s.substring(1).contains("-") || s.substring(1).contains("+")) {
				String temp = s;
				temp = temp.replaceAll("\\-", "+-"); //replacing all '-' with '+-' in order to split
				String[] arr = temp.split("\\+"); //splitting by '+' to Monoms
				for (int i = 0; i < arr.length; i++) { //iterating over the Monoms
					if(arr[i].length()>0) { 
						Monom tempMon = new Monom(arr[i]); 
						this.add(tempMon); //adding each Monom to the Polynom.
					}
				}
				this.SortPolynom(); //Sort it just in case (should be sorted anyway)
			}
			else { //if we didn't find any +- in the string we assume it's a Monom.
				if (s.length()!=0) 
				{Monom toAdd = new Monom(s);
				this.add(toAdd);
				}
				else throw new IllegalArgumentException("No String entered."); //length of the String is 0

			}
		}
		else throw new IllegalArgumentException("Wrong String input, please recheck your string"); //invalid string

	} //end of parse


	public int listSize() {
		return this.PolynomList.size;
	}
	public void printPolynomList() {
		LinkedList.printList(PolynomList);
	}

	@Override
	public double f(double x) { //iterating over the Polynom and using Monom.f(int)
		Node current = this.PolynomList.head;
		double value = 0;
		while(current!=null) {
			value+= current.data.f(x);
			current=current.next;

		}
		return value;
	}

	@Override
	public void add(Polynom_able p1) {
		Iterator<Monom> itr = p1.iterator();
		while(itr.hasNext()) {
			this.add(itr.next());
		}
		p1.SortPolynom();
	}

	@Override
	public void add(Monom m1) {
		Node currNode = this.PolynomList.head;
		boolean exponent_found = false;
		while(currNode!=null && currNode.data!=null) {
			if(m1.get_power()==currNode.data.get_power())
			{
				exponent_found = true;
				if(m1.get_coefficient()+currNode.data.get_coefficient() == 0)
				{
					LinkedList.deleteByMonom(PolynomList, currNode.data);
					break;
				}
				else {
					currNode.data.set_coefficient(m1.get_coefficient() + currNode.data.get_coefficient());
					break;
				}
			}

			currNode = currNode.next;
		}
		if(currNode==null)
			LinkedList.insert(PolynomList, m1);
	}

	//work on this! (not done)
	@Override
	public void substract(Polynom_able p1) {
		Monom MINUS1 = new Monom(-1,0);
		p1.multiply(MINUS1);
		Iterator<Monom> itr = p1.iterator();
		while(itr.hasNext()) {
			this.add(itr.next());
		}			
	}

	@Override
	public void multiply(Polynom_able p1) {
		Iterator<Monom> itr = p1.iterator();
		Polynom temp = new Polynom();
		Polynom finalsum = new Polynom();
		while(itr.hasNext()) {
			temp.copyPolynom(this);
			temp.multiply(itr.next());
			finalsum.add(temp);

		}
		this.copyPolynom(finalsum);
	}


	public boolean equals(Polynom_able p1) {
		int counter = 0;
		this.SortPolynom();
		p1.SortPolynom();
		if(p1.listSize()!= this.listSize()) return false;
		Node current = this.PolynomList.head;
		Iterator<Monom> itr = p1.iterator();
		Polynom poli = new Polynom();
		while(itr.hasNext()) {
			poli.add(itr.next());
		}
		Node current1 = poli.PolynomList.head;
		while(current!=null) {
			if(current.data.get_coefficient() ==current1.data.get_coefficient() && current.data.get_power() == current1.data.get_power()) {
				counter++;}
				if (counter==poli.listSize()) {
					return true;}
				current1 = current1.next;
				current = current.next;
			}

		return false;

	}
	@Override
	public boolean isZero() {
		Node current = this.PolynomList.head;
		while(current != null) {
			if(this.PolynomList.head ==null) 
				return false;
		}
		return true;
	}

	@Override
	public double root(double x0, double x1, double eps) {
		double x0_totalvalue = this.f(x0);
		double x1_totalvalue = this.f(x1);
		double x_middle = (x0+x1)/2;
		double y_middle = this.f((x0+x1)/2.0);
		if(x0>x1 || (x0_totalvalue*x1_totalvalue)>0)
			throw new ArithmeticException("Both positive/negative or x0>x1, please recheck "); // throw
		if((Math.abs(x0_totalvalue))<= eps)
			return x0;
		if((Math.abs(x1_totalvalue))<= eps)
			return x1;
		if(Math.abs(y_middle)<eps) 
			return Math.round(x_middle);
		if(x0_totalvalue >0 && x1_totalvalue<0 ){
			if(y_middle>0)
				return Math.round((root(x_middle,x1,eps)));
			if(y_middle<0) 
				return Math.round((root(x0,x_middle,eps))); }
		if(x0_totalvalue<0&&x1_totalvalue>0){
			if(y_middle<0)
				return Math.round((root(x_middle,x1,eps)));
			if(y_middle>0) 
				return Math.round((root(x0,x_middle,eps)));}
		throw new ArithmeticException("Unknown error occured, please recheck values"); //throw exception
	}


	@Override
	public Polynom_able copy() {
		Node current = this.PolynomList.head;
		Polynom temp = new Polynom();
		while(current!=null) {
			temp.add(current.data);
			current=current.next;
		}
		return temp;
	}

	@Override
	public Polynom_able derivative() {
		Polynom p1 = new Polynom();
		Iterator<Monom> itr = this.iterator();
		while (itr.hasNext()) { 
			p1.add(itr.next().derivative());
		}
		return p1; 
	}

	@Override
	public double area(double x0, double x1, double eps) {
	double area = 0;
		if(x0 > x1 || eps == 0 ) {
			throw new ArithmeticException("x0>x1 or eps=0, please recheck ");
		}   
		else {
			this.Integral();
			Node current = this.PolynomList.head;
			while (current != null) {
				double bufferright , bufferleft = x0;       
				while (bufferleft < x1) {          
					bufferright = bufferleft+eps;
					if ( bufferright > x1) {   // if the sum of the left buffer and the epsilon is above x1,
								   // calculate the are between x1 and buffer left
						area = area + current.data.get_coefficient()*Math.pow(x1, current.data.get_power());
						area = area - current.data.get_coefficient()*Math.pow(bufferleft, current.data.get_power());
						break;
					}
					else {			   // standard calculation
						area = area + current.data.get_coefficient()*Math.pow(bufferright, current.data.get_power());
						area = area - current.data.get_coefficient()*Math.pow(bufferleft, current.data.get_power());
					}
					bufferleft = bufferright;
				}
				current = current.next;
			}
		}
		return Math.round(area);
	}

	public void Integral() {
		Node current = this.PolynomList.head;
		while (current != null) {
			current.data.set_power(current.data.get_power()+1);
			current.data.set_coefficient(current.data.get_coefficient() / current.data.get_power());
			current = current.next;
		}
	}

	@Override
	public Iterator<Monom> iterator() {
		return this.PolynomList.iterator();
	}
	@Override
	public void multiply(Monom m1) {
		Node current = this.PolynomList.head;
		while(current!=null) {
			if(m1.isZero())
				LinkedList.deleteByMonom(this.PolynomList,current.data);
			else {
				current.data.multiply(m1);
			}
			current = current.next;
		}
	}

	public void copyPolynom(Polynom p1) {
		Node thiscurr = this.PolynomList.head;
		while(thiscurr!=null) {
			LinkedList.deleteByMonom(PolynomList, thiscurr.data);
			thiscurr=thiscurr.next;
		}
		Node current = p1.PolynomList.head;
		while(current!=null) {
			this.add(current.data);
			current = current.next;
		}
	}
	public String toString() {
		this.SortPolynom();
		Node current = this.PolynomList.head;
		this.SortPolynom();
		boolean firstMonom = true;
		String returned = "";
		while(current!=null) {
			if(firstMonom==true){
				returned+= current.data.toString();
				firstMonom = false;
			}
			else {
				if(current.data.toString().contains("-"))
					returned+= current.data.toString();
				else 
					returned+= "+" +current.data.toString();
			}
			current=current.next;
		}
		return returned;
	}

	@SuppressWarnings("static-access")
	public void SortPolynom(){
		this.PolynomList.insertionSort(this.PolynomList.head);

	}

		public boolean valid_Polynom(String s) {
		
		if(s.contains("++") || (s.length()==0))
			return false;
		String temp = s;
		temp =temp.replaceAll("\\-", "+-");
		if(temp.charAt(0)=='+'&&temp.charAt(1)=='-') {
			temp=temp.substring(1);

		}
		String[] arr = temp.split("\\+");
		for (int i = 0; i < arr.length; i++) {
			if(!arr[i].matches("^[+-]?(\\d+(\\.\\d+)?)?(x(\\^\\d+)?)?$")) 
				return false;			
		}
		return true;

	}

		@Override
		public function initFromString(String s) {
			// TODO Auto-generated method stub
			return null;
		}

	


}
