package yk;

import java.awt.Color;
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
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.ui.TextAnchor;

@WebServlet("/BarChart4")
public class BarChart4 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		double [][]data = new double[][]{{1320,1000,800,700},{720,600,900,200},{830,1100,850,790},{1400,1050,200,450}};
		String []rowKeys = {"ƻ��","�㽶","����","����"}; 
		String []columnKeys = {"����","����","����","�Ϻ�"};
		
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
        
        CategoryPlot plot = chart.getCategoryPlot();
        // �������񱳾���ɫ
 		plot.setBackgroundPaint(Color.white);
 		// ��������������ɫ
 		plot.setDomainGridlinePaint(Color.pink);
 		// �������������ɫ
 		plot.setRangeGridlinePaint(Color.pink);
 		
 		// ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
 		BarRenderer3D renderer=new BarRenderer3D();
 		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
 		renderer.setBaseItemLabelsVisible(true);
 		
 		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
 		renderer.setItemLabelAnchorOffset(10D);  
 		
 		// ����ƽ������֮�����
 		renderer.setItemMargin(0.4);
 		plot.setRenderer(renderer);
        
        
        FileOutputStream fos_jpg = null; 
        long currentTimeMillis = System.currentTimeMillis();
        try { 
        	//��ͼƬ������Tomcat������WebRoot�µ�imgĿ¼��
            fos_jpg = new FileOutputStream(request.getSession().getServletContext().getRealPath("/")+currentTimeMillis+".jpg");
            ChartUtilities.writeChartAsJPEG(fos_jpg,1,chart,500,400,null); 
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
        
		request.getRequestDispatcher("index4.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
