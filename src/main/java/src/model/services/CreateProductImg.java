package src.model.services;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class CreateProductImg {

    public static void setupImg (JFrame createProductFrame, JLabel imageLabel, JButton selectImageButton) {

        selectImageButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "gif");
                fileChooser.setFileFilter(filter);

                int result = fileChooser.showOpenDialog(createProductFrame);

                if (result == JFileChooser.APPROVE_OPTION) {

                    File selectedFile = fileChooser.getSelectedFile();

                    String imagePath = selectedFile.getAbsolutePath();

                    ImageIcon originalIcon = new ImageIcon(imagePath);

                    Image originalImage = originalIcon.getImage();
                    Image scaledImage = originalImage.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

                    imageLabel.setText("");

                    ObtainImgPath.imagePath = imagePath;

                    imageLabel.setIcon(new ImageIcon(scaledImage));

                }

            }

        });

    }

}
