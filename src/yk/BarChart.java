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
import org.jfree.data.category.DefaultCategoryDataset;

@WebServlet("/BarChart")
public class BarChart extends HttpServlet {
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
                            
        FileOutputStream fos_jpg = null; 
        long currentTimeMillis = System.currentTimeMillis();
        try {
        	//��ͼƬ����������f��
//	        fos_jpg = new FileOutputStream("f:\\"+System.currentTimeMillis()+".jpg"); 
        	//��ͼƬ������Tomcat������WebRoot�µ�imgĿ¼��
            fos_jpg = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+currentTimeMillis+".jpeg");
            ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,400,300,null); 
        } catch (Exception e) {
        	System.out.println("error");
		} finally { 
            try { 
                fos_jpg.close(); 
            } catch (Exception e) {
            	System.out.println("error");
            } 
        } 
        String filename = currentTimeMillis+".jpeg";
        request.setAttribute("filename", filename);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
