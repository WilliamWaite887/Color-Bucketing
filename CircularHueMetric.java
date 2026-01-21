package cs1501_p5;

public class CircularHueMetric implements DistanceMetric_Inter{
        /**
     * Computes the distance between the RGB values of two pixels. Different
     * implementations may use different formulas for calculating distance.
     *
     * @param p1 the first pixel
     * @param p2 the second pixel
     * @return The distance between the RGB values of p1 and p2
     */
    public double colorDistance(Pixel p1, Pixel p2){
        int h1 = p1.getHue();
        int h2 = p2.getHue();
        int diff = Math.abs(h1 - h2);
        if (diff > 180) {
            diff = 360 - diff;
        }
        return diff;
    }
}