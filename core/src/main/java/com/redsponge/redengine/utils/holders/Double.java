package com.redsponge.redengine.utils.holders;

public class Double<A, B> {

	public final A a;
	public final B b;

	public Double(A a, B b) {
		this.a = a;
		this.b = b;
	}


        @Override
        public String toString() {
            return "[" + a + ", " + b + "]";
        }
    
}