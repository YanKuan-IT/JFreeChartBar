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

@WebServlet("/BarChart2")
public class BarChart2 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//��ȡһ����ʾ�õ�������ݼ�����
		CategoryDataset dataset = getDataSet(); 
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
            ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,500,400,null); 
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
        request.getRequestDispatcher("/index2.jsp").forward(request, response);
	}
	/** 
    * ��ȡһ����ʾ�õ�������ݼ�����
    * @return 
    */ 
    private static CategoryDataset getDataSet() { 
    	DefaultCategoryDataset dataset = new DefaultCategoryDataset(); 
        dataset.addValue(100, "����", "ƻ��"); 
        dataset.addValue(100, "�Ϻ�", "ƻ��"); 
        dataset.addValue(100, "����", "ƻ��"); 
        dataset.addValue(200, "����", "����"); 
        dataset.addValue(200, "�Ϻ�", "����"); 
        dataset.addValue(200, "����", "����"); 
        dataset.addValue(300, "����", "����"); 
        dataset.addValue(300, "�Ϻ�", "����"); 
        dataset.addValue(300, "����", "����"); 
        dataset.addValue(400, "����", "�㽶"); 
        dataset.addValue(400, "�Ϻ�", "�㽶"); 
        dataset.addValue(400, "����", "�㽶"); 
        dataset.addValue(500, "����", "��֦"); 
        dataset.addValue(500, "�Ϻ�", "��֦"); 
        dataset.addValue(500, "����", "��֦"); 
        return dataset; 
    } 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
