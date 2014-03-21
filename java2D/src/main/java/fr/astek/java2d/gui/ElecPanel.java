package fr.astek.java2d.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import fr.astek.java2d.exception.MediaException;
import fr.astek.java2d.util.ImageHelper;

public class ElecPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int panelWidth = 0;
	private int panelHeight = 0;
	
	private int interX = 0;
	private int interY = 0;
	private int pileX = 0;
	private int pileY = 0;
	
	private Image pile;
	private Image interOn;
	private Image interOff;
	private Image ampouleOn;
	private Image ampouleOff;
	
	private boolean light = false;
	private boolean inter = false;
	private boolean pileOn = true;
	
	private Color cableColor = Color.RED;
	
	public ElecPanel(int width, int height) {
		panelWidth = width;
		panelHeight = height;
		setPreferredSize(new Dimension(width, height));
		try {
			pile = ImageHelper.loadImage("pile.png");
			interOn = ImageHelper.loadImage("inter_on.png");
			interOff = ImageHelper.loadImage("inter_off.png");
			ampouleOn = ImageHelper.loadImage("ampoule2.png");
			ampouleOff = ImageHelper.loadImage("ampoule1.png");
		} catch (MediaException e) {
			e.printStackTrace();
		}
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getX() >= interX && e.getX() <= interX+interOn.getWidth(null)
						&& e.getY() >= interY && e.getY() <= interY+interOn.getHeight(null)) {
					inter = !inter;
					checkLight();
				}
				else if (e.getX() >= pileX && e.getX() <= pileX+pile.getWidth(null)
						&& e.getY() >= pileY && e.getY() <= pileY+pile.getHeight(null)) {
					pileOn = !pileOn;
					checkLight();
				}
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		
		Color defaultColor = g2d.getColor();
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 700, 600);
		g2d.setColor(defaultColor);
		
		if (light) {
			g2d.drawImage(ampouleOn, (panelWidth-ampouleOn.getWidth(null))/2, 50, null);
		}
		else {
			g2d.drawImage(ampouleOff, (panelWidth-ampouleOff.getWidth(null))/2, 50, null);
		}
		
		interX = 50;
		if (inter) {
			interY = (panelHeight-interOn.getHeight(null))/2;
			g2d.drawImage(interOn, interX, interY, null);
		}
		else {
			interY = (panelHeight-interOff.getHeight(null))/2;
			g2d.drawImage(interOff, interX, interY, null);
		}
		
		pileX = (panelWidth-pile.getWidth(null))/2;
		if (pileOn) {
			pileY = (panelHeight-pile.getHeight(null)-100);
			g2d.drawImage(pile, pileX, pileY, null);
		}
		else {
			pileY = (panelHeight-pile.getHeight(null)-50);
			g2d.drawImage(pile, pileX, pileY, null);
		}
		
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(cableColor);
		
		int drawLigneX1 = 50+(interOn.getWidth(null)/2);
		int drawLigneY1 = 50;
		int drawLigneX2 = panelWidth/2;
		int drawLigneY2 = 50;
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		drawLigneX2 = drawLigneX1;
		drawLigneY2 = panelHeight/2 - interOn.getHeight(null)/2;
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		drawLigneY1 = panelHeight/2 + interOn.getHeight(null)/2;
		drawLigneY2 = (panelHeight-pile.getHeight(null)/2-100);
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		drawLigneY1 = drawLigneY2;
		drawLigneX2 = panelWidth/2 - pile.getWidth(null)/2;
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		drawLigneX1 = panelWidth/2 + pile.getWidth(null)/2;
		drawLigneX2 = panelWidth - 100;
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		drawLigneX1 = drawLigneX2;
		drawLigneY2 = 60;
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		drawLigneY1 = drawLigneY2;
		drawLigneX2 = panelWidth/2 + 10;
		g2d.drawLine(drawLigneX1, drawLigneY1, drawLigneX2, drawLigneY2);
		
		g2d.setColor(defaultColor);
		
	}
	
	private void checkLight() {
		
		if (pileOn && inter) {
			light = true;
			cableColor = Color.GREEN;
		}
		else {
			light = false;
			cableColor = Color.RED;
		}
		
		refresh();
	}
	
	public void refresh() {
		this.repaint();
	}

}
