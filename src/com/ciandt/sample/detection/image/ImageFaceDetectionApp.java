package com.ciandt.sample.detection.image;


import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class ImageFaceDetectionApp {

   public static void main(String[] args) {

       System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
       System.out.println("\nRunning FaceDetector");

       CascadeClassifier faceDetector = new CascadeClassifier(ImageFaceDetectionApp.class.getResource("lbpcascade_frontalface.xml").getPath().substring(1));
       Mat image = Imgcodecs
               .imread(ImageFaceDetectionApp.class.getResource("img_001.png").getPath().substring(1));

       MatOfRect faceDetections = new MatOfRect();
       faceDetector.detectMultiScale(image, faceDetections);

       System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

       for (Rect rect : faceDetections.toArray()) {
    	   Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                   new Scalar(0, 255, 0));
       }

       String filename = "ouput.png";
       System.out.println(String.format("Writing %s", filename));
       Imgcodecs.imwrite(filename, image);
   }
}