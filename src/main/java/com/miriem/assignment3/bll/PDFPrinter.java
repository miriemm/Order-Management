package com.miriem.assignment3.bll;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.miriem.assignment3.models.Client;
import com.miriem.assignment3.models.Order;
import com.miriem.assignment3.models.Product;

import java.awt.*;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Pdf printer
 */
public class PDFPrinter {

    private static int clientIndex = 0;
    private static int productIndex = 0;
    private static int orderIndex = 0;
    private static int billIndex = 0;

    /**
     * Creates client PDF
     * @param clients list of clients
     */
    public void createClientPDF(List<Client> clients) {

        Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            //special font sizes
            Font bfBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, new Color(0, 0, 0));
            Font bf12 = new Font(Font.TIMES_ROMAN, 12);

            //file path
            String path = "ReportClient" + clientIndex + ".pdf";
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));


            //open document
            doc.open();


            //specify column widths
            float[] columnWidths = {0.5f, 2f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "idClient", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "clientName", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "clientCity", Element.ALIGN_LEFT, 1, bfBold12);
            table.setHeaderRows(1);

            //just some random data to fill
            for (Client client : clients) {
                insertCell(table, String.valueOf(client.getId()), Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, String.valueOf(client.getName()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, String.valueOf(client.getCity()), Element.ALIGN_LEFT, 1, bf12);
            }


            //add the PDF table to the paragraph
            Paragraph paragraph = new Paragraph();
            paragraph.add(table);
            // add the paragraph to the document
            doc.add(paragraph);

        } catch (Exception dex) {
            dex.printStackTrace();
        } finally {
            //close the document
            doc.close();
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
            clientIndex++;
        }
    }


    /**
     * Creates order PDF
     * @param orders list of orders
     */
    public void createOrderPDF(List<Order> orders) {

        Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            //special font sizes
            Font bfBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, new Color(0, 0, 0));
            Font bf12 = new Font(Font.TIMES_ROMAN, 12);

            //file path
            String path = "ReportOrder" + orderIndex + ".pdf";
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));


            //open document
            doc.open();


            //specify column widths
            float[] columnWidths = {1.5f, 2f, 5f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "clientName", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "productName", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "quantity", Element.ALIGN_LEFT, 1, bfBold12);
            table.setHeaderRows(1);

            //just some random data to fill
            for (Order order : orders) {
                insertCell(table, String.valueOf(order.getClientName()), Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, String.valueOf(order.getProductName()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, String.valueOf(order.getQuantity()), Element.ALIGN_LEFT, 1, bf12);
            }

            Paragraph paragraph = new Paragraph();
            //add the PDF table to the paragraph
            paragraph.add(table);
            // add the paragraph to the document
            doc.add(paragraph);

        } catch (Exception dex) {
            dex.printStackTrace();
        } finally {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
            orderIndex++;
        }
    }

    /**
     * Creates product PDF
     * @param products list of products
     */
    public void createProductPDF(List<Product> products) {

        Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            //special font sizes
            Font bfBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, new Color(0, 0, 0));
            Font bf12 = new Font(Font.TIMES_ROMAN, 12);

            //file path
            String path = "ReportProduct" + productIndex + ".pdf";
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));


            //open document
            doc.open();


            //specify column widths
            float[] columnWidths = {1.5f, 2f, 2f, 2f};
            //create PDF table with the given widths
            PdfPTable table = new PdfPTable(columnWidths);
            // set table width a percentage of the page width
            table.setWidthPercentage(90f);

            //insert column headings
            insertCell(table, "idProduct", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "productName", Element.ALIGN_RIGHT, 1, bfBold12);
            insertCell(table, "quantity", Element.ALIGN_LEFT, 1, bfBold12);
            insertCell(table, "price", Element.ALIGN_LEFT, 1, bfBold12);
            table.setHeaderRows(1);

            //just some random data to fill
            for (Product product : products) {
                insertCell(table, String.valueOf(product.getId()), Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, String.valueOf(product.getName()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, String.valueOf(product.getQuantity()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, String.valueOf(product.getPrice()), Element.ALIGN_LEFT, 1, bf12);
            }

            Paragraph paragraph = new Paragraph();
            //add the PDF table to the paragraph
            paragraph.add(table);
            // add the paragraph to the document
            doc.add(paragraph);

        } catch (Exception dex) {
            dex.printStackTrace();
        } finally {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
            productIndex++;
        }
    }

    /**
     * Creates bill PDF containing the order
     * @param order order
     * @param outOfStock out of stock
     * @param product product
     */
    public void createBillPDF(Order order, Boolean outOfStock, Product product) {
        Document doc = new Document();
        PdfWriter docWriter = null;

        DecimalFormat df = new DecimalFormat("0.00");

        try {
            //special font sizes
            Font bfBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, new Color(0, 0, 0));
            Font bf12 = new Font(Font.TIMES_ROMAN, 12);
            //file path
            String path = "Bill" + billIndex + ".pdf";
            docWriter = PdfWriter.getInstance(doc, new FileOutputStream(path));
            //open document
            doc.open();
            //specify column widths
            float[] columnWidths = {1.5f, 2f, 2f, 2f};
            //create PDF table with the given widths
            if (!outOfStock) {
                PdfPTable table = new PdfPTable(columnWidths);
                // set table width a percentage of the page width
                table.setWidthPercentage(90f);

                //insert column headings
                insertCell(table, "Order ID", Element.ALIGN_RIGHT, 1, bfBold12);
                insertCell(table, "Client Name", Element.ALIGN_RIGHT, 1, bfBold12);
                insertCell(table, "Quantity", Element.ALIGN_LEFT, 1, bfBold12);
                insertCell(table, "Price", Element.ALIGN_LEFT, 1, bfBold12);
                table.setHeaderRows(1);

                //just some random data to fill
                insertCell(table, String.valueOf(order.getId()), Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, String.valueOf(order.getClientName()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, String.valueOf(order.getQuantity()), Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, String.valueOf(order.getQuantity() * product.getPrice()), Element.ALIGN_LEFT, 1, bf12);

                Paragraph paragraph = new Paragraph();
                //add the PDF table to the paragraph
                paragraph.add(table);
                // add the paragraph to the document
                doc.add(paragraph);
            } else {
                Paragraph paragraph = new Paragraph("The order was not fulfilled since " + product.getName() + " is out of stock!");
                doc.add(paragraph);
            }
        } catch (Exception dex) {
            dex.printStackTrace();
        } finally {
            if (doc != null) {
                //close the document
                doc.close();
            }
            if (docWriter != null) {
                //close the writer
                docWriter.close();
            }
            billIndex++;
        }
    }

    /**
     * Insert cells
     * @param table table
     * @param text text
     * @param align align
     * @param colspan colspan
     * @param font font
     */
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font) {

        //create a new cell with the specified Text and Font
        PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
        //set the cell alignment
        cell.setHorizontalAlignment(align);
        //set the cell column span in case you want to merge two or more cells
        cell.setColspan(colspan);
        //in case there is no text and you wan to create an empty row
        if (text.trim().equalsIgnoreCase("")) {
            cell.setMinimumHeight(10f);
        }
        //add the call to the table
        table.addCell(cell);

    }

}