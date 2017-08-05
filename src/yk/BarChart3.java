package yk;

import java.io.FileOutputStream;
import java.io.IOException;

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
import org.jfree.data.general.DatasetUtilities;

@WebServlet("/BarChart3")
public class BarChart3 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double [][]data = new double[][]{{1320},{720},{830},{1400}};
		String []rowKeys = {"苹果","香蕉","橘子","梨子"}; 
		String []columnKeys = {"深圳"};
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
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
        FileOutputStream fos_jpg = null; 
        long currentTimeMillis = System.currentTimeMillis();
        try { 
        	//将图片保存至Tomcat服务器WebRoot下的img目录中
            fos_jpg = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+currentTimeMillis+".jpg");
            ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,400,300,null); 
        } catch (Exception e) {
        	System.out.println("error");
		} finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {
            	System.out.println("error2");
            } 
        }
        String filename = ""+currentTimeMillis+".jpg";
        request.setAttribute("filename", filename);
        
		request.getRequestDispatcher("index3.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
