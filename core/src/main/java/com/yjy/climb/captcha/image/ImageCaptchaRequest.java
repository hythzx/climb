package com.yjy.climb.captcha.image;


import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.captcha.ICaptchaRequest;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ImageCaptchaRequest implements ICaptchaRequest, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 图片宽度
	 */
	private int width = 200;

	/**
	 * 图片高度
	 */
	private int height = 100;

	/**
	 * 验证码字符数量
	 */
	private int codeCount = 4;
	/**
	 * 干扰元素个数 line/circle 共用
	 */
	private int circleCount = 20;

	/**
	 * 干扰线宽度
	 */
	int thickness = 4;

	public ImageCaptchaRequest() {
	}

	public ImageCaptchaRequest(int width, int height, int codeCount, int circleCount, int thickness) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.circleCount = circleCount;
		this.thickness = thickness;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getCodeCount() {
		return codeCount;
	}

	public void setCodeCount(int codeCount) {
		this.codeCount = codeCount;
	}

	public int getCircleCount() {
		return circleCount;
	}

	public void setCircleCount(int circleCount) {
		this.circleCount = circleCount;
	}

	public int getThickness() {
		return thickness;
	}

	public void setThickness(int thickness) {
		this.thickness = thickness;
	}
}
