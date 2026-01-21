package cs1501_p5;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Map;

public class ColorQuantizer implements ColorQuantizer_Inter {
    private Pixel[][] pixelArray;
    private ColorMapGenerator_Inter generator;

    public ColorQuantizer(Pixel[][] pixelArray, ColorMapGenerator_Inter gen) {
        this.pixelArray = pixelArray;
        this.generator = gen;
    }

    public ColorQuantizer(String bmpFilename, ColorMapGenerator_Inter gen) {
        this.generator = gen;
        loadPixelArray(bmpFilename);
    }

    /**
     * Performs color quantization using the color map generator specified when
     * this quantizer was constructed.
     *
     * @param numColors number of colors to use for color quantization
     * @return A two dimensional array where each index represents the pixel
     * from the original bitmap image and contains a Pixel representing its
     * color after quantization
     */
    public Pixel[][] quantizeTo2DArray(int numColors){
        Pixel[] initialPalette = generator.generateColorPalette(pixelArray, numColors);
        Map<Pixel, Pixel> colorMap = generator.generateColorMap(pixelArray, initialPalette);
        Pixel[][] output = new Pixel[pixelArray.length][pixelArray[0].length];


        for (int x = 0; x < pixelArray.length; x++) {
            for (int y = 0; y < pixelArray[x].length; y++) {
                Pixel orig = pixelArray[x][y];
                output[x][y] = colorMap.get(orig);
            }
        }
        return output;
    }

    /**
     * Performs color quantization using the color map generator specified when
     * this quantizer was constructed. Rather than returning the pixel array,
     * this method writes the resulting image in bmp format to the specified
     * file.
     *
     * @param numColors number of colors to use for color quantization
     * @param fileName File to write resulting image to
     */
    public void quantizeToBMP(String fileName, int numColors){
        Pixel[][] reduced = quantizeTo2DArray(numColors);
        Util.savePixelMatrixToBitmap(fileName, reduced);
    }

    // Reads the given BMP file into pixelArray using Util.
    private void loadPixelArray(String bmpFilename) {
        try {
            BufferedImage image = ImageIO.read(new File(bmpFilename));
            this.pixelArray = Util.convertBitmapToPixelMatrix(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}