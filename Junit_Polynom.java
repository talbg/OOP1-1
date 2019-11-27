package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Junit_Polynom {

	@Test
	void test_add() {
		Polynom exp = new Polynom ("5+3x^2+12x^3");
		Polynom act = new Polynom ("2x^2+9+12x^3");
		Polynom temp = new Polynom ("-4+x^2");
		act.add(temp);
		assertTrue(exp.equals(act));

	}

	@Test
	void test_substract() {
		Polynom exp = new Polynom ("x^2+13+12x^3");
	Polynom act = new Polynom ("2x^2+12x^3+9");
	Polynom temp = new Polynom ("x^2-4");
	act.substract(temp);
	assertTrue(exp.equals(act));
	}

	@Test
	void test_multiply() {
	Polynom exp = new Polynom ("12x^2+18x+6");
	Polynom act = new Polynom ("3x+3");
	Polynom temp = new Polynom ("4x+2");
	act.multiply(temp);
	System.out.println(act.toString());
	assertTrue(exp.equals(act));
	
	}

}
