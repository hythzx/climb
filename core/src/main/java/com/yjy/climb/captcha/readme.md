## 验证码模块

该模块为通用验证码生成和验证模块，目前设计支持短信验证码和图形验证码两种，验证码模块有下面的几部分组成：
1. `ICaptcha` : 验证码服务对外提供的接口，提供验证码生成和验证码校验功能
2. `ICaptchaInfo`: 生成的验证码信息
3. `ICaptchaParam`: 用于生成验证码的参数
4. `ICaptchaPersistence`: 验证码持久化接口，用于缓存验证码，默认使用Redis

验证码使用方式:

```java
import com.yjy.climb.captcha.ICaptcha;
import com.yjy.climb.captcha.ICaptchaInfo;
import com.yjy.climb.captcha.ICaptchaParam;

public class CaptchaTest {

	private final ICaptcha iCaptcha;

	public CaptchaTest(ICaptcha iCaptcha) {
		this.iCaptcha = iCaptcha;
	}

	public ICaptchaInfo create() {
        return iCaptcha.create();     // 使用默认参数
	}
}


```
