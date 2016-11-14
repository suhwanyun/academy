package academy.group5.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

public class ImageControl {
	// 이미지 최대 픽셀크기
	private static final int MAX_IMG_SIZE = 1280;
	/** 이미지 저장 및 리사이징 */
	public void imageResize(String orgFilePath, String targetFilePath, String imageType) throws IOException{

		BufferedImage originalImage = ImageIO.read(new File(orgFilePath));
			
		int imgWidth = originalImage.getWidth();
		int imgHeight = originalImage.getHeight();
		
		double sizeRate;
		
		if(imgWidth > imgHeight && imgWidth > MAX_IMG_SIZE){
			sizeRate = imgWidth / imgHeight;
			
			imgWidth = MAX_IMG_SIZE;
			imgHeight = (int) (MAX_IMG_SIZE / sizeRate);
		} else if(imgWidth < imgHeight && imgHeight > MAX_IMG_SIZE){
			sizeRate = imgHeight / imgWidth;
			
			imgHeight = MAX_IMG_SIZE;
			imgWidth = (int) (MAX_IMG_SIZE / sizeRate);
		}

		// 이미지 크기 줄이기
		BufferedImage resizedImage = Scalr.resize(originalImage, imgWidth, imgHeight);
		
		// 이미지 저장
		ImageIO.write(resizedImage, imageType, new File(targetFilePath));
	}
	
	// 축소할 이미지의 픽셀크기
	private static final int TMP_IMG_SIZE = 150;	
	/** 미리보기 이미지 리사이징 및 저장 */
	public void previewImageResize(String orgFilePath, String targetFilePath, String imageType) throws IOException{

		BufferedImage originalImage = ImageIO.read(new File(orgFilePath));
			
		int imgWidth = originalImage.getWidth();
		int imgHeight = originalImage.getHeight();
		int minLength = Math.min(imgWidth, imgHeight);
		
		// 이미지 정사각형으로 자르기
		BufferedImage scaledImage = Scalr.crop(originalImage,
												(imgWidth - minLength)/2,
												(imgHeight - minLength)/2,
												minLength, minLength);		
		// 이미지 크기 줄이기
		BufferedImage resizedImage = Scalr.resize(scaledImage, TMP_IMG_SIZE, TMP_IMG_SIZE);
		// 이미지 저장
		ImageIO.write(resizedImage, imageType, new File(targetFilePath));
	}
}
