package com.baplib.ui.view;

import java.util.TimerTask;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/*
 * 子控件横向布局，默认从右向左排序
 */
public class HoriCardStack extends RelativeLayout implements View.OnTouchListener {

	private static final int MAX_INTERVAL_FOR_CLICK = 250; // time to find
															// single click
	private static final int MAX_DISTANCE_FOR_CLICK = 20; // move scale to find
															// single click
	private int CARDLABLEWIDTH = 58;// dp,使用时将实时计算真实宽度
	private Context _context;
	private int _currentTop = 0;// 当前主要显示哪一个控件

	public HoriCardStack(Context context) {
		super(context);
		_context = context;
	}

	public HoriCardStack(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
		_context = context;
	}

	/*
	 * set the card space
	 */
	public void setCardSpace(int space) {
		CARDLABLEWIDTH = space;
	}

	private int getVisibleChildCount() {
		int num = 0;
		for (int i = 0; i < getChildCount(); i++) {
			if (getChildAt(i).getVisibility() != View.GONE) {
				num += 1;
			}
		}
		return num;
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);		
	}
	
	/*
	 * 在重写的onlayout中定义各卡片的显示位置和方式
	 * 
	 * @see android.widget.RelativeLayout#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// 获取当前ViewGroup的宽度
		int parewidth = getWidth();
		int childCount = getVisibleChildCount();
		// 设置子View的位置
		int right = 0;
		int top = 0;
		// 计算内容区域的宽度
		int width = parewidth - childCount * dip2px(_context, CARDLABLEWIDTH) + dip2px(_context, CARDLABLEWIDTH);

		for (int i = 0; i < childCount; i++) {
			View child = getChildAt(i);
			// 判断是否显示
			if (child.getVisibility() == View.GONE) {
				continue;
			}

			MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
			lp.width = width;
			child.setLayoutParams(lp);

			if (i != _currentTop) {
				int cLeft = right + lp.leftMargin;
				int cRight = cLeft + child.getMeasuredWidth();
				int cTop = top + lp.topMargin;
				int cBottom = cTop + child.getMeasuredHeight();
				// 进行子View布局
				child.layout(cLeft, cTop, cRight, cBottom);
				right += dip2px(_context, CARDLABLEWIDTH) + lp.leftMargin + lp.rightMargin;
			} else {
				int cLeft = right + lp.leftMargin;
				int cRight = cLeft + child.getMeasuredWidth();
				int cTop = top + lp.topMargin;
				int cBottom = cTop + child.getMeasuredHeight();
				// 进行子View布局
				child.layout(cLeft, cTop, cRight, cBottom);
				right += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
			}

			child.setClickable(true);// to avoid multi-layout reaction
			child.setOnTouchListener(this);
		}
	}

	/*
	 * Convert dp to px
	 */
	private int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	private int lastX, origionX;

	boolean mIsWaitUpEvent = false;
	Runnable mTimerForUpEvent = new Runnable() {
		public void run() {
			if (mIsWaitUpEvent) {
				mIsWaitUpEvent = false;
			}
		}
	};
	boolean mIsOperatingNext = false;

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int operaNo = 0;
		for (int i = 0; i < getVisibleChildCount(); i++) {
			View child = getChildAt(i);
			if (child == v) {
				operaNo = i;
			}
		}

		int ea = event.getAction();
		switch (ea) {
		case MotionEvent.ACTION_DOWN:
			lastX = origionX = (int) event.getRawX();
			// 准备查看是否单击事件
			mIsWaitUpEvent = true;
			postDelayed(mTimerForUpEvent, MAX_INTERVAL_FOR_CLICK);
			break;
		case MotionEvent.ACTION_MOVE:
			// 先判断是否单击事件
			if (Math.abs((int) event.getRawX() - origionX) > MAX_DISTANCE_FOR_CLICK) {
				mIsWaitUpEvent = false;
				removeCallbacks(mTimerForUpEvent);

				int max = getWidth() - getVisibleChildCount() * dip2px(_context, CARDLABLEWIDTH);
				int dx = (int) event.getRawX() - lastX;

				// 拖动越界判断
				if ((operaNo == _currentTop && (event.getRawX() - origionX < 0))) { // 当前页面向左拖动时
					// 当前页面及其右侧的页面一起向左滑动

					if (operaNo + 1 <= getVisibleChildCount() - 1) {// 如果不是最右边的，拖动当前也页时，其实要打开右侧页面
						operaNo = (Math.min(operaNo + 1, getVisibleChildCount() - 1));
						mIsOperatingNext = true;
						for (int j = operaNo; j >= operaNo - 1; j--) {
							int left = getChildAt(j).getLeft() + dx;
							int top = getChildAt(j).getTop();
							int right = getChildAt(j).getRight() + dx;
							int bottom = getChildAt(j).getBottom();
							getChildAt(j).layout(left, top, right, bottom);
						}
					} else {// 如果是最右边的，只拖动当前页
						int left = getChildAt(operaNo).getLeft() + dx;
						int top = getChildAt(operaNo).getTop();
						int right = getChildAt(operaNo).getRight() + dx;
						int bottom = getChildAt(operaNo).getBottom();
						getChildAt(operaNo).layout(left, top, right, bottom);
					}

					lastX = (int) event.getRawX();

				} else if (!((event.getRawX() - origionX) >= dip2px(_context, CARDLABLEWIDTH) / 2 && operaNo > _currentTop)// 当前标签右侧的标签，向右挪动时
						&& !((event.getRawX() - origionX) <= -1 * dip2px(_context, CARDLABLEWIDTH) / 2 && operaNo < _currentTop)// 当前标签左侧的标签，向左挪动时
						&& !(Math.abs(event.getRawX() - origionX) >= max)) {// 任何情况下，都不能超过内容宽度

					int oldTop = _currentTop;
					int newTop = operaNo;
					// 对左右每个view执行动画效果,oldTop左边且curTop右边的全向右推，oldTop右边且curTop左边的全向左推,每一个都要向右挪到一个宽度的位置
					if (oldTop < newTop) {// oldTop右边且curTop左边的全向左推,每一个都要向右挪到一个宽度的位置
						if (dx > 0) {
							int left = getChildAt(operaNo).getLeft() + dx;
							int top = getChildAt(operaNo).getTop();
							int right = getChildAt(operaNo).getRight() + dx;
							int bottom = getChildAt(operaNo).getBottom();
							getChildAt(operaNo).layout(left, top, right, bottom);
						} else {
							for (int j = oldTop + 1; j <= newTop; j++) {
								int left = getChildAt(j).getLeft() + dx;
								int top = getChildAt(j).getTop();
								int right = getChildAt(j).getRight() + dx;
								int bottom = getChildAt(j).getBottom();
								getChildAt(j).layout(left, top, right, bottom);
							}
						}
					} else {// oldTop左边且curTop右边的全向右推,,每一个都要向右挪到一个宽度的位置
						if (dx < 0) {
							int left = getChildAt(operaNo).getLeft() + dx;
							int top = getChildAt(operaNo).getTop();
							int right = getChildAt(operaNo).getRight() + dx;
							int bottom = getChildAt(operaNo).getBottom();
							getChildAt(operaNo).layout(left, top, right, bottom);
						} else {
							for (int j = newTop; j <= oldTop; j++) {
								int left = getChildAt(j).getLeft() + dx;
								int top = getChildAt(j).getTop();
								int right = getChildAt(j).getRight() + dx;
								int bottom = getChildAt(j).getBottom();
								getChildAt(j).layout(left, top, right, bottom);
							}
						}
					}

					lastX = (int) event.getRawX();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			// 拖动的情况下
			if (Math.abs(lastX - origionX) > MAX_DISTANCE_FOR_CLICK) {
				mIsWaitUpEvent = false;
				removeCallbacks(mTimerForUpEvent);

				// 计算拉出的幅度够不够，不够的话缩回，够得话伸展到位并更新_currentTop
				int curX = lastX;
				int movedis = curX - origionX;
				int dis = Math.abs(movedis);

				if (mIsOperatingNext) {
					operaNo = (Math.min(operaNo + 1, getVisibleChildCount() - 1));
					mIsOperatingNext = false;
				}

				if (dis <= dip2px(_context, CARDLABLEWIDTH) / 2) {// 恢复原状
					int oldTop = _currentTop;
					int newTop = operaNo;
					// 对左右每个view执行动画效果,oldTop左边且curTop右边的全向右推，oldTop右边且curTop左边的全向左推,每一个都要向右挪到一个宽度的位置
					if (oldTop < newTop) {// oldTop右边且curTop左边的全向左推,每一个都要向右挪到一个宽度的位置
						for (int j = oldTop; j <= newTop; j++) {
							int curleft = getChildAt(j).getLeft();
							int desleft = getDestX(j);
							int left = desleft;
							int top = getChildAt(j).getTop();
							int right = getChildAt(j).getRight() + desleft - curleft;
							int bottom = getChildAt(j).getBottom();
							getChildAt(j).layout(left, top, right, bottom);
						}
					} else {// oldTop左边且curTop右边的全向右推,,每一个都要向右挪到一个宽度的位置
						for (int j = Math.max(newTop - 1, 0); j <= oldTop; j++) {
							int curleft = getChildAt(j).getLeft();
							int desleft = getDestX(j);
							int left = desleft;
							int top = getChildAt(j).getTop();
							int right = getChildAt(j).getRight() + desleft - curleft;
							int bottom = getChildAt(j).getBottom();
							getChildAt(j).layout(left, top, right, bottom);
						}
					}
				} else {// 拖动到位
					int oldTop = _currentTop;
					int newTop = operaNo;
					if (lastX < origionX) {// 向左滑动时，点击这个将成为主页
						_currentTop = operaNo;

					} else {// 向右滑动时，左边那个将成为主页
						_currentTop = operaNo - 1 >= 0 ? operaNo - 1 : 0;
					}
					if (oldTop < newTop) {// oldTop右边且curTop左边的全向左推,每一个都要向右挪到一个宽度的位置
						for (int j = oldTop; j <= newTop; j++) {
							int curleft = getChildAt(j).getLeft();
							int desleft = getDestX(j);
							int left = desleft;
							int top = getChildAt(j).getTop();
							int right = getChildAt(j).getRight() + desleft - curleft;
							int bottom = getChildAt(j).getBottom();
							getChildAt(j).layout(left, top, right, bottom);
						}
					} else {
						for (int j = Math.max(newTop - 1, 0); j <= oldTop; j++) {
							int curleft = getChildAt(j).getLeft();
							int desleft = getDestX(j);
							int left = desleft;
							int top = getChildAt(j).getTop();
							int right = getChildAt(j).getRight() + desleft - curleft;
							int bottom = getChildAt(j).getBottom();
							getChildAt(j).layout(left, top, right, bottom);
						}
					}
				}
			} else {// in case of click
				mIsWaitUpEvent = false;
				removeCallbacks(mTimerForUpEvent);
				onSingleClick(v);
			}

			break;
		case MotionEvent.ACTION_CANCEL:
			mIsWaitUpEvent = false;
			removeCallbacks(mTimerForUpEvent);
			break;
		}
		return false;
	}

	/*
	 * single click from ontouch event
	 */
	public void onSingleClick(View v) {
		int operaNo = 0;
		for (int i = 0; i < getVisibleChildCount(); i++) {
			View child = getChildAt(i);
			if (child == v) {
				operaNo = i;
			}
		}
		int oldTop = _currentTop;
		int newTop = operaNo;
		_currentTop = operaNo;
		if (oldTop < newTop) {// oldTop右边且curTop左边的全向左推,每一个都要向右挪到一个宽度的位置
			for (int j = oldTop + 1; j <= newTop; j++) {
				int curleft = getChildAt(j).getLeft();
				int desleft = getDestX(j);
				int left = desleft;
				int top = getChildAt(j).getTop();
				int right = getChildAt(j).getRight() + desleft - curleft;
				int bottom = getChildAt(j).getBottom();
				getChildAt(j).layout(left, top, right, bottom);
			}
		} else {
			for (int j = newTop; j <= oldTop; j++) {
				int curleft = getChildAt(j).getLeft();
				int desleft = getDestX(j);
				int left = desleft;
				int top = getChildAt(j).getTop();
				int right = getChildAt(j).getRight() + desleft - curleft;
				int bottom = getChildAt(j).getBottom();
				getChildAt(j).layout(left, top, right, bottom);

			}
		}
	}

	/*
	 * 计算每个控件的目标位置
	 */
	private int getDestX(int position) {
		int destX = 0;
		for (int i = 0; i < position; i++) {
			if (i == _currentTop) {
				View v = getChildAt(i);
				MarginLayoutParams lp = (MarginLayoutParams) v.getLayoutParams();
				destX += v.getWidth() + lp.leftMargin + lp.rightMargin;
			} else {
				View v = getChildAt(i);
				MarginLayoutParams lp = (MarginLayoutParams) v.getLayoutParams();
				destX += dip2px(_context, CARDLABLEWIDTH) + lp.leftMargin + lp.rightMargin;
			}
		}
		return destX;
	}

}
