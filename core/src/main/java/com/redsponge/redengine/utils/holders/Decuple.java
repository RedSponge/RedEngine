package com.redsponge.redengine.utils.holders;

public class Decuple<A, B, C, D, E, F, G, H, I, J> {

	public final A a;
	public final B b;
	public final C c;
	public final D d;
	public final E e;
	public final F f;
	public final G g;
	public final H h;
	public final I i;
	public final J j;

	public Decuple(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
		this.g = g;
		this.h = h;
		this.i = i;
		this.j = j;
	}


        @Override
        public String toString() {
            return "[" + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + "]";
        }
    
}