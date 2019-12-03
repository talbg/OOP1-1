package myMath;
import java.util.Iterator;

//this class written all by us
public class ComplexFunction implements complex_function{
	function head;
	function left;
	function right;
	Operation op;
	
	
	public ComplexFunction(function f){
		this.left =f.copy();
		this.right = new Polynom();
		this.op = Operation.None;
		
		} 
	
	public ComplexFunction(String op , function left , function right){
		left = this.left();
		right = this.right();
		this.op = getOpFromString(op);
		ComplexFunction cpx = new Complexfunction;
	} 
	//	Plus, Times, Divid, Max, Min, Comp , None, Error

	public Operation getOpFromString(String op) {
		op.toLowerCase();
		if(op.equals("plus"))
			return Operation.Plus;
		if(op.equals("times"))
			return Operation.Times;
		if(op.equals("divid"))
			return Operation.Divid;
		if(op.equals("max"))
			return Operation.Max;
		if(op.equals("min"))
			return Operation.Min;
		if(op.equals("comp"))
			return Operation.Comp;
		if(op.equals("none"))
			return Operation.None;
	return Operation.Error;
	}
	
	@Override
	public void plus(function f1) {
		
	}

	@Override
	public void mul(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void div(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void max(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void min(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void comp(function f1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// if/case for each operation-string
	public Operation getOp() {
		return this.op;
	}

	@Override
	public function initFromString(String s) {
		
		Polynom p = new Polynom(s);
		this.left = p.copy();
		this.right = new Polynom();
		this.op = op.None;
		
		return null;
	}
	
}
