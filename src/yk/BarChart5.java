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
		
		CategoryDataset dataset = getDataSet(); //��ȡһ����ʾ�õļ����ݼ�����
        JFreeChart chart = ChartFactory.createBarChart3D( 
                           "ˮ������ͼ", // ͼ�����
                           "ˮ��", // Ŀ¼�����ʾ��ǩ
                           "����", // ��ֵ�����ʾ��ǩ
                            dataset, // ���ݼ�
                            PlotOrientation.VERTICAL, // ͼ����ˮƽ����ֱ
                            true,  // �Ƿ���ʾͼ��(���ڼ򵥵���״ͼ������ false)
                            false, // �Ƿ񴴽�������ʾ (tooltip) 
                            false  // �Ƿ����� URL ����
                            ); 
        
        response.setContentType("image/jpeg");
        
        OutputStream out = response.getOutputStream();
        
        ChartUtilities.writeChartAsJPEG(out,0.5f,chart,500,400,null); 
        
	}
	/** 
    * ��ȡһ����ʾ�õļ����ݼ�����
    * @return 
    */ 
    private static CategoryDataset getDataSet() { 
        DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        dataset.addValue(100, "", "ƻ��"); 
        dataset.addValue(200, "", "����"); 
        dataset.addValue(300, "", "����"); 
        dataset.addValue(400, "", "�㽶"); 
        dataset.addValue(500, "", "��֦"); 
        return dataset; 
    } 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
