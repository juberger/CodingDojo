package fr.astek.java2d.core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import fr.astek.java2d.gui.ElecPanel;
import fr.astek.java2d.gui.PaintPanel;

public class Application {
	
	private JFrame fenetre;
	
	private PaintPanel paintPanel;
	
	public Application() {
		init();
	}
	
	private void init() {
		fenetre = new JFrame();
		fenetre.setSize(new Dimension(800, 600));
		fenetre.setTitle("Mon Application");
		fenetre.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		
		content.add(commandePanel(), BorderLayout.LINE_START);
		
//		paintPanel = new PaintPanel(700, 600);
//		content.add(paintPanel, BorderLayout.CENTER);
		
		ElecPanel elecPanel = new ElecPanel(700, 600);
		content.add(elecPanel, BorderLayout.CENTER);
		
		fenetre.setContentPane(content);
	}
	
	private JPanel commandePanel() {
		JPanel commandePanel = new JPanel();
		commandePanel.setPreferredSize(new Dimension(100, 600));
		commandePanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		commandePanel.setLayout(new GridLayout(0,2));
		
		JLabel squareJLabel = new JLabel("Square");
		commandePanel.add(squareJLabel);
		final JCheckBox squareBox = new JCheckBox();
		squareBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawSquare(squareBox.isSelected());
			}
		});
		commandePanel.add(squareBox);
		
		JLabel ovalJLabel = new JLabel("Oval");
		commandePanel.add(ovalJLabel);
		final JCheckBox ovalBox = new JCheckBox();
		ovalBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawOval(ovalBox.isSelected());
			}
		});
		commandePanel.add(ovalBox);
		
		JLabel antiLabel = new JLabel("Anti");
		commandePanel.add(antiLabel);
		final JCheckBox antiBox = new JCheckBox();
		antiBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawAnti(antiBox.isSelected());
			}
		});
		commandePanel.add(antiBox);
		
		JLabel textJLabel = new JLabel("Texte");
		commandePanel.add(textJLabel);
		final JCheckBox texrBox = new JCheckBox();
		texrBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawText(texrBox.isSelected());
			}
		});
		commandePanel.add(texrBox);
		
		JLabel textureJLabel = new JLabel("Texture");
		commandePanel.add(textureJLabel);
		final JCheckBox textureBox = new JCheckBox();
		textureBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawTexture(textureBox.isSelected());
			}
		});
		commandePanel.add(textureBox);
		
		JLabel imageJLabel = new JLabel("Image");
		commandePanel.add(imageJLabel);
		final JCheckBox imageBox = new JCheckBox();
		imageBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawImage(imageBox.isSelected());
			}
		});
		commandePanel.add(imageBox);
		
		JLabel polygonJLabel = new JLabel("Polygon");
		commandePanel.add(polygonJLabel);
		final JCheckBox polygonBox = new JCheckBox();
		polygonBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawPolygon(polygonBox.isSelected());
			}
		});
		commandePanel.add(polygonBox);
		
		JLabel areaJLabel = new JLabel("Area");
		commandePanel.add(areaJLabel);
		final JCheckBox areaBox = new JCheckBox();
		areaBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawArea(areaBox.isSelected());
			}
		});
		commandePanel.add(areaBox);
		
		JLabel transformJLabel = new JLabel("Transform");
		commandePanel.add(transformJLabel);
		final JCheckBox transformBox = new JCheckBox();
		transformBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				paintPanel.drawTransform(transformBox.isSelected());
			}
		});
		commandePanel.add(transformBox);
		
		return commandePanel;
	}
	
	public void start() {
		fenetre.setVisible(true);
	}

	public static void main(String[] args) {
		Application application = new Application();
		application.start();
	}

}
