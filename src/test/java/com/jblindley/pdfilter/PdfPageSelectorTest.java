package com.jblindley.pdfilter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.Before;
import org.junit.Test;

public class PdfPageSelectorTest {

  private static final String TEST_FILE_NAME = "PhoneRecords.pdf";

  private PdfPageSelector     instance;

  private PDDocument          doc;

  @Before
  public void setUp() throws IOException {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME);
    doc = PDDocument.load(in);
    in.close();
    instance = PdfPageSelector.getInstance(doc);
  }

  @Test
  public void checkReturnedPageCount() throws IOException {
    List<PDPage> pages = instance.select("01/02/11");

    assertEquals(5, pages.size());
  }
}
