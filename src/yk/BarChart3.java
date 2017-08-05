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
		String []rowKeys = {"ƻ��","�㽶","����","����"}; 
		String []columnKeys = {"����"};
		
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		
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
        	//��ͼƬ������Tomcat������WebRoot�µ�imgĿ¼��
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
