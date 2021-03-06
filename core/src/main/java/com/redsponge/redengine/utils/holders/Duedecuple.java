package com.redsponge.redengine.utils.holders;

public class Duedecuple<A, B, C, D, E, F, G, H, I, J, K, L> {

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
	public final K k;
	public final L l;

	public Duedecuple(A a, B b, C c, D d, E e, F f, G g, H h, I i, J j, K k, L l) {
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
		this.k = k;
		this.l = l;
	}


        @Override
        public String toString() {
            return "[" + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ", " + g + ", " + h + ", " + i + ", " + j + ", " + k + ", " + l + "]";
        }
    
}