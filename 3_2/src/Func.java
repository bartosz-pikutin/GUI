interface Func {
	double apply(double x);
	static Func compose(Func f, Func g) {
		return x -> f.apply(g.apply(x));
	}
}