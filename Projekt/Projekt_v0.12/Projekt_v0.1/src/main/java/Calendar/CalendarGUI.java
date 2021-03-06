package Calendar; /**
 * Created by Sylwek on 2016-12-08.
 */

import Controlers.StateController;
import Types.Days;
import Types.HoursPreference;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class CalendarGUI extends JFrame {
    private JPanel panel1;
    private JButton button1;
    private JTable mondayJTable;
    private JTable tuesdayJTable;
    private JTable wednesdayJTable;
    private JTable thursdayJTable;
    private JTable fridayJTable;
    private JTable[] tables;// = {mondayJTable, tuesdayJTable, wednesdayJTable, thursdayJTable, fridayJTable};

    private HoursPreference preferences = new HoursPreference();
    private StateController stateController = StateController.getStateControler();

    public CalendarGUI(JFrame contextFrame) {

        tables = Arrays.copyOf(new JTable[]{mondayJTable, tuesdayJTable, wednesdayJTable, thursdayJTable, fridayJTable}, 5);
        start();
        String[] colNames = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int i = 0;
        System.out.println(mondayJTable + " " + tables.length);
        for (int j = 0; j < tables.length; j++) {
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            String[] rows = {"7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
            model.addColumn(colNames[i++], rows);
            DefaultTableCellRenderer defaultTableCellRenderer = new DefaultTableCellRenderer();
            defaultTableCellRenderer.setHorizontalAlignment(JLabel.CENTER);
            tables[j].setDefaultRenderer(String.class, defaultTableCellRenderer);
            tables[j].setModel(model);
        }

        loadFromPreference();


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSelection();
                contextFrame.setVisible(true);
                dispose();
            }
        });
    }

    public void loadSelection() {
        Days[] days = {Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY};

        int i = 0;
        for (JTable table : tables) {
            int[] selected = table.getSelectedRows();
            if (selected.length > 0) {
                Boolean[] selectedBinaryField = new Boolean[15];
                Arrays.fill(selectedBinaryField, false);
                for (int j = 0; j < selected.length; ++j)
                    selectedBinaryField[selected[j]] = true;

                preferences.addDay(days[i], selectedBinaryField);
            }

            //System.out.println(table.getColumnName(0) + Arrays.toString(selected));
            ++i;
        }

        stateController.addHours(preferences);
        //System.out.println(StateController.preference);
        setVisible(false);


    }

    public void start() {

        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 350));
        pack();

        setVisible(true);

    }

    private void loadFromPreference() {
        Days[] days = {Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, Days.FRIDAY};
        HoursPreference preference = stateController.get();


        for (int i = 0; i < tables.length; i++) {

            if (preference != null && preference.isDay(days[i])) {
                Boolean[] selections = preference.getDay(days[i]);
                for (int l = 0; l < selections.length; l++) {

                    if (selections[l] != false) tables[i].addRowSelectionInterval(l, l);

                }
            }


            //System.out.println(table.getColumnName(0) + Arrays.toString(selected));

        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 8, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, 1, null, null, null, 0, false));
        mondayJTable = new JTable();
        mondayJTable.setPreferredScrollableViewportSize(new Dimension(200, 240));
        scrollPane1.setViewportView(mondayJTable);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tuesdayJTable = new JTable();
        tuesdayJTable.setPreferredScrollableViewportSize(new Dimension(200, 240));
        scrollPane2.setViewportView(tuesdayJTable);
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(1, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        button1 = new JButton();
        button1.setText("OK");
        panel1.add(button1, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(3, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel1.add(scrollPane3, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wednesdayJTable = new JTable();
        wednesdayJTable.setPreferredScrollableViewportSize(new Dimension(200, 240));
        scrollPane3.setViewportView(wednesdayJTable);
        final JScrollPane scrollPane4 = new JScrollPane();
        panel1.add(scrollPane4, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        thursdayJTable = new JTable();
        thursdayJTable.setPreferredScrollableViewportSize(new Dimension(200, 240));
        scrollPane4.setViewportView(thursdayJTable);
        final JScrollPane scrollPane5 = new JScrollPane();
        panel1.add(scrollPane5, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        fridayJTable = new JTable();
        fridayJTable.setPreferredScrollableViewportSize(new Dimension(200, 240));
        scrollPane5.setViewportView(fridayJTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}

