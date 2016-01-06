/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package richtercloud.document.scanner.gui;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.swing.JComponent;
import org.apache.commons.lang3.tuple.Pair;
import richtercloud.document.scanner.components.OCRResultPanelFetcher;
import richtercloud.document.scanner.components.ScanResultPanelFetcher;
import richtercloud.document.scanner.ocr.OCREngine;
import richtercloud.document.scanner.setter.ValueSetter;
import richtercloud.reflection.form.builder.ClassAnnotationHandler;
import richtercloud.reflection.form.builder.fieldhandler.FieldHandler;
import richtercloud.reflection.form.builder.fieldhandler.FieldUpdateEvent;
import richtercloud.reflection.form.builder.components.AmountMoneyCurrencyStorage;
import richtercloud.reflection.form.builder.components.AmountMoneyUsageStatisticsStorage;
import richtercloud.reflection.form.builder.fieldhandler.FieldAnnotationHandler;
import richtercloud.reflection.form.builder.message.MessageHandler;

/**
 *
 * @author richter
 */
public class DocumentTab extends javax.swing.JPanel {

    private static final long serialVersionUID = 1L;
    private String title;
    private DocumentForm documentForm;
    private OCRSelectComponent oCRSelectComponent;
    private OCREngine oCREngine;

    public DocumentTab(String title,
            OCRSelectComponent oCRSelectComponent,
            OCREngine oCREngine,
            Set<Class<?>> entityClasses,
            Class<?> primaryClassSelection,
            FieldHandler fieldHandler,
            EntityManager entityManager,
            List<Pair<Class<? extends Annotation>,FieldAnnotationHandler>> fieldAnnotationMapping,
            List<Pair<Class<? extends Annotation>,ClassAnnotationHandler<Object,FieldUpdateEvent<Object>>>> classAnnotationMapping,
            OCRResultPanelFetcher oCRResultPanelFetcher,
            ScanResultPanelFetcher scanResultPanelFetcher,
            AmountMoneyUsageStatisticsStorage amountMoneyUsageStatisticsStorage,
            AmountMoneyCurrencyStorage amountMoneyAdditionalCurrencyStorage,
            MessageHandler messageHandler) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this(title,
                oCRSelectComponent,
                oCREngine,
                entityClasses,
                primaryClassSelection,
                fieldHandler,
                DocumentScanner.VALUE_SETTER_MAPPING_DEFAULT,
                entityManager,
                fieldAnnotationMapping,
                classAnnotationMapping,
                oCRResultPanelFetcher,
                scanResultPanelFetcher,
                amountMoneyUsageStatisticsStorage,
                amountMoneyAdditionalCurrencyStorage,
                messageHandler);
    }

    public DocumentTab(String title,
            OCRSelectComponent oCRSelectComponent,
            OCREngine oCREngine,
            Set<Class<?>> entityClasses,
            Class<?> primaryClassSelection,
            FieldHandler fieldHandler,
            Map<Class<? extends JComponent>, ValueSetter<?>> valueSetterMapping,
            EntityManager entityManager,
            List<Pair<Class<? extends Annotation>,FieldAnnotationHandler>> fieldAnnotationMapping,
            List<Pair<Class<? extends Annotation>,ClassAnnotationHandler<Object,FieldUpdateEvent<Object>>>> classAnnotationMapping,
            OCRResultPanelFetcher oCRResultPanelFetcher,
            ScanResultPanelFetcher scanResultPanelFetcher,
            AmountMoneyUsageStatisticsStorage amountMoneyUsageStatisticsStorage,
            AmountMoneyCurrencyStorage amountMoneyAdditionalCurrencyStorage,
            MessageHandler messageHandler) throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.title = title;
        this.oCRSelectComponent = oCRSelectComponent;
        this.oCREngine = oCREngine;
        this.documentForm = new DocumentForm(entityClasses,
                primaryClassSelection,
                fieldHandler,
                valueSetterMapping,
                entityManager,
                oCRResultPanelFetcher,
                scanResultPanelFetcher,
                amountMoneyUsageStatisticsStorage,
                amountMoneyAdditionalCurrencyStorage,
                messageHandler);
        this.initComponents();
        this.imageScrollPane.getViewport().setView(oCRSelectComponent);
        this.splitPane.setRightComponent(this.documentForm);
        this.splitPane.validate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitPane = new javax.swing.JSplitPane();
        imageScrollPane = new javax.swing.JScrollPane();

        splitPane.setDividerLocation(350);
        splitPane.setLeftComponent(imageScrollPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(splitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public String getTitle() {
        return this.title;
    }

    protected void setTitle(String title) {
        this.title = title;
    }

    public DocumentForm getDocumentForm() {
        return this.documentForm;
    }

    public OCRSelectComponent getoCRSelectComponent() {
        return this.oCRSelectComponent;
    }

    public void setoCRSelectComponent(OCRSelectComponent oCRSelectComponent) {
        this.oCRSelectComponent = oCRSelectComponent;
        this.imageScrollPane.getViewport().setView(this.oCRSelectComponent);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane imageScrollPane;
    private javax.swing.JSplitPane splitPane;
    // End of variables declaration//GEN-END:variables

}
