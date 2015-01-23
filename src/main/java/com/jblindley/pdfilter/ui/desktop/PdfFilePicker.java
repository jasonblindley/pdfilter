package com.jblindley.pdfilter.ui.desktop;

import com.jblindley.pdfilter.control.desktop.FilePicker;

public class PdfFilePicker extends FilePicker {

  private static final long serialVersionUID = 1L;

  public PdfFilePicker(String textFieldLabel) {
    super(textFieldLabel);
    setFileTypeFilter("pdf", "PDF Files");
    getFileChooser().setAcceptAllFileFilterUsed(false);
  }

}
