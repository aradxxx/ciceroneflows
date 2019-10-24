[![](https://jitpack.io/v/aradxxx/ciceronetabs.svg)](https://jitpack.io/#aradxxx/ciceronetabs)
#### CiceroneTabs - небольшая библиотека упрощаяющая реализацию Instagram like навигации с использованием [Cicerone](https://github.com/terrakok/Cicerone)

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
    implementation 'com.github.aradxxx:ciceronetabs:0.1'
}
```

#### About

<div style="text-align:center"><img src="/media/ui_scheme.png"/></div>

```TabCicerone``` предоставляет доступ к различным экземплярам ```Cicerone``` везде где необходим независимый стек фрагментов:
активити, таб фрагменты и фрагмент - контейнер для таб фрагментов.

```TabRouter``` предоставляет группу дополнительных методов для работы с таб навигацией:
 1. Используйте стандартные методы для выполнения навигации в текущем стеке (например ```router.navigateTo(Screen screen)```).
 2. Для выполнения навигации в стеке таба используйте перегруженные стандартные методы с указанием тега таба (например ```router.navigateTo("Tab1", Screen screen)```).
 3. Для выполнения навигации в стеке активити используйте перегруженные стандартные методы с указанием пустого тега (например ```router.navigateTo("", Screen screen)```).
 4. Инициализация и/или переключение таба выполняется командой ```router.switchTab(Screen screen)```.
 
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