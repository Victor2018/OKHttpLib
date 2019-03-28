
# OKHttpLib

# 一个okhttp & fastjson 封装的http网络请求框架及view注解,通过注解配置参数的网络请求框架

Usage

- Step 1. Add the JitPack repository to your build file

allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
- Step 2. Add the dependency

dependencies {
        compile 'com.github.Victor2018:OKHttpLib:latestVersion'
}

- Step 3.extends BasePresenterImpl<T> impl your presenterImpl

@HttpParms(method = Request.POST,bodyContentType = HttpRequest.mDefaultBodyContentType,
			responseCls = PhoneCodeReq.class)
	@Override
	public void sendRequest(String url, H header, T parm) {
		HttpInject.inject(this);
		super.sendRequest(url,header,parm);
	}

# 关注开发者：
- 邮箱： victor423099@gmail.com
- 新浪微博
- ![image](https://github.com/Victor2018/AppUpdateLib/raw/master/SrceenShot/sina_weibo.jpg)

## License

Copyright (c) 2017 Victor

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

