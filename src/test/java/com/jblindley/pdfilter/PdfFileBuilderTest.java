package com.jblindley.pdfilter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PdfFileBuilderTest {

  private static final String TEST_FILE_NAME = "PhoneRecords.pdf";

  private File                resultFile;

  @Before
  public void setup() {
    resultFile = new File(UUID.randomUUID().toString() + ".pdf");
  }

  @After
  public void teardown() {
    resultFile.delete();
  }

  @Test
  public void writePages() throws IOException, COSVisitorException {
    InputStream in = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE_NAME);
    PDDocument doc = PDDocument.load(in);
    in.close();

    PdfPageSelector pageSelector = PdfPageSelector.getInstance(doc);
    List<PDPage> pages = pageSelector.select("01/02/11");

    assertFalse(resultFile.exists());

    PdfFileBuilder.write(pages, resultFile);

    assertTrue(resultFile.exists());
  }
}
