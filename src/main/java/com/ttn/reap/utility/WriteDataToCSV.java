package com.ttn.reap.utility;

import com.ttn.reap.entity.BadgeTransaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;


public class WriteDataToCSV {

    public static void writeObjectToCSV(PrintWriter writer,List<BadgeTransaction> transactions) {
        try (
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .withHeader("SenderName", "ReceiverName","Badge", "Message", "Date"));
        ) {
            for (BadgeTransaction transaction :transactions) {
                List<String> data = (List<String>) Arrays.asList(
                        transaction.getSender().getFirstName()+" "+transaction.getSender().getLastName(),
                        transaction.getReceiver().getFirstName()+" "+transaction.getReceiver().getLastName(),
                        transaction.getBadge().getBadge(),
                        transaction.getMessage(),
                        transaction.getDate()
                );

                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
        } catch (Exception e) {
            System.out.println("Writing CSV error!");
            e.printStackTrace();
        }
    }
}