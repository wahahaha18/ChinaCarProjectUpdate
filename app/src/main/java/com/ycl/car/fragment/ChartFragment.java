package com.ycl.car.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ycl.car.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by y11621546 on 2017/3/7.
 */

public class ChartFragment extends BasePageFragment {
    public final static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec",};
    List<AxisValue> axisValues;
    public final static String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun",};
    @BindView(R.id.btn_test)
    Button btnTest;
    @BindView(R.id.chart)
    LineChartView chart;
    private LineChartData lineData;

    private int i;

    public static ChartFragment newInstance() {

        Bundle args = new Bundle();

        ChartFragment fragment = new ChartFragment();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void fetchData() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chart, container, false);
        int numValues = 7;
        ButterKnife.bind(this, view);
        axisValues = new ArrayList<AxisValue>();
        List<PointValue> values = new ArrayList<PointValue>();
        for (int i = 0; i < numValues; ++i) {
            values.add(new PointValue(i, i + 10));
            axisValues.add(new AxisValue(i).setLabel(days[i]));
        }

        Line line = new Line(values);
        line.setColor(ChartUtils.COLOR_GREEN).setCubic(true);

        List<Line> lines = new ArrayList<Line>();
        lines.add(line);

        lineData = new LineChartData(lines);
        lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
        lineData.setAxisYLeft(new Axis().setHasLines(true).setMaxLabelChars(3));

        chart.setLineChartData(lineData);

        // For build-up animation you have to disable viewport recalculation.
        chart.setViewportCalculationEnabled(false);

        // And set initial max viewport and current viewport- remember to set viewports after data.
//        Viewport v = new Viewport(0, 10, 10, 0);
//        binding.chart.setMaximumViewport(v);
//        binding.chart.setCurrentViewport(v);

        chart.setZoomType(ZoomType.HORIZONTAL);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Viewport v = new Viewport(0, 0, ++i, 0);
//                binding.chart.setCurrentViewport(v);
                axisValues.add(new AxisValue(++i + 7));
                lineData.setAxisXBottom(new Axis(axisValues).setHasLines(true));
            }
        });


        return view;


    }


}
