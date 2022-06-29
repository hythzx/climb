## 验证码模块

该模块为通用验证码生成和验证模块，目前设计支持短信验证码和图形验证码两种，验证码模块有下面的几部分组成：
1. `ICaptcha` : 验证码服务对外提供的接口，提供验证码生成和验证码校验功能
2. `ICaptchaResponse`: 生成的验证码信息
3. `ICaptchaRequest`: 用于生成验证码的参数
4. `ICaptchaPersistence`: 验证码持久化接口，用于缓存验证码，默认使用Redis
5. `hutool`: 使用hutool工具生成图形验证码
6. `sms`: 使用短信方式发送验证码
