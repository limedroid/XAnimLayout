package cn.droidlover.xanimlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/11/9 9:14
 * @editTime
 * @editor
 */
public class AnimScrollView extends ScrollView {

    AnimLinearLayout contentLayout;

    public AnimScrollView(Context context) {
        super(context);
    }

    public AnimScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (contentLayout != null) {
            contentLayout.dispatchScroll(l, t, oldl, oldt);
        }

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            if (getChildAt(0) instanceof AnimLinearLayout) {
                contentLayout = (AnimLinearLayout) getChildAt(0);
            }
        }
    }


}
