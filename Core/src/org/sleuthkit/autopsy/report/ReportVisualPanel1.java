/*
 * Autopsy Forensic Browser
 *
 * Copyright 2012 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.report;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import org.openide.util.Lookup;
import org.sleuthkit.autopsy.coreutils.Logger;

public final class ReportVisualPanel1 extends JPanel {
    private static final Logger logger = Logger.getLogger(ReportVisualPanel1.class.getName());
    private ReportWizardPanel1 wizPanel;
    
    private Map<TableReportModule, Boolean> tableModuleStates = new LinkedHashMap<TableReportModule, Boolean>();
    private Map<GeneralReportModule, Boolean> generalModuleStates = new LinkedHashMap<GeneralReportModule, Boolean>();
    private Map<FileReportModule, Boolean> fileListModuleStates = new LinkedHashMap<FileReportModule, Boolean>();
    private List<TableReportModule> tableModules = new ArrayList<TableReportModule>();
    private List<GeneralReportModule> generalModules = new ArrayList<GeneralReportModule>();
    private List<FileReportModule> fileListModules = new ArrayList<FileReportModule>();
    
    private ModulesTableModel modulesModel;
    private ModuleSelectionListener modulesListener;

    /**
     * Creates new form ReportVisualPanel1
     */
    public ReportVisualPanel1(ReportWizardPanel1 wizPanel) {
        initComponents();
        initModules();
        this.wizPanel = wizPanel;
        configurationPanel.setLayout(new BorderLayout());
        descriptionTextPane.setEditable(false);
        modulesTable.setRowSelectionInterval(0, 0);
    }
    
    // Initialize the list of ReportModules
    private void initModules() {
        for(TableReportModule module : Lookup.getDefault().lookupAll(TableReportModule.class)) {
            if(module.getName().equals("HTML")) {
                tableModuleStates.put(module, Boolean.TRUE);
            } else {
                tableModuleStates.put(module, Boolean.FALSE);
            }
            tableModules.add(module);
        }
        for(GeneralReportModule module : Lookup.getDefault().lookupAll(GeneralReportModule.class)) {
            generalModuleStates.put(module, Boolean.FALSE);
            generalModules.add(module);
        }
        for(FileReportModule module : Lookup.getDefault().lookupAll(FileReportModule.class)) {
            fileListModuleStates.put(module, Boolean.FALSE);
            fileListModules.add(module);
        }
        
        modulesModel = new ModulesTableModel();
        modulesListener = new ModuleSelectionListener();
        modulesTable.setModel(modulesModel);
        modulesTable.getSelectionModel().addListSelectionListener(modulesListener);
        modulesTable.setTableHeader(null);
        modulesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modulesTable.setRowHeight(modulesTable.getRowHeight() + 5);
        
        int width = modulesScrollPane.getPreferredSize().width;
        for (int i = 0; i < modulesTable.getColumnCount(); i++) {
            TableColumn column = modulesTable.getColumnModel().getColumn(i);
            if (i == 0) {
                column.setPreferredWidth(((int) (width * 0.15)));
            } else {
                column.setPreferredWidth(((int) (width * 0.84)));
            }
        }
    }

    @Override
    public String getName() {
        return "Select and Configure Report Modules";
    }
    
    /**
     * @return the enabled/disabled states of all TableReportModules
     */
    Map<TableReportModule, Boolean> getTableModuleStates() {
        return tableModuleStates;
    }
    
    /**
     * @return the enabled/disabled states of all GeneralReportModules
     */
    Map<GeneralReportModule, Boolean> getGeneralModuleStates() {
        return generalModuleStates;
    }
    
    /**
     * @return the enabled/disabled states of all FileListReportModules
     */
    Map<FileReportModule, Boolean> getFileListModuleStates() {
        return fileListModuleStates;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modulesScrollPane = new javax.swing.JScrollPane();
        modulesTable = new javax.swing.JTable();
        reportModulesLabel = new javax.swing.JLabel();
        configurationPanel = new javax.swing.JPanel();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextPane = new javax.swing.JTextPane();

        setPreferredSize(new java.awt.Dimension(650, 250));

        modulesTable.setBackground(new java.awt.Color(240, 240, 240));
        modulesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        modulesTable.setShowHorizontalLines(false);
        modulesTable.setShowVerticalLines(false);
        modulesScrollPane.setViewportView(modulesTable);

        org.openide.awt.Mnemonics.setLocalizedText(reportModulesLabel, org.openide.util.NbBundle.getMessage(ReportVisualPanel1.class, "ReportVisualPanel1.reportModulesLabel.text")); // NOI18N

        configurationPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(125, 125, 125)));

        javax.swing.GroupLayout configurationPanelLayout = new javax.swing.GroupLayout(configurationPanel);
        configurationPanel.setLayout(configurationPanelLayout);
        configurationPanelLayout.setHorizontalGroup(
            configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 432, Short.MAX_VALUE)
        );
        configurationPanelLayout.setVerticalGroup(
            configurationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        descriptionScrollPane.setBorder(null);

        descriptionTextPane.setBackground(new java.awt.Color(240, 240, 240));
        descriptionTextPane.setBorder(null);
        descriptionScrollPane.setViewportView(descriptionTextPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reportModulesLabel)
                    .addComponent(modulesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(configurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionScrollPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(reportModulesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modulesScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(configurationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel configurationPanel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextPane descriptionTextPane;
    private javax.swing.JScrollPane modulesScrollPane;
    private javax.swing.JTable modulesTable;
    private javax.swing.JLabel reportModulesLabel;
    // End of variables declaration//GEN-END:variables

    private class ModulesTableModel extends AbstractTableModel {

        @Override
        public int getRowCount() {
           return tableModules.size() + generalModules.size() + fileListModules.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ReportModule module;
            if (rowIndex < tableModules.size()) {
                module = tableModules.get(rowIndex);
            } else if (rowIndex >= tableModules.size() && rowIndex < tableModules.size() + generalModules.size()){
                module = generalModules.get(rowIndex - tableModules.size());
            } else {
                module = fileListModules.get(rowIndex - tableModules.size() - generalModules.size());
            }
            if (columnIndex == 0 && rowIndex < tableModules.size()) {
               return tableModuleStates.get(tableModules.get(rowIndex));
            } else if (columnIndex == 0 && rowIndex >= tableModules.size() && rowIndex < tableModules.size() + generalModules.size()) {
                return generalModuleStates.get(generalModules.get(rowIndex - tableModules.size()));
            } else if (columnIndex == 0 && rowIndex >= tableModules.size() + generalModules.size()) {
                return fileListModuleStates.get(fileListModules.get(rowIndex - tableModules.size() - generalModules.size()));
            } else {
                return module.getName();
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 0;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if (columnIndex == 0 && rowIndex < tableModules.size()) {
                tableModuleStates.put(tableModules.get(rowIndex), (Boolean) aValue);
            } else if (columnIndex == 0 && rowIndex >= tableModules.size() && rowIndex < tableModules.size() + generalModules.size()) {
                generalModuleStates.put(generalModules.get(rowIndex - tableModules.size()), (Boolean) aValue);
            } else if (columnIndex == 0 && rowIndex >= tableModules.size() + generalModules.size()) {
                fileListModuleStates.put(fileListModules.get(rowIndex - tableModules.size() - generalModules.size()), (Boolean) aValue);
            }
            // Check if there are any TableReportModules enabled
            boolean tableModuleEnabled = false;
            for (Entry<TableReportModule, Boolean> module : tableModuleStates.entrySet()) {
                if (module.getValue()) {
                    tableModuleEnabled = true;
                }
            }
            boolean generalModuleEnabled = false;
            for (Entry<GeneralReportModule, Boolean> module : generalModuleStates.entrySet()) {
                if (module.getValue()) {
                    generalModuleEnabled = true;
                }
            }
            boolean fileListModuleEnabled = false;
            for (Entry<FileReportModule, Boolean> module : fileListModuleStates.entrySet()) {
                if (module.getValue()) {
                    fileListModuleEnabled = true;
                }
            }
            if(tableModuleEnabled || fileListModuleEnabled) {
                wizPanel.setNext(true);
                wizPanel.setFinish(false);
            } else if(generalModuleEnabled) {
                wizPanel.setFinish(true);
                wizPanel.setNext(false);
            } else {
                wizPanel.setFinish(false);
                wizPanel.setNext(false);
            }
        }

        @Override
        public Class<?> getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }
    
    private class ModuleSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            configurationPanel.removeAll();
            int rowIndex = modulesTable.getSelectedRow();
            if (rowIndex < tableModules.size()) {
                configurationPanel.add(new DefaultReportConfigurationPanel(), BorderLayout.CENTER);
                descriptionTextPane.setText(tableModules.get(rowIndex).getDescription());
            } else if (rowIndex >= tableModules.size() && rowIndex < tableModules.size() + generalModules.size()) {
                GeneralReportModule module = generalModules.get(rowIndex - tableModules.size());
                JPanel panel = module.getConfigurationPanel();
                descriptionTextPane.setText(module.getDescription());
                if (panel != null) {
                    configurationPanel.add(panel, BorderLayout.CENTER);
                }
            } else {
                configurationPanel.add(new DefaultReportConfigurationPanel(), BorderLayout.CENTER);
                descriptionTextPane.setText(fileListModules.get(rowIndex - tableModules.size() - generalModules.size()).getDescription());
            }
            configurationPanel.revalidate();
            configurationPanel.repaint();
        }
        
    }

}
