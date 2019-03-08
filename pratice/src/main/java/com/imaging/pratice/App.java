package com.imaging.pratice;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.dcm4che3.imageio.plugins.dcm.DicomImageReadParam;

/**
 * Hello world!
 * @author Srinivas Nangana
 *
 *Read Read me document
 */
public class App {
	static BufferedImage myJpegImage = null;

	public static void main(String[] args) throws FileNotFoundException, IOException {
		File file = new File("D:/Srinivas/Dycom/input/image-000001.dcm");//Your Sourse
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("DICOM");
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		while (iterator.hasNext()) {
			ImageReader imageReader = (ImageReader) iterator.next();
			DicomImageReadParam dicomImageReadParam = (DicomImageReadParam) imageReader.getDefaultReadParam();
			try {
				ImageInputStream iis = ImageIO.createImageInputStream(file);
				imageReader.setInput(iis, false);
				myJpegImage = imageReader.read(0, dicomImageReadParam);
				iis.close();
				if (myJpegImage == null) {
					System.out.println("Could not read image!!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			File file2 = new File("D:/Srinivas/Dycom/output/test15.jpg");//destination
			try {
				
				ImageIO.write(myJpegImage, "jpg", outputStream);

				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Completed");

			try (OutputStream outputStream1 = new FileOutputStream(file2)) {
				outputStream.writeTo(outputStream1);
			}
		}

	}
}
