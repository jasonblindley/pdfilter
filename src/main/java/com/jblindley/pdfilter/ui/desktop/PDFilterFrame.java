package com.jblindley.pdfilter.ui.desktop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class PDFilterFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  public PDFilterFrame() {
    configureDisplay();
    configureListeners();
    configureContent();
  }

  private void configureContent() {
    getContentPane().add(new PDFilterPanel());
  }

  private void configureListeners() {
    addWindowListener(new PDFilterFrameWindowAdapter(this));
  }

  private void configureDisplay() {
    setTitle("PDFilter");
    setSize(500, 300);
    setLocation(200, 200);
  }

  protected void close() {
    System.exit(0);
  }
}

class PDFilterFrameWindowAdapter extends WindowAdapter {

  private final PDFilterFrame frame;

  PDFilterFrameWindowAdapter(PDFilterFrame frame) {
    this.frame = frame;
  }

  @Override
  public void windowClosing(WindowEvent e) {
    super.windowClosing(e);
    frame.close();
  }
}
