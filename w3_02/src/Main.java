import static java.lang.Character.isUpperCase;

public class Main {

    public static abstract class Singer {
        static int counter = 1;

        int num;
        String name;
        public Singer(String name){
            this.name = name;
            this.num = counter++;
        }

        abstract String sing();

        public String toString(){
            return "(" + num + ") " + name + ": ";
        }

        static int biggestNumber = 0;
        static String loudestSinger;

        public static String loudest(Singer[] sng){
            int counter = 0;
            for(Singer s : sng){
                String song = s.sing();
                for(char c : song.toCharArray()){
                    if(isUpperCase(c))
                        counter++;
                }
                if(counter > biggestNumber) {
                    loudestSinger = s + s.sing();
                    biggestNumber = counter;
                }
                counter = 0;
            }
            return loudestSinger;
        }
    }

    public static void main(String[] args) {
        Singer s1 = new Singer("Martin"){
            @Override
            String sing() {
                return "Arrivederci, Roma...";
            }
        };

        Singer s2 = new Singer("Joplin"){
            @Override
            String sing() {
                return "...for me and my Bobby MacGee ";
            }
        };

        Singer s3 = new Singer("Houston"){
            @Override
            String sing() {
                return "I will always love youuuu ";
            }
        };
        Singer[] sng = {s1, s2, s3};
        for (Singer s : sng) System.out.println(s + s.sing());
        System.out.println(Singer.loudest(sng));
    }
}