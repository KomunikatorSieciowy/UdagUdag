package udagudag.view;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import udagudag.Main;

public class LoadingPanel extends JLabel {

	private JLabel centeredLabel;

	public LoadingPanel(int width, int height) {

		setPreferredSize(new Dimension(width, height));
		setOpaque(true);
		setIcon(new ImageIcon(Main.class.getResource("/background.png")));
		setLayout(new GridBagLayout());

		centeredLabel = new JLabel();
		centeredLabel.setPreferredSize(new Dimension(128,128));
		centeredLabel.setOpaque(false);
		centeredLabel.setIcon(new ImageIcon(Main.class.getResource("/loading.gif")));

		this.add(centeredLabel);		
	}
}
