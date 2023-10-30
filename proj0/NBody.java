public class NBody {
    private static final String imageBackground = "images/starfield.jpg";

    /**
     * Read the radius of the universe in the given file
     *
     * @param fileName the file name which store the universe planet data
     * @return the radius of the universe in the given file
     */
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        in.readInt();
        return in.readDouble();
    }

    /**
     * Read the Planets of the given file
     *
     * @param fileName the file name which store the universe planet data
     * @return the planets array in the file
     */
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numberOfPlanet = in.readInt();
        var planets = new Planet[numberOfPlanet];
        in.readDouble();
        for (int i = 0; i < numberOfPlanet; ++i) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        var planets = readPlanets(filename);
        int lengthOfPlanets = planets.length;

        StdDraw.setScale(-radius, radius);

        StdDraw.enableDoubleBuffering();

        for (double time = 0; time < T; time += dt) {
            var xForces = new double[lengthOfPlanets];
            var yForces = new double[lengthOfPlanets];

            // Calculate the net x and y forces for each planet
            for (int i = 0; i < lengthOfPlanets; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // Update each planet's position, velocity, and acceleration
            for (int j = 0; j < lengthOfPlanets; ++j) {
                planets[j].update(dt, xForces[j], yForces[j]);
            }

            // Draw the backgorund image.
            StdDraw.picture(0, 75, imageBackground);

            // Draw all the planets
            for (Planet p : planets) {
                p.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        System.out.println(lengthOfPlanets);
        System.out.printf("%.2e\n", radius);
        for (Planet p : planets) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }

        //StdOut.printf("%d\n", planets.length);
        //StdOut.printf("%.2e\n", radius);
        //for (int i = 0; i < planets.length; i++) {
        //    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        //            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
        //            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        //}
    }
}
