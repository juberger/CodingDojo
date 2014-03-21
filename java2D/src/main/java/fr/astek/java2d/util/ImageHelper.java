package fr.astek.java2d.util;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import fr.astek.java2d.exception.MediaException;

public class ImageHelper {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	public static final String IMAGES_PATH = "/images";
	
	public static final GraphicsConfiguration graphicsConfiguration = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice().getDefaultConfiguration();;
	
	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
            
    private ImageHelper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Chargement d'une image par l'intermédiaire du classpath, cette image peut se trouver 
	 * dans le jar de l'application ou dans le dossier racine de celle-ci, sous le répertoire <code>/images</code>
	 * @param imageName Nom de l'image à charger
	 * @return Image chargée à partir d'un fichier
	 * @throws MediaException Si le chemin du fichier image n'a pas été trouvé ou si le fichier ne peut pas être 
	 * chargé en tant qu'image
	 */
	public static BufferedImage loadImage(String imageName) throws MediaException {
		BufferedImage lodedImage = null;
		URL imagePath = ImageHelper.class.getResource(IMAGES_PATH+"/"+imageName);
		BufferedImage compatibleImage = null;
		try {
			lodedImage = ImageIO.read(imagePath);
			compatibleImage = graphicsConfiguration.createCompatibleImage(lodedImage.getWidth(), lodedImage.getHeight(), Transparency.TRANSLUCENT);
			Graphics2D g2d = compatibleImage.createGraphics();
			g2d.setComposite(AlphaComposite.Src);
			g2d.drawImage(lodedImage, 0, 0, null);
			g2d.dispose();
		} catch (IOException | IllegalArgumentException ie) {
			throw new MediaException("Erreur lors du chargement de l'image "+imageName+", avec le chemin "+imagePath!=null?imagePath.getPath():"null", ie);
		}
		return compatibleImage;
	}
	
	/**
	 * Chargement d'une icone par l'intermédiaire du classpath, l'image de l'icone peut se trouver 
	 * dans le jar de l'application ou dans le dossier racine de celle-ci, sous le répertoire <code>/images</code>
	 * @param imageName Nom de l'image de l'icone à charger
	 * @return ImageIcon chargée à partir d'un fichier
	 * @throws MediaException Si le chemin du fichier image n'a pas été trouvé
	 */
	public static ImageIcon loadIcon(String imageName) throws MediaException {
		ImageIcon iconLoaded = null;
		URL imagePath = ImageHelper.class.getResource(IMAGES_PATH+"/"+imageName);
		if (imagePath != null) {
			iconLoaded = new ImageIcon(imagePath);
		}
		else {
			throw new MediaException("Erreur lors du chargement de l'image "+imageName+", le chemin n'a pas été trouvé");
		}
		return iconLoaded;
	}
	
	public static BufferedImage toBufferedImage(Image image) {
		
		BufferedImage bufferedImage = null;
		
        if (image != null) {
                /** On crée la nouvelle image */
                bufferedImage = new BufferedImage(
                            image.getWidth(null),
                            image.getHeight(null),
                            BufferedImage.TYPE_INT_ARGB );
                
                Graphics g = bufferedImage.createGraphics();
                g.drawImage(image,0,0,null);
                g.dispose();
        }
        
        return( bufferedImage );
	}
	
	public static TexturePaint toTexturePaint(Image image) {
		TexturePaint lRetour = null;
		
		BufferedImage bufferedImage = toBufferedImage(image);
		lRetour = new TexturePaint(bufferedImage,new Rectangle(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight()));
		
		return lRetour;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
}