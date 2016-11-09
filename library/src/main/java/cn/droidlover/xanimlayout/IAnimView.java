package cn.droidlover.xanimlayout;

/**
 * @author wanglei
 * @version 1.5.2
 * @description
 * @createTime 2016/11/9 9:32
 * @editTime
 * @editor
 */
public interface IAnimView {
    /**
     * 动画效果
     *
     * @param ratio 百分比 0-1之间
     */
    void apply(float ratio);

    /**
     * 动画重置
     */
    void onReset();
}
