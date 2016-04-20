package com.ciandt.sample.detection.video.connected;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ConnectedApp 
{
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	public static void main(String[] args) throws Exception {
		String filePath = "SuperTucano.jpg";
		Mat newImage = Imgcodecs.imread(filePath);

		if(newImage.dataAddr()==0){
			System.out.println("Couldn't open file " + filePath);
		}else{

			GUI gui = new GUI("Connected Components", newImage);
			gui.init();
		}
		return;
	}
}