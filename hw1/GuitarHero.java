
/** @source: GuitarHeroLite.java */
public class GuitarHero {

    //private static final double CONCERT_A = 440.0;
    //private static final double CONCERT_C = CONCERT_A * Math.pow(2, 3.0 / 12.0);
    private static final String KEYBOARD= "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */
        synthesizer.GuitarString[] stringList = new synthesizer.GuitarString[KEYBOARD.length()];
        int i = 0;
        for (char c : KEYBOARD.toCharArray()) {
            int index = KEYBOARD.indexOf(c);
            double concert = 440.0 * Math.pow(2, (index - 24) / 12.0);
            stringList[i++] = new synthesizer.GuitarString(concert);
        }
        //synthesizer.GuitarString stringC = new synthesizer.GuitarString(CONCERT_C);

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = KEYBOARD.indexOf(key);
                if (index != -1) {
                    stringList[index].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (var key : stringList) {
                sample += key.sample();
            }
            //double sample = stringA.sample() + stringC.sample();

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (var key : stringList) {
                key.tic();
            }
            //stringA.tic();
            //stringC.tic();
        }
    }
}
