package com.bcstudents.personnelmanagement.util;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ObjectToPdf {
    public static void strToPdf(String content,String filePath) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document,new FileOutputStream(filePath));
        BaseFont bfEnglish = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
        Font font = new Font(bfEnglish, 12, Font.NORMAL);
        //Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);


        // Set the body content
        Paragraph ph = new Paragraph(content,font);
        document.open();
        document.add(ph);
        document.close();
    }
    public static void strToDoc(String context,String filePath){
        try (XWPFDocument document = new XWPFDocument()) {
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText(context);

            FileOutputStream out = new FileOutputStream(filePath);
            document.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void strToJpg(String filePath,String output) throws Exception{
        java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 20); // Font type
        Color textColor = Color.BLACK; // Text color
        Color backgroundColor = Color.WHITE; // Background color

        int margin=20;
        int width = 800; // Image width
        int height = getTxtHeigth(width,margin,filePath,font,textColor,backgroundColor); // Image height
        try {
            // Create image object
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();

            // Set background color
            graphics.setColor(backgroundColor);
            graphics.fillRect(0, 0, width, height);

            // Set text font and text color
            graphics.setFont(font);
            graphics.setColor(textColor);

            // Consider border width
            Insets insets = new Insets(margin, margin, margin, margin);
            int availableWidth = width - insets.left - insets.right;
            //int availableHeight = height - insets.top - insets.bottom;

            // Read txt file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int textHeight = 0; // Txt height
            FontMetrics fontMetrics = graphics.getFontMetrics();
            while ((line = reader.readLine()) != null) {
                line = line.replace("\\n", System.lineSeparator()); // Replace "\n" with the system's line separator

                // Process line breaks
                int lineWidth = fontMetrics.stringWidth(line);
                if (lineWidth > availableWidth) {
                    StringBuilder sb = new StringBuilder();
                    int startIndex = 0;
                    int endIndex = 0;
                    while (startIndex < line.length()) {
                        while (endIndex < line.length() && fontMetrics.stringWidth(sb.toString() + line.charAt(endIndex)) <= availableWidth) {
                            sb.append(line.charAt(endIndex));
                            endIndex++;
                        }
                        graphics.drawString(sb.toString(), insets.left, insets.top + textHeight + fontMetrics.getHeight());
                        textHeight += fontMetrics.getHeight();
                        sb.setLength(0);
                        startIndex = endIndex;
                    }
                    if (startIndex < line.length()) {
                        sb.append(line.substring(startIndex));
                        graphics.drawString(sb.toString(), insets.left, insets.top + textHeight + fontMetrics.getHeight());
                        textHeight += fontMetrics.getHeight();
                    }
                } else {
                    graphics.drawString(line, insets.left, insets.top + textHeight + fontMetrics.getHeight());
                    textHeight += fontMetrics.getHeight();
                }
            }

            reader.close();


            // Save the image to a file
            ImageIO.write(image, "jpg", new File(output));

            // System.out.println("Text written to image successfully!");
            // System.out.println("Total text height: " + textHeight);
        } catch (Exception ex) {
            System.out.println("Failed to write text to image: " + ex.getMessage());
        }
    }
    public static void strToPng(String filePath,String output) throws Exception{
        java.awt.Font font = new java.awt.Font("Arial", java.awt.Font.PLAIN, 20); // Font type
        Color textColor = Color.BLACK; // Text color
        Color backgroundColor = Color.WHITE; // Background color

        int margin=20;
        int width = 800; // Image width
        int height = getTxtHeigth(width,margin,filePath,font,textColor,backgroundColor); // Image height
        try {

            // Create an image object
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();

            // Set the background color
            graphics.setColor(backgroundColor);
            graphics.fillRect(0, 0, width, height);

            // Set the font and color
            graphics.setFont(font);
            graphics.setColor(textColor);

            // Consider margins
            Insets insets = new Insets(margin, margin, margin, margin);
            int availableWidth = width - insets.left - insets.right;
            //int availableHeight = height - insets.top - insets.bottom;

            // Read text file
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int textHeight = 0; // Text height
            FontMetrics fontMetrics = graphics.getFontMetrics();
            while ((line = reader.readLine()) != null) {
                line = line.replace("\\n", System.lineSeparator()); // Replace "\n" with the system's line separator

                // Handle line breaks
                int lineWidth = fontMetrics.stringWidth(line);
                if (lineWidth > availableWidth) {
                    StringBuilder sb = new StringBuilder();
                    int startIndex = 0;
                    int endIndex = 0;
                    while (startIndex < line.length()) {
                        while (endIndex < line.length() && fontMetrics.stringWidth(sb.toString() + line.charAt(endIndex)) <= availableWidth) {
                            sb.append(line.charAt(endIndex));
                            endIndex++;
                        }
                        graphics.drawString(sb.toString(), insets.left, insets.top + textHeight + fontMetrics.getHeight());
                        textHeight += fontMetrics.getHeight();
                        sb.setLength(0);
                        startIndex = endIndex;
                    }
                    if (startIndex < line.length()) {
                        sb.append(line.substring(startIndex));
                        graphics.drawString(sb.toString(), insets.left, insets.top + textHeight + fontMetrics.getHeight());
                        textHeight += fontMetrics.getHeight();
                    }
                } else {
                    graphics.drawString(line, insets.left, insets.top + textHeight + fontMetrics.getHeight());
                    textHeight += fontMetrics.getHeight();
                }
            }

            reader.close();

            // Save the image to a file
            ImageIO.write(image, "png", new File(output));

            // System.out.println("Text written to image successfully!");
            // System.out.println("Total text height: " + textHeight);
        } catch (Exception ex) {
            System.out.println("Failed to write text to image: " + ex.getMessage());
        }
    }
    private static int getTxtHeigth(int width,int margin, String filePath, java.awt.Font font, Color textColor, Color backgroundColor) throws Exception{
        // Create an image object
        int height=200;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        // Set the background color
        graphics.setColor(backgroundColor);
        graphics.fillRect(0, 0, width, height);

        // Set the font and color
        graphics.setFont(font);
        graphics.setColor(textColor);

        // Consider margins
        Insets insets = new Insets(margin, margin, margin, margin);
        int availableWidth = width - insets.left - insets.right;
        int availableHeight = height - insets.top - insets.bottom;

        // Read text file
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        int textHeight = 0; // Text height
        FontMetrics fontMetrics = graphics.getFontMetrics();
        while ((line = reader.readLine()) != null) {
            line = line.replace("\\n", System.lineSeparator()); // Replace "\n" with the system's line separator

            // Handle line breaks
            int lineWidth = fontMetrics.stringWidth(line);
            if (lineWidth > availableWidth) {
                StringBuilder sb = new StringBuilder();
                int startIndex = 0;
                int endIndex = 0;
                while (startIndex < line.length()) {
                    while (endIndex < line.length() && fontMetrics.stringWidth(sb.toString() + line.charAt(endIndex)) <= availableWidth) {
                        sb.append(line.charAt(endIndex));
                        endIndex++;
                    }
                    graphics.drawString(sb.toString(), insets.left, insets.top + textHeight + fontMetrics.getHeight());
                    textHeight += fontMetrics.getHeight();
                    sb.setLength(0);
                    startIndex = endIndex;
                }
                if (startIndex < line.length()) {
                    sb.append(line.substring(startIndex));
                    graphics.drawString(sb.toString(), insets.left, insets.top + textHeight + fontMetrics.getHeight());
                    textHeight += fontMetrics.getHeight();
                }
            } else {
                graphics.drawString(line, insets.left, insets.top + textHeight + fontMetrics.getHeight());
                textHeight += fontMetrics.getHeight();
            }
        }

        reader.close();


        return textHeight+40;
    }

    public static void main(String[] args) throws Exception{
        //new ObjectToPdf().strToPdf();
        // new ObjectToPdf().strToDoc();
    }
}
