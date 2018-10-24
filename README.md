Image-Zoomer
==========
--------
### Specs
[![Bintray](https://img.shields.io/badge/Bintray-v1.0.2-brightgreen.svg?)](https://bintray.com/code-crusher/maven/ImageZoomer)
<a href="http://www.methodscount.com/?lib=com.vatsal.imagezoomer%3Aimage-zoomer%3A1.0.2"><img src="https://img.shields.io/badge/Methods%20and%20size-core:%20103%20%7C%2026%20KB-e91e63.svg"/></a>
<a href="http://twitter.com/vatsal__bajpai"><img src="https://img.shields.io/badge/Twitter-@vatsal__bajpai-blue.svg?" alt="Twitter" data-canonical-src="https://img.shields.io/badge/Twitter-@vatsal__bajpai-blue.svg?" style="max-width:100%;"></a><br>

Image zoomer provides easy way to add zoom animations to you ImageButton. You can set the animation duration and reverse mode to reverse the animation.

### Featured in
<a href="http://android-arsenal.com/details/1/3799"><img src="https://img.shields.io/badge/Android%20Arsenal-ImageZoomer-green.svg"/></a>

# Screenshots
###Zoom
![Screenshot](https://github.com/code-crusher/Image-Zoomer/blob/master/screenshots/zoom.gif)

### Zoom Reverse
![Screenshot](https://github.com/code-crusher/Image-Zoomer/blob/master/screenshots/zoomReverse.gif)

# Integration
-------------

 - ImageZoomer is available in the MavenCentral, so getting it as simple as adding it as a dependency

```gradle
compile 'com.vatsal.imagezoomer:image-zoomer:1.0.2'
```

# Usage
### Method 1 : XML
In your activity_main.xml file use this code instead of ImageButton:

```xml  
<com.vatsal.imagezoomer.ImageZoomButton
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:src="@mipmap/ic_launcher" />
```
### Attributes

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

### Method 2 : Java
In your MainActivity.class use this code in OnClick() of ImageButton:

```Java
Activity activity = getActivity();
ImageButton imageButton = (ImageButton) findViewById(R.id.image_button);
long duration = 500;
```
 - For simple zoom:
```Java
imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZoomAnimation zoomAnimation = new ZoomAnimation(activity);
                zoomAnimation.zoom(v, duration);
            }
        });
```

 - For Reverse zoom:

```Java
imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZoomAnimation zoomAnimation = new ZoomAnimation(activity);
                zoomAnimation.zoomReverse(v, duration);
            }
        });
```

Variable     | Type
-------- | ---
image_button | ImageButton
activity    | Activity
duration(ms)     | long

# License

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
