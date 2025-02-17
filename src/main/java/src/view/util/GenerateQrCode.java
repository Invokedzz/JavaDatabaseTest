package src.view.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import src.exceptions.QrCodeException;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

public class GenerateQrCode {

    public static ImageIcon create (String qrCodeUrl) {

        try {

            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.MARGIN, 1);

            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(qrCodeUrl, BarcodeFormat.QR_CODE, 300, 300, hints);

            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            return new ImageIcon(bufferedImage);

        } catch (WriterException exception) {

            throw new QrCodeException(exception.getMessage());

        }

    }

}
