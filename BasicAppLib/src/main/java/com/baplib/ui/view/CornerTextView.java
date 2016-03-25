package com.baplib.ui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.TextView;

/**  
 * 版权：  极智网络技术
 * @author lanhm  
 * @version 创建时间：2013-7-2 下午12:56:12  
 * 类说明  
 */
public class CornerTextView extends TextView{

    
    public CornerTextView(Context context) {
        super(context);
    }
    
    public CornerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //倾斜度90,上下左右居中
        canvas.rotate(45, getMeasuredWidth()/3, getMeasuredHeight()/3);
        super.onDraw(canvas);
    }
	
}
