package fr.astek.java2d.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.TexturePaint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import fr.astek.java2d.exception.MediaException;
import fr.astek.java2d.util.ImageHelper;

public class PaintPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	boolean texture = false;
	boolean square = false;
	boolean oval = false;
	boolean anti = false;
	boolean text = false;
	boolean image = false;
	boolean polygon = false;
	boolean area = false;
	boolean transform = false;
	Font font;
	Image astekImage;
	Image astekImageBlack;
	Image astekImageWhite;
	TexturePaint fondTexture;
	Rectangle2D recFond;
	boolean imageSwitch = false;
	int imageWidth = 0;
	int imageHeight = 0;
	
	public PaintPanel(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		font = new Font("Arial", Font.ITALIC, 18);
		try {
			astekImageBlack = ImageHelper.loadImage("AstekSudOuestNoir.gif");
			astekImageWhite = ImageHelper.loadImage("AstekSudOuestBlanc.png");
			fondTexture = ImageHelper.toTexturePaint(ImageHelper.loadImage("MetalFloors.jpg"));
		} catch (MediaException e) {
			e.printStackTrace();
		}
		

		recFond = new Rectangle2D.Double(0, 0, width, height);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int imageX = 120;
				int imageY = 40;
				if (e.getX() >= imageX && e.getX() <= imageX+imageWidth
						&& e.getY() >= imageY && e.getY() <= imageY+imageHeight) {
					imageSwitch = !imageSwitch;
					refresh();
				}
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		Color defaultColor = g2d.getColor();
		Font defaultFont = g2d.getFont();
		
		if (texture) {
			Paint defaultPaint = g2d.getPaint();
			g2d.setPaint(fondTexture);
			g2d.fill(recFond);
			g2d.setPaint(defaultPaint);
		}
		if (anti) {
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		if (square) {
			g2d.drawRect(10, 10, 100, 100);
		}
		if (oval) {
			g2d.setColor(Color.BLUE);
			g2d.drawOval(10, 120, 100, 100);
			g2d.setColor(defaultColor);
		}
		if (text) {
			g2d.setFont(font);
			g2d.drawString("Ecrivons un texte", 120, 20);
			g2d.setFont(defaultFont);
		}
		if (image) {
			if (imageSwitch) {
				g2d.drawImage(astekImageWhite, 120, 40, null);
				imageWidth = astekImageWhite.getWidth(null);
				imageHeight = astekImageWhite.getHeight(null);
			}
			else {
				g2d.drawImage(astekImageBlack, 120, 40, null);
				imageWidth = astekImageBlack.getWidth(null);
				imageHeight = astekImageBlack.getHeight(null);
			}
		}
		if(polygon) {
			int x1Points[] = {10, 100, 10, 100};
			int y1Points[] = {10, 50, 50, 10};
			GeneralPath polygon = 
			        new GeneralPath(GeneralPath.WIND_EVEN_ODD,
			                        x1Points.length);
			polygon.moveTo(x1Points[0], y1Points[0]);

			for (int index = 1; index < x1Points.length; index++) {
			        polygon.lineTo(x1Points[index], y1Points[index]);
			};

			polygon.closePath();
			Stroke defaultStroke = g2d.getStroke();
			g2d.setStroke(new BasicStroke(2));
			g2d.draw(polygon);
			g2d.setStroke(defaultStroke);
		}
		if(area) {
			Shape rond = new Ellipse2D.Double(50,50,160,100);
			Shape carre = new Rectangle2D.Double(150,90,80,100);
			Area area1 = new Area(rond);
			Area area2 = new Area(carre);
			area1.add(area2);
			
			g2d.draw(area1);
		}
		if(transform) {
			g2d.draw(new Rectangle(10, 10, 100, 50));
			AffineTransform transformer = new AffineTransform();
			transformer.scale(1.5, 1.5);
			transformer.translate(100, 100);
			transformer.rotate(Math.toRadians(90));
			g2d.setTransform(transformer);
			g2d.draw(new Rectangle(10, 10, 100, 50));
		}
	}
	
	public void drawSquare(boolean draw) {
		square = draw;
		refresh();
	}
	
	public void drawOval(boolean draw) {
		oval = draw;
		refresh();
	}
	
	public void drawAnti(boolean draw) {
		anti = draw;
		refresh();
	}

	
	public void drawText(boolean draw) {
		text = draw;
		refresh();
	}
	
	public void drawImage(boolean draw) {
		image = draw;
		refresh();
	}
	
	public void drawPolygon(boolean draw) {
		polygon = draw;
		refresh();
	}
	
	public void drawArea(boolean draw) {
		area = draw;
		refresh();
	}
	
	public void drawTransform(boolean draw) {
		transform = draw;
		refresh();
	}
	
	public void drawTexture(boolean draw) {
		texture = draw;
		refresh();
	}
	
	public void refresh() {
		this.repaint();
	}
}