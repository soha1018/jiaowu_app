package net.common.android.Engine;

import android.content.Context;
import android.graphics.Color;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 画柱形图的引擎类
 * Created by Administrator on 2017/5/17.
 */

public class BarChart {
	private Context context;
	private String chartName;
	private int length = 3;
	private int[] xv = new int[length];
	private String[] xData = new String[length];
	private XYMultipleSeriesRenderer renderer;
	private XYMultipleSeriesDataset dataset;
	private double yMax = 100;
	private int addX, addY;
	private XYSeries series1;
	private GraphicalView chartView;


	public BarChart(Context context, String chartName) {
		this.context = context;
		this.chartName = chartName;
	}

	public GraphicalView getView(int max) {
		this.yMax = max;
		renderer = new XYMultipleSeriesRenderer();
		dataset = new XYMultipleSeriesDataset();

		setRenderer(renderer);

		series1 = new XYSeries("一班");

		dataset.addSeries(series1);


		int[] colors = new int[]{Color.GREEN};
		XYSeriesRenderer seriesRenderer;
		for (int i = 0; i < colors.length; i++) {
			seriesRenderer = new XYSeriesRenderer();
			seriesRenderer.setDisplayChartValues(true);
			seriesRenderer.setColor(colors[i]);
			seriesRenderer.setLineWidth(2f);
			seriesRenderer.setPointStyle(PointStyle.CIRCLE);
			seriesRenderer.setFillPoints(true);
			seriesRenderer.setChartValuesTextSize(30f);

			renderer.addSeriesRenderer(seriesRenderer);
		}


		if (chartView == null) {
			chartView = ChartFactory.getBarChartView(context, dataset, renderer, org.achartengine.chart.BarChart.Type.DEFAULT);
		} else {
			chartView.repaint();
		}
		return chartView;
	}

	/**
	 * 获得当前的时间
	 *
	 * @return 时间
	 */
	private String getTime() {
		SimpleDateFormat dateFormat = (SimpleDateFormat) SimpleDateFormat.getDateInstance();
		dateFormat.applyPattern("HH:mm:ss");
		return dateFormat.format(new Date());
	}

	public void upData(int values1) {
		addX = 1;
		addY = values1;
		String time = getTime();

		int count = series1.getItemCount();
		if (count > length) {
			count = length;
		}

		dataset.removeSeries(series1);

		if (count < length) {
			series1.add(count+1, addY);
			renderer.addXTextLabel(count+1,time);
			xData[count] = time;
		} else {
			for (int i = 0; i < length - 1; i++) {
				xv[i] = (int) series1.getY(i + 1);
			}

			series1.clear();

			for (int i = 0; i < length - 1; i++) {
				series1.add(i+1, xv[i]);
			}

			xData[length-1] = time;
			series1.add(length,addY);
			renderer.addXTextLabel(length,time);
		}

		dataset.addSeries(series1);

		chartView.invalidate();

	}


	private void setRenderer(XYMultipleSeriesRenderer renderer) {
		renderer.setChartTitle(chartName);
		renderer.setChartTitleTextSize(45f);
		renderer.setXTitle("时间");
		renderer.setXLabels(0);
		renderer.setYLabels(10);
		renderer.setPanEnabled(false, false);
		renderer.setZoomEnabled(false, false);
		renderer.setClickEnabled(false);
		renderer.setMarginsColor(0x00888888);
		renderer.setBarSpacing(0.4f);
		renderer.setAxisTitleTextSize(30f);
		renderer.setPointSize(5f);
		renderer.setLabelsTextSize(30f);
		renderer.setLegendTextSize(25f);
		renderer.setXAxisMin(0);
		renderer.setXAxisMax(length + 2);
		renderer.setYAxisMin(0);
		renderer.setYAxisMax(yMax);
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setLabelsColor(Color.BLACK);
		renderer.setAxesColor(Color.BLACK);
	}

}
