package com.redsponge.redengine.utils.holders;

public class Octuple<A, B, C, D, E, F, G, H> {

	public final A a;
	public final B b;
	public final C c;
	public final D d;
	public final E e;
	public final F f;
	public final G g;
	public final H h;

	public Octuple(A a, B b, C c, D d, E e, F f, G g, H h) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
	}


        @Override
        public String toString() {
            return "[" + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + "]";
        }
    
}