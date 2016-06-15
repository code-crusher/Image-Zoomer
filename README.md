Image-Zoomer
==========
----------
<img src="https://img.shields.io/badge/release-v1.0.0-brightgreen.svg">
<a href="http://twitter.com/vatsal__bajpai"><img src="https://img.shields.io/badge/Twitter-@vatsal__bajpai-blue.svg?style=falt" alt="Twitter" data-canonical-src="https://img.shields.io/badge/Twitter-@vatsal__bajpai-blue.svg?style=falt" style="max-width:100%;"></a><br>

Image zoomer provides easy way to add zoom animations to you ImageButton. You can set the animation duration and reverse mode to reverse the animation.

#Integration
-------------

 - ImageZoomer is available in the MavenCentral, so getting it as simple as adding it as a dependency

```gradle
compile 'com.vatsal.imagezoomer:image-zoomer:1.0.0'
```

#Usage
###Method 1 : XML
In your activity_main.xml file use this code instead of ImageButton:

```xml  
<com.vatsal.imagezoomer.ImageZoomButton
    android:id="@+id/image_buttom"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/ic_launcher" />
```
###Attributes

<ul>• To set the duration use:
</ul>
```xml
    app:duration="200"
```
<ul>• To use the reverse mode:
</ul>
```xml
    app:modeReverse="true"
```

###Method 2 : Java
In your MainActivity.class use this code in OnClick() of ImageButton:

 - For simple zoom:
```Java
ZoomAnimation.zoom(image_button, activity, duration);
```

 - For Reverse zoom:

```Java
 ZoomAnimation.zoomReverse(image_button, activity, duration);
```

Variable     | Type
-------- | ---
image_button | ImageButton
activity    | Activity
duration     | long

#License

> Copyright 2016 Vatsal Bajpai

>Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

>   http://www.apache.org/licenses/LICENSE-2.0

>Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
>WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
>See the License for the specific language governing permissions and
>limitations under the License.
