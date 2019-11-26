//This is our class tester, we had much more tests that were performed during the programming.
//It covers all of the functions with a variety of test cases.

package myMath;
public class ClassTester {
	public static void main(String[] args) {
		
		String[] rootpolynoms = {"5x-7", "-4x+8","8x-15","4x^2+x-6","-3x+9","2x+4+2x^2"};
		String[] wrongpolynoms = {"-3,5", "","3..","3x^-x","3t^-3","3xx","--3x","++3x","-3x^2.23"};
		String[] goodpolynoms = {"2+2x^4-5", "-x-14x^2","-3.2x^2+5+8+12x^3","4x^2-1.2x^7","-1.5x^2+22.5x^2","7x^5-3x+1"};
		
		TEST(goodpolynoms,wrongpolynoms,rootpolynoms);
		}
	
	
	/**
	 * 
	 *  * good represent correct strings from the user input,  
	 *  * bad represent incorrect strings from the user input, 
	 *  * root represent correct strings for calculating root.
	 */
	
		static void TEST(String[] good, String[] bad,String[] root) {
		Monom m1=new Monom ("4x^3");
		for(int i=0;i<good.length-1;i++) {
			Polynom p1= new Polynom(good[i]);
			Polynom p2= new Polynom(good[i+1]);
			Polynom p3= new Polynom(root[i]);
			System.out.println("test "+(i+1)+":");
			System.out.println("polynom 1: "+p1.toString());
			System.out.println("polynom 2: "+p2.toString());
			System.out.println("polynom 1 + polynom 2: "+testAddP(p1,p2));	
			System.out.println("polynom 1 + 4x^3:"+testAddM(p1,m1));
			System.out.println("polynom 1 * polynom 2:"+testMultP(p1,p2));
			System.out.println("polynom 1 * 4x^3:"+testMultM(p1,m1));
			System.out.println("polynom 1 equal to polynom 2?: "+testEquals(p1,p2));
			System.out.println("polynom 1 derived: "+testDerivative(p1));
			System.out.println("polynom 1 area:" + testArea(p1));
			System.out.println("polynom 1 root:"+testRoot(p3));
			System.out.println();
		}
		for(int i=0;i<bad.length-1;i++) {
			Polynom p1= new Polynom(bad[i]);
			Polynom p2= new Polynom(bad[i+1]);
			Polynom p3= new Polynom(root[i]);
			System.out.println("test "+(i+1)+":");
			System.out.println("polynom1: "+p1.toString());
			System.out.println("polynom2: "+p2.toString());
			System.out.println("polynom1+plynom 2: "+testAddP(p1,p2));	
			System.out.println("polynom1+4x^3: "+testAddM(p1,m1));
			System.out.println("polynom1*polynom 2: "+testMultP(p1,p2));
			System.out.println("polynom1*4x^3: "+testMultM(p1,m1));
			System.out.println("polynom1 equal to polynom 2?: "+testEquals(p1,p2));
			System.out.println("polynom1 derived: "+testDerivative(p1));
			System.out.println("polynom1 area: " + testArea(p1));
			System.out.println("polynom1 root: "+testRoot(p3));
			System.out.println();
		}


	}

	static Polynom testAddP(Polynom p1,Polynom p2) {
		Polynom temp1 =new Polynom();
		temp1.copyPolynom(p1);
		temp1.add(p2);
		return temp1;
	}
	static Polynom testAddM(Polynom p1,Monom m1) {
		Polynom temp1 =new Polynom();
		temp1.copyPolynom(p1);
		temp1.add(m1);
		return temp1;
	}
	static Polynom testMultP(Polynom p1,Polynom p2) {
		Polynom temp1 =new Polynom();
		temp1.copyPolynom(p1);
		Polynom temp2 = new Polynom();
		temp2.copyPolynom(p2);
		temp1.multiply(p2);
		return temp1;
	}
	static Polynom testMultM(Polynom p1,Monom m1) {
		Polynom temp1 =new Polynom();
		temp1.copyPolynom(p1);
		temp1.multiply(m1);
		return temp1;
	}
	static boolean testEquals(Polynom p1,Polynom p2) {
		return p1.equals(p2);

	}
	static Polynom_able testDerivative(Polynom p1) {
		return p1.derivative();
	}
	static double testArea(Polynom p1) {
		return p1.area(1, 6, 1);
	}
	static double testRoot(Polynom p1) {

		return p1.root(1,6,1);
	}
}
