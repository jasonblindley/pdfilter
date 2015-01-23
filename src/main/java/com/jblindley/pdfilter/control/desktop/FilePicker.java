package com.jblindley.pdfilter.control.desktop;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FilePicker extends JPanel {

  private static final long   serialVersionUID            = 1L;

  private static final String DEFAULT_BROWSE_BUTTON_LABEL = "Browse...";
  private static final String DEFAULT_SELECT_BUTTON_LABEL = "Select";

  private final JTextField    textField;
  private final JButton       browseButton;

  private final JFileChooser  fileChooser;

  private final String        selectButtonText;

  public FilePicker(String textFieldLabel) {
    this(textFieldLabel, DEFAULT_BROWSE_BUTTON_LABEL, DEFAULT_SELECT_BUTTON_LABEL);
  }

  public FilePicker(String textFieldLabel, String browseButtonLabel, String selectButtonText) {
    this.selectButtonText = selectButtonText;

    setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));

    fileChooser = new JFileChooser();
    textField = new JTextField(5);
    browseButton = new JButton(browseButtonLabel);

    browseButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent evt) {
        browseButtonClicked();
      }
    });

    add(new JLabel(textFieldLabel));
    add(textField);
    add(browseButton);

  }

  private void browseButtonClicked() {
    if (getFileChooser().showDialog(this, selectButtonText) == JFileChooser.APPROVE_OPTION) {
      textField.setText(getFileChooser().getSelectedFile().getAbsolutePath());
    }
  }

  public void setFileTypeFilter(String extension, String description) {
    getFileChooser().setFileFilter(new FileTypeFilter(extension, description));
  }

  protected JFileChooser getFileChooser() {
    return fileChooser;
  }

}