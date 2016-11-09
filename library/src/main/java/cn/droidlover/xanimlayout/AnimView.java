package cn.droidlover.xanimlayout;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/11/9 9:14
 * @editTime
 * @editor
 */
public class AnimView extends LinearLayout implements IAnimView {

    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    public AnimView(Context context) {
        super(context);
    }

    public AnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void apply(float ratio) {
        AnimLinearLayout.LayoutParams params = getLayoutParams();
        if (params == null) return;

        int width = getWidth();
        int height = getHeight();

        if (params.isAlpha()) {
            setAlpha(ratio);
        }
        if (params.isScaleX()) {
            setScaleX(ratio);
        }
        if (params.isScaleY()) {
            setScaleY(ratio);
        }
        if (params.isRotate()) {
            setRotation(ratio * 360);
        }
        if (params.isBgGradient()) {
            setBackgroundColor((Integer) argbEvaluator.evaluate(ratio, params.getStartColorBg(), params.getEndColorBg()));
        }
        if (params.isTranslate()) {

            if (params.verifyGravity(AnimLinearLayout.LayoutParams.TRANSLATE_GRAVITY_TOP)) {     //上->下
                setTranslationY(-height * (1 - ratio));
            }

            if (params.verifyGravity(AnimLinearLayout.LayoutParams.TRANSLATE_GRAVITY_BOTTOM)) {  //下->上
                setTranslationY(height * (1 - ratio));
            }

            if (params.verifyGravity(AnimLinearLayout.LayoutParams.TRANSLATE_GRAVITY_LEFT)) {    //左->右
                setTranslationX(-width * (1 - ratio));
            }

            if (params.verifyGravity(AnimLinearLayout.LayoutParams.TRANSLATE_GRAVITY_RIGHT)) {   //右->左
                setTranslationX(width * (1 - ratio));
            }

        }


    }

    @Override
    public void onReset() {
        apply(0);       //恢复到最初的状态
    }


    public AnimLinearLayout.LayoutParams getLayoutParams() {
        return (AnimLinearLayout.LayoutParams) super.getLayoutParams();
    }
}
