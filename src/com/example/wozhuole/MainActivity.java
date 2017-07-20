package com.example.wozhuole;

import java.util.ArrayList;
import java.util.List;

import me.angeldevil.autoscrollviewpager.AutoScrollViewPager;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.viewpagerindicator.CirclePageIndicator;




public class MainActivity extends Activity {
	private LinearLayout ll_home;
	TextView textView;
	ValueAnimator anim_rv;
//测试看看行不行
	
	//这是sourceTree提交测试的代码
	
//这个是eclipse提交的测试代码
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ll_home=(LinearLayout)findViewById(R.id.ll_home);
		LinearLayout.LayoutParams tv_homePageHint_lp = new LinearLayout.LayoutParams(
				-1, -1);
		
		textView = getTextView(tv_homePageHint_lp, "数据加载失败，点击重新加载",
				Gravity.CENTER, 14, "#777777");
		ll_home.addView(textView);
		textView.setVisibility(View.GONE);
		anim_rv = ValueAnimator.ofInt(0, 1000);
		anim_rv.setDuration(Integer.MAX_VALUE);
		anim_rv.setInterpolator(new LinearInterpolator());
		List<String> list=new ArrayList<String>();
		list.add("这是第一个滑动页面");
		list.add("这是第er个滑动页面");
		list.add("这是第一san滑动页面");
		list.add("这是第si滑动页面");
		list.add("这是第wu个滑动页面");
		list.add("这是第六个滑动页面");
		list.add("这是第七个滑动页面");
		list.add("这是第八个滑动页面");
		
		
		
		ll_home.addView(getAdLayout(list,MainActivity.this));
		
		
	}
	
	
	
	// 获取广告布局
		private View getAdLayout(List<String> list, Context mContext) {
			String kuangaobi = "0.30";
//			if (list.size() > 0 && !TextUtils.isEmpty(list.get(0))) {
//				kuangaobi = list.get(0);
//			}
			RelativeLayout rl_ad = new RelativeLayout(mContext);
			int withDisplay = getWithDisplay(mContext);

			int showHeight = (int) (withDisplay * Double.valueOf(kuangaobi));
			LinearLayout.LayoutParams lp_rl_ad = new LinearLayout.LayoutParams(-1,
					showHeight);
			rl_ad.setLayoutParams(lp_rl_ad);

			AutoScrollViewPager autoScrollViewPager = new AutoScrollViewPager(
					mContext);
			RelativeLayout.LayoutParams lp_asp = new RelativeLayout.LayoutParams(
					-1, -1);
			autoScrollViewPager.setLayoutParams(lp_asp);

			CirclePageIndicator cpi = new CirclePageIndicator(mContext);
			RelativeLayout.LayoutParams lp_cpi = new RelativeLayout.LayoutParams(
					-1, -2);
			lp_cpi.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
			cpi.setLayoutParams(lp_cpi);
			cpi.setPadding(0, dip2px(mContext, 10), 0,
					dip2px(mContext, 10));

			rl_ad.addView(autoScrollViewPager);
			rl_ad.addView(cpi);

			initAutoScrollViewPager(list, autoScrollViewPager, cpi);

			return rl_ad;
		}
		
		
		// 初始化广告滚动条
		private void initAutoScrollViewPager(final List<String> list,
				AutoScrollViewPager asp, CirclePageIndicator cpi) {
			MyViewPagerAdapter vp_adapter = new MyViewPagerAdapter(list);
			asp.setAdapter(vp_adapter);
			cpi.setViewPager(asp);
			cpi.setSnap(true);
			asp.setOnPageClickListener(new AutoScrollViewPager.OnPageClickListener() {
				@Override
				public void onPageClick(AutoScrollViewPager autoScrollPager,
						int position) {
					if (list.size() > 0) {
						
					}
				}
			});
			asp.setScrollFactgor(list.size());
			asp.setOffscreenPageLimit(list.size());
			asp.startAutoScroll();
		}
		
		
	    /**
	     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	     */
	    public static int dip2px(Context context, float dpValue) {
	        final float scale = context.getResources().getDisplayMetrics().density;
	        return (int) (dpValue * scale + 0.5f);
	    }
	
		
		 public static int getWithDisplay(Context mContext)
		    {

		        return mContext.getResources().getDisplayMetrics().widthPixels;
		    }
	
	
	private TextView getTextView(ViewGroup.LayoutParams lp, String msg,
			int gravity, int textSize, String colorValue) {
		TextView tv = new TextView(MainActivity.this);
		tv.setLayoutParams(lp);
		tv.setGravity(gravity);
		tv.setTextSize(textSize);
		tv.setTextColor(Color.parseColor(colorValue));
		tv.setText(msg);

		return tv;
	}
	class MyViewPagerAdapter extends PagerAdapter {

		private List<String> list_adbean;

		public MyViewPagerAdapter(List<String> list) {
			this.list_adbean = list;
		}

		@Override
		public int getCount() {
			return list_adbean.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object o) {
			return view == o;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			TextView view = new TextView(container.getContext());
//			view.setScaleType(TextView.SCALE_X);
			if (list_adbean.size() > 0)
				
				view.setText(list_adbean.get(position));
//				ImageLoader.getInstance().displayImage(
//						list_adbean.get(position).imageurl, view,
//						Options.getPortraitOptions(R.drawable.iv_adbaner_default));
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

}
