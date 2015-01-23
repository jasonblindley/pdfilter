package com.jblindley.pdfilter;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class PdfFileBuilder {

  public static void write(List<PDPage> pages, File outputFile) throws COSVisitorException, IOException {
    clobberExistingFile(outputFile);
    PDDocument doc = buildPDDocument(pages);
    writeDocToFile(doc, outputFile);
  }

  private static void writeDocToFile(PDDocument doc, File outputFile) throws COSVisitorException, IOException {
    doc.save(outputFile);
  }


  private static PDDocument buildPDDocument(List<PDPage> pages) {
    PDDocument result = new PDDocument();

    for (PDPage p : pages) {
      result.addPage(p);
    }
    
    return result;
  }


  private static void clobberExistingFile(File outputFile) {
    if (outputFile.exists()) {
      outputFile.delete();
    }
  }

}
