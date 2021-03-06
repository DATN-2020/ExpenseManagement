package datn.datn_expansemanagement.view.custom_charts;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.highlight.BarHighlighter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;

public class CustomBarChart extends BarLineChartBase<BarData> implements BarDataProvider{

    private boolean mFitBars = false;
    private boolean mDrawBarShadow = false;
    private boolean mDrawValueAboveBar = true;
    protected boolean mHighlightFullBarEnabled = false;

    public CustomBarChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomBarChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomBarChart(Context context) {
        super(context);
    }

    @Override
    protected void init() {
        super.init();
        mRenderer = new CustomBarChartRenderer(this, mAnimator, mViewPortHandler);
        setHighlighter(new BarHighlighter(this));

        getXAxis().setSpaceMin(0.5f);
        getXAxis().setSpaceMax(0.5f);
    }

    @Override
    protected void calcMinMax() {
        super.calcMinMax();
        if(mFitBars){
            mXAxis.calculate(mData.getXMin() - mData.getBarWidth() / 2f, mData.getXMax() + mData.getBarWidth() / 2f);
        }else {
            mXAxis.calculate(mData.getXMin(), mData.getXMax());
        }

        mAxisLeft.calculate(mData.getYMin(YAxis.AxisDependency.LEFT), mData.getYMax(YAxis.AxisDependency.LEFT));
    }


    @Override
    public Highlight getHighlightByTouchPoint(float x, float y) {
        if (mData == null) {
            Log.e(LOG_TAG, "Can't select by touch. No data set.");
            return null;
        } else {
            Highlight h = getHighlighter().getHighlight(x, y);
            if (h == null || !isHighlightFullBarEnabled()) return h;

            // For isHighlightFullBarEnabled, remove stackIndex
            return new Highlight(h.getX(), h.getY(),
                    h.getXPx(), h.getYPx(),
                    h.getDataSetIndex(), -1, h.getAxis());
        }
    }

    @Override
    public BarData getBarData() {
        return mData;
    }

    @Override
    public boolean isDrawBarShadowEnabled() {
        return mDrawBarShadow;
    }

    @Override
    public boolean isDrawValueAboveBarEnabled() {
        return mDrawValueAboveBar;
    }

    @Override
    public boolean isHighlightFullBarEnabled() {
        return mHighlightFullBarEnabled;
    }
}