package com.ciandt.sample.detection.video.background.backgroundprocessors;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.BackgroundSubtractorMOG2;

import com.ciandt.sample.detection.video.background.utils.VideoProcessor;

public class CustomTransformationBackground implements VideoProcessor {

	private BackgroundSubtractorMOG2 mog = org.opencv.video.Video.createBackgroundSubtractorMOG2();
	private Mat foreground = new Mat();
	private double learningRate = 0.01;

	public Mat process(Mat inputImage) {

		mog.apply(inputImage, foreground, learningRate);

		Mat structuringElement = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
	//	Imgproc.morphologyEx(foreground, foreground, Imgproc.MORPH_OPEN, structuringElement);
	//	Imgproc.morphologyEx(foreground, foreground, Imgproc.MORPH_CLOSE, structuringElement);
		
		Imgproc.GaussianBlur(foreground, foreground, new Size(5,5),0 );
		Imgproc.threshold(foreground, foreground, 0, 255, Imgproc.THRESH_OTSU);
	//	Imgproc.adaptiveThreshold(foreground, foreground, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_OTSU, 15, 4);
		
		

		return foreground;
	}

	@Override
	public Mat process(Mat frame1, Mat frame2) {
		// TODO Auto-generated method stub
		return null;
	}

}
