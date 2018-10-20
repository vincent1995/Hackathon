package huang.bling.hackathon.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by 沈东 on 2016/3/18.
 */
public class CustomEditText extends android.support.v7.widget.AppCompatEditText {
    private Drawable mLeft,mTop,mRight,mBottom;
    private Rect mBounds;

    public CustomEditText(Context context) {
        super(context);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }



    private void init() {
        setDrawable();
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
    }

    //左侧图标显示，右侧图标进行控制
    private void setDrawable() {
        //如果未输入文字或未获取焦点时，只显示左侧图标
        if(this.length()==0 ||!this.isFocused()){
            setCompoundDrawables(mLeft,mTop,null,mBottom);
        }
        //否则，显示左侧和右侧图标
        else {
            setCompoundDrawables(mLeft,mTop,mRight,mBottom);
        }
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if(mLeft == null){
            this.mLeft = left;
        }
        if(mTop == null){
            this.mTop = top;
        }
        if(mRight == null){
            this.mRight = right;
        }
        if(mBottom == null){
            this.mBottom = bottom;
        }
        super.setCompoundDrawables(left, top, right, bottom);
    }

    //http://www.cnblogs.com/leaven/p/3462455.html
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //ACTION_UP:表示用户抬起了手指
        if(mRight !=null && event.getAction() == MotionEvent.ACTION_UP){
            this.mBounds = mRight.getBounds();
            mRight.getIntrinsicWidth();
            //getX() 获得事件发生时,触摸的中间区域在屏幕的X轴.
            int eventX = (int) event.getX();
            int width = mBounds.width();
            int right = getRight();
            int left = getLeft();
            if(eventX > (right - width *2 - left)){
                setText("");
                //ACTION_CANCEL:表示手势被取消了
                event.setAction(MotionEvent.ACTION_CANCEL);
            }
        }

        return super.onTouchEvent(event);
    }

    //退出，重置
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.mBottom=null;
        this.mLeft=null;
        this.mTop=null;
        this.mRight=null;
        this.mBounds=null;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDrawable();
    }
}
