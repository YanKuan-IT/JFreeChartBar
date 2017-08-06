package yk;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

@WebServlet("/BarChart5")
public class BarChart5 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CategoryDataset dataset = getDataSet(); //获取一个演示用的简单数据集对象
        JFreeChart chart = ChartFactory.createBarChart3D( 
                           "水果产量图", // 图表标题
                           "水果", // 目录轴的显示标签
                           "产量", // 数值轴的显示标签
                            dataset, // 数据集
                            PlotOrientation.VERTICAL, // 图表方向：水平、垂直
                            true,  // 是否显示图例(对于简单的柱状图必须是 false)
                            false, // 是否创建工具提示 (tooltip) 
                            false  // 是否生成 URL 链接
                            ); 
        
        response.setContentType("image/jpeg");
        
        OutputStream out = response.getOutputStream();
        
        ChartUtilities.writeChartAsJPEG(out,0.5f,chart,500,400,null); 
        
	}
	/** 
    * 获取一个演示用的简单数据集对象
    * @return 
    */ 
    private static CategoryDataset getDataSet() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        dataset.addValue(100, "", "苹果"); 
        dataset.addValue(200, "", "梨子"); 
        dataset.addValue(300, "", "葡萄"); 
        dataset.addValue(400, "", "香蕉"); 
        dataset.addValue(500, "", "荔枝"); 
        return dataset; 
    } 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
