🎨 Java Color Quantization & Palette Generator

This project is a Java image-processing system that reduces full-color images into compact color palettes using clustering and bucketing algorithms with pluggable color-distance metrics.
It models how professional tools (GIF encoders, image compressors, pixel-art generators) transform high-resolution images into palette-based formats while preserving visual fidelity.

🚀 What This Program Does

Given an input image and a target number of colors, the system:

Reads all pixels from the image

Builds a color palette using either:

Bucketing (histogram-based grouping), or

Clustering (k-means-style centroid selection)

Assigns each pixel to the closest palette color

Outputs a new image using only the reduced palette

The entire pipeline is driven by App.java.


The system is designed around strategy interfaces, allowing algorithms to be swapped without changing core logic.

| File                           | Description                                                                                 |
| ------------------------------ | ------------------------------------------------------------------------------------------- |
| `App.java`                     | Program entry point — loads images, selects algorithms, runs quantization, and saves output |
| `Pixel.java`                   | Represents a pixel with RGB and HSV values                                                  |
| `Util.java`                    | Utility functions for image I/O and color conversions                                       |
| `ColorQuantizer.java`          | Core engine that maps pixels to palette colors                                              |
| `ColorQuantizer_Inter.java`    | Quantizer interface                                                                         |
| `ColorMapGenerator_Inter.java` | Interface for palette generators                                                            |
| `BucketingMapGenerator.java`   | Histogram-based palette generator                                                           |
| `ClusteringMapGenerator.java`  | K-means-style palette generator                                                             |
| `DistanceMetric_Inter.java`    | Interface for color distance                                                                |
| `SquaredEuclideanMetric.java`  | RGB color distance                                                                          |
| `CircularHueMetric.java`       | Hue-aware HSV color distance                                                                |

🎯 Supported Algorithms
Palette Generation

Bucketing

Groups pixels into fixed color ranges

Fast and deterministic

Clustering

Iteratively finds representative color centroids

Produces higher-quality palettes for complex images

Color Distance Metrics

Squared Euclidean (RGB)

Standard distance in RGB space

Circular Hue (HSV)

Treats hue as circular (red ≈ red)

Improves perceptual accuracy for color-rich images

These can be freely combined:

Generator	Metric
Bucketing	RGB
Bucketing	HSV Hue
Clustering	RGB
Clustering	HSV Hue
🧪 How the System Works

All pixels are extracted from the input image

A color palette is generated using the selected algorithm

Each pixel is mapped to the closest palette color using the chosen distance metric

The output image is reconstructed using only the palette colors

This preserves visual similarity while drastically reducing the number of colors.

🧩 Why Circular Hue Matters

Standard RGB distance treats red and blue as far apart even though they are neighbors on the color wheel.
The CircularHueMetric wraps hue values, allowing the system to correctly recognize that red ≈ red, improving image quality in saturated and gradient-heavy images.

🏗 Software Engineering Design

This project follows real-world architecture principles:

Strategy pattern (distance metrics & palette generators)

Interface-based decoupling

Algorithm isolation

Easy extensibility for new color models or quantization methods

New algorithms can be added without modifying existing code.

🏁 Summary

This is a full end-to-end image color quantization pipeline written in Java, combining clustering algorithms, histogram techniques, and perceptually aware distance metrics into a clean, extensible system suitable for graphics, compression, and visualization tasks.
