package com.jblindley.pdfilter.ui.desktop;

import java.awt.FlowLayout;

import javax.swing.JPanel;

public class PDFilterPanel extends JPanel {

  private static final long serialVersionUID = 1L;

  public PDFilterPanel() {
    setLayout(new FlowLayout(FlowLayout.LEFT));
    configureControls();
  }

  private void configureControls() {
    add(new PdfFilePicker("Source File:"));
    add(new PdfFilePicker("Result File:"));
  }

}
