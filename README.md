# StreamingX
How to use:

Step 1:
Add it in your root build.gradle at the end of repositories:
Android SDK33 or above：
	allprojects {
		repositories {
			...
   			allowInsecureProtocol = true
			maven { url 'https://jitpack.io' }
		}
	}
Lower than Android SDK33 version
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}


Step 2:
Add the dependency:
	dependencies {
	        implementation 'com.github.akapwsd:streamingx:Tag'
	}
  
[![](https://jitpack.io/v/akapwsd/streamingx.svg)](https://jitpack.io/#akapwsd/streamingx)
