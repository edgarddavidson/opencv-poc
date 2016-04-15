package com.ciandt.sample.imageviewer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/*
 * 
 */
public class ImageViewerApp {

	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}

	public static void main(String[] args) throws Exception {
		String filePath = "C:/dev/OpenCV/imgs/img_0016.png";
		Mat newImage = Imgcodecs.imread(filePath);

		if (newImage.dataAddr() == 0) {
			System.out.println("Couldn't open file " + filePath);
		} else {
			
			newImage = filter(newImage);
			
			ImageViewer imageViewer = new ImageViewer();
			imageViewer.show(newImage, "Loaded image");
		}
	}

	public static Mat filter(Mat image) {
		int totalBytes = (int) (image.total() * image.elemSize());
		byte buffer[] = new byte[totalBytes];
		image.get(0, 0, buffer);
		for (int i = 0; i < totalBytes; i++) {
			if (i % 3 == 0)
				buffer[i] = 0;
		}
		image.put(0, 0, buffer);
		return image;
	}
}
