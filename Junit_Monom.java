package myMath;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class Junit_Monom {

//	@Test
//
//	void test_add() {
//		Monom m = new Monom("4x^2");
//		Monom output = new Monom("5x^2");
//		Monom adder = new Monom("-x^2");
//		output.add(adder);
//		assertTrue(m.equals(output));
//	}
	
	@Test
	void test_mul() {
		Monom m = new Monom("-5x^4");
	Monom output = new Monom("5x^2");
	Monom adder = new Monom("-x^2");
	output.multiply(adder);
	assertTrue(m.equals(output));
	}
	
}
