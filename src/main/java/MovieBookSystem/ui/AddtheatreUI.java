package MovieBookSystem.ui;

import MovieBookSystem.dao.AddTheatreDAO;
import model.AddTheatre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AddtheatreUI extends JFrame {
    private JTextField txtName;
    private JTextField txtCity;
    private JTextField txtTheatretype;
    private JTextField txtAddress;
    private JTable tblTheatres;
    private DefaultTableModel theatreTableModel;

    public AddtheatreUI() {
        setTitle("Manage Theatres");
        setSize(600, 400);
        setLayout(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        JLabel lblName = new JLabel("Name:");
        txtName = new JTextField();
        JLabel lblAge = new JLabel("City:");
        txtCity = new JTextField();
        JLabel lblTheatretype = new JLabel("Theatretype:");
        txtTheatretype = new JTextField();
        JLabel lblAddress = new JLabel("Address:");
        txtAddress = new JTextField();


        formPanel.add(lblName);
        formPanel.add(txtName);
        formPanel.add(lblAge);
        formPanel.add(txtCity);
        formPanel.add(lblTheatretype);
        formPanel.add(txtTheatretype);
        formPanel.add(lblAddress);
        formPanel.add(txtAddress);


        // Button Panel
        JPanel buttonPanel = new JPanel();
        JButton btnSave = new JButton("Save");
        JButton btnUpdate = new JButton("Update");
        JButton btnDelete = new JButton("Delete");
        JButton btnClear = new JButton("Clear");

        buttonPanel.add(btnSave);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        // Table Panel
        String[] columnNames = {"ID", "Name", "City", "Theatretype", "Address"};
        theatreTableModel = new DefaultTableModel(columnNames, 0);
        tblTheatres = new JTable(theatreTableModel);
        JScrollPane scrollPane = new JScrollPane(tblTheatres);

        // Populate table with existing Theatres
        loadTheatres();

        // Add action listeners
        btnSave.addActionListener(this::saveTheatre);
        btnUpdate.addActionListener(this::updateTheatre);
        btnDelete.addActionListener(this::deleteTheatre);
        btnClear.addActionListener(e -> clearForm());

        tblTheatres.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                loadSelectedTheatre();
            }
        });

        // Add panels to frame
        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Load theatres into the table
    private void loadTheatres() {
        theatreTableModel.setRowCount(0); // Clear the table
        List<AddTheatre> theatres = AddTheatreDAO.getAllTheatre();
        for (AddTheatre theatre : theatres) {
            Object[] row = new Object[]{
                    theatre.getId(),
                    theatre.getName(),
                    theatre.getCity(),
                    theatre.gettheatretype(),
                    theatre.getAddress()
            };
            theatreTableModel.addRow(row);
        }
    }

    // Save a new theatre
    private void saveTheatre(ActionEvent e) {
        AddTheatre theatre = new AddTheatre();
        theatre.setName(txtName.getText());
        theatre.setCity(txtCity.getText());
        theatre.settheatretype(txtTheatretype.getText());
        theatre.setAddress(txtAddress.getText());

        AddTheatreDAO.saveTheatre(theatre);
        JOptionPane.showMessageDialog(this, "Theatre saved successfully!");

        loadTheatres();  // Reload theatres to reflect the new theatre
        clearForm();
    }

    // Update an existing theatre
    private void updateTheatre(ActionEvent e) {
        int selectedRow = tblTheatres.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a theatre to update.");
            return;
        }

        int theatreId = (int) tblTheatres.getValueAt(selectedRow, 0);  // Get the theatre ID from the selected row

        AddTheatre theatre = AddTheatreDAO.getTheatreById(theatreId);
        if (theatre != null) {
            theatre.setName(txtName.getText());
            theatre.setCity(txtCity.getText());

            theatre.settheatretype(txtTheatretype.getText());
            theatre.setAddress(txtAddress.getText());

            AddTheatreDAO.updateTheatre(theatre);
            JOptionPane.showMessageDialog(this, "Theatre updated successfully!");

            loadTheatres();  // Reload theatres to reflect the update
            clearForm();
        } else {
            JOptionPane.showMessageDialog(this, "Theatre not found!");
        }
    }

    // Delete an existing patient
    private void deleteTheatre(ActionEvent e) {
        int selectedRow = tblTheatres.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a theatre to delete.");
            return;
        }

        int theatreId = (int) tblTheatres.getValueAt(selectedRow, 0);  // Get the theatre ID from the selected row
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this theatre?");
        if (confirm == JOptionPane.YES_OPTION) {
            AddTheatreDAO.deleteTheatre(theatreId);
            JOptionPane.showMessageDialog(this, "Theatre deleted successfully!");

            loadTheatres();  // Reload Theatres to reflect the deletion
            clearForm();
        }
    }

    // Clear form fields
    private void clearForm() {
        txtName.setText("");
        txtCity.setText("");
        txtTheatretype.setText("");
        txtAddress.setText("");
        tblTheatres.clearSelection();
    }

    // Load selected theatre details into form
    private void loadSelectedTheatre() {
        int selectedRow = tblTheatres.getSelectedRow();
        if (selectedRow != -1) {
            txtName.setText(tblTheatres.getValueAt(selectedRow, 1).toString());
            txtCity.setText(tblTheatres.getValueAt(selectedRow, 2).toString());
            txtTheatretype.setText(tblTheatres.getValueAt(selectedRow, 3).toString());
            txtAddress.setText(tblTheatres.getValueAt(selectedRow, 4).toString());
        }
    }
}
