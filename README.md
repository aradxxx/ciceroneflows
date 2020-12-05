[![](https://jitpack.io/v/aradxxx/ciceroneflows.svg)](https://jitpack.io/#aradxxx/ciceroneflows)
#### CiceroneFlows - library for simple subnavigation (e.g. instagram like tabs) with [Cicerone](https://github.com/terrakok/Cicerone).

#### Download
```
allprojects {
	repositories {
	    ...
	    maven { url 'https://jitpack.io' }
    }
}

dependencies {
    ...
    implementation 'com.github.aradxxx:ciceroneflows:$latestVersion'
}
```

#### About

<div style="text-align:center"><img src="/media/ui_scheme.png"/></div>

```FlowCicerone``` provides different instances of ```Cicerone``` wherever an independent navigation flow is necessary:
activity, flow fragments and flow fragment container.

```FlowRouter``` provides several additional methods for working with subnavigation:
1. Use standart methods for current flow navigation (for example ```router.navigateTo(screen)```).
2. Use overloaded methods for navigation on a specific flow (for example ```router.navigateTo("Tab1", screen)```).
3. Use overloaded methods with empty tag for navigation in activity stack (for example ```router.navigateTo("", screen)```).
4. Use switch command for switch current flow ```router.switch("Tab2")```.

Check out [sample app](app)
 
 #### License
 ```
 MIT License
 
 Copyright (c) 2019 aradxxx
 
 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:
 
 The above copyright notice and this permission notice shall be included in all
 copies or substantial portions of the Software.
 
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 SOFTWARE.
 ```
