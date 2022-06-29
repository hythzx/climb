package com.yjy.climb.captcha.hutool;


import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.captcha.CaptchaResponseBase;

public class ImageCaptchaResponse extends CaptchaResponseBase implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 图片base64编码
	 */
	private String base64Content;

	public ImageCaptchaResponse() {
		super();
	}

	public ImageCaptchaResponse(String key, String code, String base64Content) {
		super(code, key);
		this.base64Content = base64Content;
	}


	public String getBase64Content() {
		return base64Content;
	}

	public void setBase64Content(String base64Content) {
		this.base64Content = base64Content;
	}
}
