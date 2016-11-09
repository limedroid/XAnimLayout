package cn.droidlover.xanimlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/11/9 9:14
 * @editTime
 * @editor
 */
public class AnimLinearLayout extends LinearLayout {

    boolean firstFillViewPort = true;

    public AnimLinearLayout(Context context) {
        this(context, null);
    }

    public AnimLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimLinearLayout);
        firstFillViewPort = typedArray.getBoolean(R.styleable.AnimLinearLayout_al_firstFillViewPort, true);
        typedArray.recycle();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (getChildCount() > 0
                && firstFillViewPort) {
            View parent = (View) getParent();
            if (parent != null) {
                getChildAt(0).getLayoutParams().height = parent.getHeight();
            }
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!isNeedWrap(params)) {
            super.addView(child, index, params);
            return;
        }

        AnimView animView = new AnimView(getContext());
        animView.addView(child, params);

        super.addView(animView, index, params);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new AnimLinearLayout.LayoutParams(getContext(), attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof AnimLinearLayout.LayoutParams;
    }

    private boolean isNeedWrap(ViewGroup.LayoutParams params) {
        if (params != null && params instanceof AnimLinearLayout.LayoutParams) {

            if (((AnimLinearLayout.LayoutParams) params).isScaleX()
                    || ((AnimLinearLayout.LayoutParams) params).isScaleY()
                    || ((AnimLinearLayout.LayoutParams) params).isRotate()
                    || ((AnimLinearLayout.LayoutParams) params).isAlpha()
                    || ((AnimLinearLayout.LayoutParams) params).isBgGradient()
                    || ((AnimLinearLayout.LayoutParams) params).isTranslate())
                return true;
        }
        return false;
    }


    public void dispatchScroll(int l, int t, int oldl, int oldt) {

        int childCount = getChildCount();
        if (childCount > 0) {

            for (int pos = 0; pos < childCount; pos++) {
                View child = getChildAt(pos);

                if (child instanceof IAnimView) {
                    int childTop = child.getTop();
                    int childHeight = child.getHeight();

                    int deltaTop = childTop - t;
                    if (deltaTop <= childHeight) {
                        int visibleGap = childHeight - deltaTop;
                        float ratio = clamp(visibleGap / (float) childHeight, 1, 0);
                        ((IAnimView) child).apply(ratio);
                    } else {
                        ((IAnimView) child).onReset();
                    }

                }
            }
        }
    }

    private static float clamp(float value, float max, float min) {
        return Math.max(Math.min(value, max), min);
    }


    public static class LayoutParams extends LinearLayout.LayoutParams {

        private boolean isRotate = false;
        private boolean isAlpha = false;
        private boolean isScaleX = false;
        private boolean isScaleY = false;

        private int startColorBg = -1;
        private int endColorBg = -1;

        private int translateGravity = -1;

        public static final int TRANSLATE_GRAVITY_LEFT = 0x01;
        public static final int TRANSLATE_GRAVITY_TOP = 0x02;
        public static final int TRANSLATE_GRAVITY_RIGHT = 0x04;
        public static final int TRANSLATE_GRAVITY_BOTTOM = 0x08;


        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);

            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimView_Layout);

            isRotate = typedArray.getBoolean(R.styleable.AnimView_Layout_av_rotate, false);
            isAlpha = typedArray.getBoolean(R.styleable.AnimView_Layout_av_alpha, false);
            isScaleX = typedArray.getBoolean(R.styleable.AnimView_Layout_av_scaleX, false);
            isScaleY = typedArray.getBoolean(R.styleable.AnimView_Layout_av_scaleY, false);

            startColorBg = typedArray.getColor(R.styleable.AnimView_Layout_av_startColorBg, -1);
            endColorBg = typedArray.getColor(R.styleable.AnimView_Layout_av_endColorBg, -1);

            translateGravity = typedArray.getInt(R.styleable.AnimView_Layout_av_translateGravity, -1);

            typedArray.recycle();
        }

        public boolean isRotate() {
            return isRotate;
        }

        public boolean isAlpha() {
            return isAlpha;
        }

        public boolean isScaleX() {
            return isScaleX;
        }

        public boolean isScaleY() {
            return isScaleY;
        }

        public boolean isBgGradient() {
            return startColorBg != -1 && endColorBg != -1;
        }

        public boolean isTranslate() {
            return translateGravity != -1;
        }


        public int getStartColorBg() {
            return startColorBg;
        }

        public int getEndColorBg() {
            return endColorBg;
        }

        public int getTranslateGravity() {
            return translateGravity;
        }

        public boolean verifyGravity(int sGravity) {
            return (translateGravity & sGravity) == sGravity;
        }
    }

}
