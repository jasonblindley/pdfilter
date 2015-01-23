package com.jblindley.pdfilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.util.PDFTextStripper;

public class PdfPageSelector {

  public static final PdfPageSelector getInstance(PDDocument doc) throws IOException {
    return new PdfPageSelector(doc);
  }

  private final PDDocument      doc;
  private final PDFTextStripper textStripper;

  private PdfPageSelector(PDDocument doc) throws IOException {
    this.doc = doc;
    textStripper = new PDFTextStripper();
  }

  public List<PDPage> select(String searchString) throws IOException {
    List<PDPage> result = new ArrayList<PDPage>();
    List pages = doc.getDocumentCatalog().getAllPages();

    for (int i = 0; i < pages.size(); i++) {
      PDPage page = (PDPage) pages.get(i);

      textStripper.setStartPage(i + 1);
      textStripper.setEndPage(i + 1);

      textStripper.resetEngine();
      String text = textStripper.getText(doc);

      if (text.toLowerCase().contains(searchString.toLowerCase())) {
        stampOriginalPageNumber(page, i + 1, pages.size());
        result.add(page);
      }
    }

    return result;
  }

  private void stampOriginalPageNumber(PDPage page, int pageNumber, int totalPages) throws IOException {
    PDRectangle pageSize = page.findMediaBox();
    PDFont font = PDType1Font.HELVETICA_BOLD;
    float fontSize = 12.0f;
    String text = "Original page: " + pageNumber + " of " + totalPages;
    float stringWidth = font.getStringWidth(text);
    float centeredPosition = (pageSize.getWidth() - (stringWidth * fontSize) / 1000f) / 2f;

    PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true);

    contentStream.beginText();
    contentStream.setFont(font, fontSize);
    contentStream.moveTextPositionByAmount(10, 10);
    contentStream.drawString(text);
    contentStream.endText();
    contentStream.close();
  }
}
