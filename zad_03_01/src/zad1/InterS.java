package zad1;

interface TwoStringsOper {

	public String apply(String a, String b);
}


public class InterS{

	static class Concat implements TwoStringsOper{
		@Override
		public String apply(String a, String b) {
			return a.concat(b);
		}
	}

	static class ConcatRev implements TwoStringsOper{
		@Override
		public String apply(String a, String b){
			return b.concat(a);
		}
	}

	static class Initials implements TwoStringsOper{
		@Override
		public String apply(String a, String b){
			return String.valueOf(a.charAt(0)) + b.charAt(0);
		}
	}

	static class Separ implements TwoStringsOper{

		private String sep;

		public Separ(String sep){
			this.sep = sep;
		}
		@Override
		public String apply(String a, String b){
			return a + sep + b;
		}
	}

	public static void main(String[] args) {

		TwoStringsOper[] a = {
				(str1, str2) -> str1.concat(str2),          // Concat
				(str1, str2) -> str2.concat(str1),          // ConcatRev
				(str1, str2) -> String.valueOf(str1.charAt(0)) + str2.charAt(0), // Initials
				(str1, str2) -> str1 + " loves " + str2     // Separ (" loves ")
				//new Concat(), new ConcatRev(), new Initials(), new Separ(" loves ")
		};
		for (TwoStringsOper op : a) {
			System.out.println(op.apply("Mary", "John"));
		}
	}
}