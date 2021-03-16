package com.miriem.assignment3.presentation;

import com.miriem.assignment3.bll.PDFPrinter;
import com.miriem.assignment3.dao.ClientDAO;
import com.miriem.assignment3.dao.OrderDAO;
import com.miriem.assignment3.dao.ProductDAO;
import com.miriem.assignment3.models.Client;
import com.miriem.assignment3.models.Order;
import com.miriem.assignment3.models.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Parser {

    private ClientDAO clientDAO;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    private PDFPrinter pdfPrinter;

    public Parser() {
        clientDAO = new ClientDAO();
        productDAO = new ProductDAO();
        orderDAO = new OrderDAO();
        pdfPrinter = new PDFPrinter();
    }


    public void parse() {
        try {
            File myFile = new File("commands.txt");
            FileReader fr = new FileReader(myFile);
            BufferedReader br = new BufferedReader(fr);
            String currentLine;

            //am mers pe fiecare linie din fisierul txt
            while ((currentLine = br.readLine()) != null) {
                //verific ce comanda mi se cere(insert, report sau delete)
                //daca comanda mea e de tipul insert(startsWith: cu ce incepe linia curenta)
                if (currentLine.startsWith("Insert")) {
                    //verific ce trebuie sa inserez
                    //daca imi trebuie client
                    //imi returneaza -1 daca cuvantul client nu se gaseste in currentLine
                    if (currentLine.contains("client")) {
                        //am cautat cu indexOf indexul unde la care se gaseste ":" si ","->atunci stiu ca numele clientului e intre acesti indecsi
                        int indexColon = currentLine.indexOf(":");
                        int indexComma = currentLine.indexOf(",");

                        String clientName = currentLine.substring(indexColon + 2, indexComma);
                        String clientCity = currentLine.substring(indexComma + 2);

                        //trebuie sa-i dau comanda sa-mi insereze clientul in baza de date
                        clientDAO.insert(new Client(clientName, clientCity));
                    }

                    if (currentLine.contains("product")) {

                        int indexColon = currentLine.indexOf(":");
                        int indexComma = currentLine.indexOf(",");

                        String productName = currentLine.substring(indexColon + 2, indexComma);
                        String str = currentLine.substring(indexComma + 2);
                        indexComma = str.indexOf(",");
                        String productQuantity = str.substring(0, indexComma);
                        String productPrice = str.substring(indexComma + 2);

                        productDAO.insert(new Product(productName, Integer.parseInt(productQuantity), Double.parseDouble(productPrice)));
                    }
                }

                if (currentLine.startsWith("Delete")) {
                    if (currentLine.contains("client")) {

                        int indexColon = currentLine.indexOf(":");
                        int indexComma = currentLine.indexOf(",");

                        String clientName = currentLine.substring(indexColon + 2, indexComma);

                        Client myClient = clientDAO.findbyName(clientName);
                        clientDAO.deleteById(myClient.getId());
                        clientDAO.update(myClient);
                    }

                    if (currentLine.contains("Product")) {

                        int indexColon = currentLine.indexOf(":");
                        int indexComma = currentLine.indexOf(",");

                        String productName = currentLine.substring(indexColon + 2);

                        Product myProduct = productDAO.findbyName(productName);
                        productDAO.deleteById(myProduct.getId());
                        //productDAO.update(myProduct);
                    }
                }

                if (currentLine.startsWith("Order")) {

                    int indexColon = currentLine.indexOf(":");
                    int indexComma = currentLine.indexOf(",");
                    String clientName = currentLine.substring(indexColon + 2, indexComma);
                    String str = currentLine.substring(indexComma + 2);
                    indexComma = str.indexOf(",");
                    String productName = str.substring(0, indexComma);
                    String quantity = str.substring(indexComma + 2);


                    Product myProduct = productDAO.findbyName(productName);
                    if (myProduct.getQuantity() > Integer.parseInt(quantity)) {
                        Order order = new Order(clientName, productName, Integer.parseInt(quantity));
                        orderDAO.insert(order);
                        pdfPrinter.createBillPDF(order, false, myProduct);
                        myProduct.setQuantity(myProduct.getQuantity() - Integer.parseInt(quantity));
                        productDAO.update(myProduct);
                   } else {
                        pdfPrinter.createBillPDF(null, true, myProduct);
                    }
                }

                if (currentLine.startsWith("Report")) {
                    if (currentLine.contains("client")) {
                        pdfPrinter.createClientPDF(clientDAO.findAll());
                    }
                    if (currentLine.contains("order")) {
                        pdfPrinter.createOrderPDF(orderDAO.findAll());
                    }
                    if(currentLine.contains("product")){
                        pdfPrinter.createProductPDF(productDAO.findAll());
                    }
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}

