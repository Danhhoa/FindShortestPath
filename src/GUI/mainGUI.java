package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import dijkstra.App;
import dijkstra.Map;
import dijkstra.Render;
import dijkstra.RenderMap;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;

public class mainGUI extends JFrame {

	private JPanel contentPane;
	private final JFileChooser openFileChooser;
	public static String filename;

	/**
	 * Create the frame.
	 */
	public mainGUI() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();

		openFileChooser = new JFileChooser();
		openFileChooser.setCurrentDirectory(new File(s));
		openFileChooser.setFileFilter(new FileNameExtensionFilter("Chỉ chọn file đuôi .txt", "txt"));
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		panel.setBounds(0, 0, 218, 355);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnSelectFindPath = new JButton("Tìm đường đi ngắn nhất");
		btnSelectFindPath.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSelectFindPath.setForeground(SystemColor.menuText);
		btnSelectFindPath.setBackground(UIManager.getColor("InternalFrame.activeTitleGradient"));
		btnSelectFindPath.setBounds(10, 26, 198, 58);
		panel.add(btnSelectFindPath);
		
				JButton btnScheduleCPU = new JButton("Lập lịch CPU");
				btnScheduleCPU.setBounds(10, 193, 198, 58);
				panel.add(btnScheduleCPU);
								
								JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
								tabbedPane.setBounds(228, 11, 519, 344);
								contentPane.add(tabbedPane);
								
								JPanel panel_1 = new JPanel();
								tabbedPane.addTab("Tìm đường đi ngắn nhất", null, panel_1, null);
										panel_1.setLayout(null);
								
										JButton btnOpenFile = new JButton("Open file...");
										btnOpenFile.setBounds(10, 108, 87, 23);
										panel_1.add(btnOpenFile);
										
												JLabel messageLabel = new JLabel("New label");
												messageLabel.setBounds(133, 108, 253, 23);
												panel_1.add(messageLabel);
												
												JButton btnStartFindPath = new JButton("Start find path");
												btnStartFindPath.setBounds(321, 154, 129, 23);
												panel_1.add(btnStartFindPath);
												
												JLabel lblNewLabel = new JLabel("Chọn file đuôi .txt chứa các đỉnh và cạnh cần xử lý");
												lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
												lblNewLabel.setBounds(10, 28, 376, 35);
												panel_1.add(lblNewLabel);
												
												JButton btnStartDraw = new JButton("Xem đồ thị");
												btnStartDraw.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														try {
															Render.startDraw(filename);
//															Map.startDraw(filename);
														} catch (NumberFormatException e1) {
															// TODO Auto-generated catch block
															e1.printStackTrace();
														}
													}
												});
												btnStartDraw.setBounds(321, 202, 129, 23);
												panel_1.add(btnStartDraw);
												btnStartFindPath.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														
												
															App.startDijkstra(filename);
															
														
														
													}
												});
										
												btnOpenFile.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														int returnValue = openFileChooser.showOpenDialog(null);
										
														if (returnValue == JFileChooser.APPROVE_OPTION) {
															// set the label to the path of the selected file
															filename = openFileChooser.getSelectedFile().getAbsolutePath();
															System.out.println("file chooser: " + filename);
															messageLabel.setText(filename);
														}
														// if the user cancelled the operation
														else
															messageLabel.setText("the user cancelled the operation");
													}
												});
								
								JPanel panel_2 = new JPanel();
								tabbedPane.addTab("Lập lịch CPU", null, panel_2, null);
										panel_2.setLayout(null);
				btnScheduleCPU.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
	}
}
