# XAnimLayout 让引导页动画easy

[XAnimLayout](https://github.com/limedroid/XAnimLayout)封装了常见的引导页动画，仅仅需要在xml中设置相关属性，即可实现translate、rotate、alpha、scale、colorGradient等动画。

<p align="center">
  <img src="/art/xanimlayout.gif" alt="XAnimLayout" />
</p>

## 主要功能

* 实现常用的动画效果
* 自定义属性 
* 扩展性强

## 使用

* Gradle ： **compile 'cn.droidlover:XAnimLayout:1.0.0''**
* Github ： [XAnimLayout](https://github.com/limedroid/XAnimLayout)

## 说明
库中包括两部分：

* AnimScrollView ：继承ScrollView，用于滑动和滑动状态的监听。
* AnimLinearLayout ：AnimScrollView的直接子view

## 主要用法

```xml
<cn.droidlover.xanimlayout.AnimScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:scrollbars="none">

    <cn.droidlover.xanimlayout.AnimLinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical"
        app:al_firstFillViewPort="true">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:background="#BDF0B9"
            android:gravity="center"
            android:text="XAnimLayout"
            android:textColor="#fff"
            android:textSize="22sp"
            app:av_alpha="true" />


        <TextView
            android:layout_width="match_parent"
            android:layout_height="150dp"
            android:background="#FFE2C8"
            android:gravity="center"
            android:text="RxJava"
            android:textColor="#fff"
            android:textSize="22sp"
            app:av_translateGravity="right" />


        <TextView
            android:layout_width="match_parent"
            android:layout_height="200dp"
            android:gravity="center"
            android:text="Dagger"
            android:textSize="22sp"
            app:av_endColorBg="#140405"
            app:av_startColorBg="#A15772" />

        <TextView
            android:layout_width="match_parent"
            android:layout_height="300dp"
            android:background="#BAE4D0"
            android:gravity="center"
            android:text="Android"
            android:textSize="28sp"
            app:av_alpha="true" />

    </cn.droidlover.xanimlayout.AnimLinearLayout>

</cn.droidlover.xanimlayout.AnimScrollView>
```

> 不需要任何java代码，即可实现开场动画。

## 使用解析

### AnimLinearLayout 

AnimLinearLayout包含一个自定义属性：al_firstFillViewPort 。若true，则其第一个子view的高度就会占满全屏。若false，则其高度不被调整。此属性默认为true。

### 支持的属性

```xml
 <attr name="av_rotate" format="boolean" />
 <attr name="av_alpha" format="boolean" />
 <attr name="av_scaleX" format="boolean" />
 <attr name="av_scaleY" format="boolean" />
 <attr name="av_startColorBg" format="color" />
 <attr name="av_endColorBg" format="color"  />
 <attr name="av_translateGravity">
     <flag name="left" value="0x01" />
     <flag name="top" value="0x02" />
     <flag name="right" value="0x04" />
     <flag name="bottom" value="0x08" />
 </attr>
```

属性说明：

* av_rotate：boolean 表示是否可旋转
    * true 有旋转动画
    * false 没有旋转动画
* av_alpha：Boolean 表示是否有alpha动画
    * true 伴随alpha动画
    * false 没alpha动画
* av_scaleX 表示x方向的scale动画
* av_scaleY 表示y方向的scale动画
* av_startColorBg与av_endColorBg配对使用，作用于该view的background，分别表示开始颜色、结束颜色
* av_translateGravity 表示translate的开始方向，有left、top、right、bottom等值，默认为-1，需要说明的是，此属性与传统的gravity用法类似，可组合使用

```xml
app:av_translateGravity="right"
```
或者使用两个值
```xml
app:av_translateGravity="right|bottom"
```















