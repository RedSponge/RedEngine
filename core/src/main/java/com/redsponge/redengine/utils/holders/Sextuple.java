package com.redsponge.redengine.utils.holders;

public class Sextuple<A, B, C, D, E, F> {

	public final A a;
	public final B b;
	public final C c;
	public final D d;
	public final E e;
	public final F f;

	public Sextuple(A a, B b, C c, D d, E e, F f) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}


        @Override
        public String toString() {
            return "[" + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + "]";
        }
    
}