public class InterS{

    class Concat implements TwoStringsOper{
        @Override
        public String apply(String a, String b) {
            return a.concat(b);
        }
    }

    class ConcatRev implements TwoStringsOper{
        @Override
        public String apply(String a, String b){
            return b.concat(a);
        }
    }

    class Initials implements TwoStringsOper{
        @Override
        public String apply(String a, String b){
            char initA = a.charAt(0);
            char initB = b.charAt(0);
            return a + b;
        }
    }

    class Separ implements TwoStringsOper{

        private String sep;

        public Separ(String sep){
            this.sep = sep;
        }
        @Override
        public String apply(String a, String b){
            return a + sep + b;
        }
    }

    public void main(String[] args) {

        TwoStringsOper[] a = {
                new Concat(), new ConcatRev(),
                new Initials(), new Separ(" loves ")
        };
        for (TwoStringsOper op : a) {
            System.out.println(op.apply("Mary", "John"));
        }
    }
}