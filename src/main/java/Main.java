import HttpClient.HTTP;
import Util.Footer;
import Util.Serializer;
import Util.asciiArt;
import PDFParser.parser;
import Util.url;

import javax.swing.text.html.parser.Parser;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        asciiArt util = new asciiArt();
        Scanner in = new Scanner(System.in);

        String choice = "no";
        int logicalYear= 2020;
        String logicalType = "mds.pdf";


        System.out.println("");
        //ASCII ART BITCH
        util.printTextArt("PDF PARSER", 10);

        System.out.println("");

        System.out.print("Do you want to Download-Scan [0] or Scan [1] ->");
        int userScanChoice = in.nextInt();
        if(userScanChoice== 0)
        {
            //Default Values
            System.out.println("The default Values of:");
            System.out.println("1- Logical Year = " + logicalYear);
            System.out.println("2- Logical Type = " + logicalType);
            System.out.println("Do you want to Change these values [yes / no] -> ");
            String userChoice = in.nextLine();

            if(userChoice.contentEquals("yes") || userChoice.contentEquals("Yes"))
            {
                System.out.println("1- Logical Year ->");
                logicalYear = in.nextInt();
                System.out.println("2- Logical Type ->");
                logicalType= in.nextLine();
            }
            System.out.println("[+] URls generated");
            url generated_urls = new url(logicalYear, logicalType);
            System.out.println("[+] Downloading files");
            ArrayList<String> files = HTTP.download(generated_urls.getUrl());
            parser pdfparser = new parser();
            System.out.println("[+] Parsing files");
            ArrayList<Footer> footerobjectlist = pdfparser.parsePDFTOString(files);
            Serializer serializer = new Serializer();
            System.out.println("[+] Serializing Objects to JSON");
            serializer.to_json(footerobjectlist);
        }
        else if (userScanChoice == 1)
        {
            parser p = new parser();
            p.parsePDFTOString();
        }
        else
        {
            System.out.println("Unkown choice : Program Exiting");
            System.exit(0);
        }

    }
}
