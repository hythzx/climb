package com.yjy.climb.captcha;


public class ImageCaptchaInfo extends CaptchaInfoBase{

	/**
	 * 图片base64编码
	 */
	private String base64Content;

	public ImageCaptchaInfo() {
		super();
	}

	public ImageCaptchaInfo(String key, String code, String base64Content) {
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
