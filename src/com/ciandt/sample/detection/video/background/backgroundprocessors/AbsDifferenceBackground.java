package com.ciandt.sample.detection.video.background.backgroundprocessors;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import com.ciandt.sample.detection.video.background.utils.VideoProcessor;

public class AbsDifferenceBackground implements VideoProcessor {

	private final static int SENSITIVITY_VALUE = 20;
	private final static int BLUR_SIZE = 15;

	public Mat process(Mat inputImage) {
		Mat foregroundImage = new Mat();
		return foregroundImage;
	}

	@Override
	public Mat process(Mat frame1, Mat frame2) {
		Mat grayImage1 = frame1.clone();
		Mat grayImage2 = frame2.clone();
		Mat differenceImage = new Mat();
		Mat thresholdImage = new Mat();
		
		Imgproc.cvtColor(frame1, grayImage1,Imgproc.COLOR_BGR2GRAY);
		Imgproc.cvtColor(frame2, grayImage2,Imgproc.COLOR_BGR2GRAY);
		
		Core.absdiff(grayImage1, grayImage2, differenceImage);
		
		
		Imgproc.threshold(differenceImage, thresholdImage, SENSITIVITY_VALUE, 255, Imgproc.THRESH_BINARY);
		
		Imgproc.blur(thresholdImage, thresholdImage, new Size(BLUR_SIZE,BLUR_SIZE));
		
		Imgproc.threshold(thresholdImage, thresholdImage, SENSITIVITY_VALUE, 255, Imgproc.THRESH_OTSU);
		
		return thresholdImage;
	}

}
