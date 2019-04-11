package com.redsponge.redengine.utils.holders;

public class Single<A> {

	public final A a;

	public Single(A a) {
		this.a = a;
	}


        @Override
        public String toString() {
            return "[" + a + "]";
        }
    
}