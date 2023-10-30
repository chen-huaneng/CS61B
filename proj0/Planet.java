public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**
     * Copy the p object deeply into a new object
     */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /**
     * Initialize the object
     *
     * @param xP  Its current x position
     * @param yP  Its current y position
     * @param xV  Its current velocity in the x direction
     * @param yV  Its current velocity in the y direction
     * @param m   Its mass
     * @param img The name of the file that corresponds to the image that depicts the body
     */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /**
     * Calculates the distance between two Planets.
     *
     * @param p Another Planet
     * @return the distance between two Planet
     */
    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
    }

    /**
     * Calculate the force exerted on this planet by the given planet
     *
     * @param p another planet
     * @return the force exerted by another planet
     */
    public double calcForceExertedBy(Planet p) {
        double distance = calcDistance(p);
        return (G * p.mass * mass) / (distance * distance);
    }

    /**
     * Calculate the force exerted on this planet by the given planet in x direction
     *
     * @param p another planet
     * @return the force exerted by another planet in x direction
     */
    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - xxPos;
        return calcForceExertedBy(p) * dx / calcDistance(p);
    }

    /**
     * Calculate the force exerted on this planet by the given planet in y direction
     *
     * @param p another planet
     * @return the force exerted by another planet in y direction
     */
    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - yyPos;
        return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    /**
     * Calculate the net X force exerted by all Planets in that array upon the current Planet except itself
     *
     * @param allPlanets all the Planet that exerted on the current one
     * @return the net force exerted by all other Planets in X direction
     */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceExertedByX = 0;
        for (Planet p : allPlanets) {
            if (!equals(p)) {
                netForceExertedByX += calcForceExertedByX(p);
            }
        }
        return netForceExertedByX;
    }

    /**
     * Calculate the net Y force exerted by all Planets in that array upon the current Planet except itself
     *
     * @param allPlanets all the Planet that exerted on the current one
     * @return the net force exerted by all other Planets in Y direction
     */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceExertedByY = 0;
        for (Planet p : allPlanets) {
            if (!equals(p)) {
                netForceExertedByY += calcForceExertedByY(p);
            }
        }
        return netForceExertedByY;
    }

    /**
     * In the period of time dt, update the current Planet change position under the net force of y direction and x direction
     *
     * @param dt a small period of time
     * @param fX net force in x direction
     * @param fY net force in y direction
     */
    public void update(double dt, double fX, double fY) {
        double aNetX = fX / mass;
        double aNetY = fY / mass;
        xxVel = xxVel + dt * aNetX;
        yyVel = yyVel + dt * aNetY;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    /**
     * Draw the planet image at the position that Planet provide
     */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
        // StdDraw.show();
    }
}
