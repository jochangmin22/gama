package com.category.image;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.category.common.FileRenamePolicy;

public class WsImageTextUtil {

	private WsImageTextUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static void write(String imagePath, String savePath, String message) throws Exception {
		File loadImage = new File(imagePath);
		File makeImage = new FileRenamePolicy().rename(new File(savePath + File.separator + loadImage.getName()));

		BufferedImage bi = ImageIO.read(loadImage);
		Graphics2D g2 = bi.createGraphics();

		Font font = new Font("나눔고딕", Font.BOLD, 30);
		Map<TextAttribute, Object> attributes = new HashMap<>();
		attributes.put(TextAttribute.TRACKING, 0.15);
		Font font2 = font.deriveFont(attributes);

		g2.setColor(Color.black);
		g2.setFont(font2);
		g2.drawString(message, 140, 870);
		g2.dispose();

		ImageIO.write(bi, "jpeg", makeImage);
	}
}