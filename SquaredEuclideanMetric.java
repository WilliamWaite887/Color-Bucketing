package cs1501_p5;
public class SquaredEuclideanMetric implements DistanceMetric_Inter{
        /**
     * Computes the distance between the RGB values of two pixels. Different
     * implementations may use different formulas for calculating distance.
     *
     * @param p1 the first pixel
     * @param p2 the second pixel
     * @return The distance between the RGB values of p1 and p2
     */
    public double colorDistance(Pixel p1, Pixel p2){
        double dr = p1.getRed() - p2.getRed();
        double dg = p1.getGreen() - p2.getGreen();
        double db = p1.getBlue() - p2.getBlue();
        return dr * dr + dg * dg + db * db;
    }

}