/*
 * GWT-Ext Widget Library
 * Copyright(c) 2007-2008, GWT-Ext.
 * licensing@gwt-ext.com
 * 
 * http://www.gwt-ext.com/license
 */
package rent.client;

import rent.client.paneles.PanelManager;

import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Node;
import com.gwtext.client.data.NodeTraversalCallback;
import com.gwtext.client.data.ObjectFieldDef;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.util.DelayedTask;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.tree.TreeFilter;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;

public class MenuManager {

    private static Store store;

    private TreeFilter treeFilter;
    private TreePanel treePanel;
    private DelayedTask delayedTask = new DelayedTask();

    public Panel getAccordionNav() {
        Panel accordion = new Panel();
        accordion.setTitle("Accordion");
        accordion.setLayout(new AccordionLayout(true));

        Store store = getStore();

        Record[] records = store.getRecords();
        for (int i = 0; i < records.length; i++) {
            Record record = records[i];

            String id = record.getAsString("id");
            final String category = record.getAsString("category");
            String title = record.getAsString("title");
            final String iconCls = record.getAsString("iconCls");

            String thumbnail = record.getAsString("thumbnail");
            String qtip = record.getAsString("qtip");

            final PanelManager panel = (PanelManager) record.getAsObject("screen");

            if (category == null) {
                Panel categoryPanel = new Panel();
                categoryPanel.setAutoScroll(true);
                categoryPanel.setLayout(new FitLayout());
                categoryPanel.setId(id + "-acc");
                categoryPanel.setTitle(title);
                categoryPanel.setIconCls(iconCls);
                accordion.add(categoryPanel);
            } else {
                Panel categoryPanel = (Panel) accordion.findByID(category + "-acc");
                TreePanel treePanel = (TreePanel) categoryPanel.findByID(category + "-acc-tree");
                TreeNode root = null;
                if (treePanel == null) {
                    treePanel = new TreePanel();
                    treePanel.setAutoScroll(true);
                    treePanel.setId(category + "-acc-tree");
                    treePanel.setRootVisible(false);
                    root = new TreeNode();
                    treePanel.setRootNode(root);
                    categoryPanel.add(treePanel);
                } else {
                    root = treePanel.getRootNode();
                }

                TreeNode node = new TreeNode();
                node.setText(title);
                node.setId(id);
                if (iconCls != null) node.setIconCls(iconCls);
                if (qtip != null) node.setTooltip(qtip);
                root.appendChild(node);

                //FIXME
            }
        }

        return accordion;
    }

   

    
    public TreePanel getTreeNav() {
        treePanel = new TreePanel();
        treePanel.setTitle("Vista arbol");
        treePanel.setId("nav-tree");
        treePanel.setWidth(180);
        treePanel.setCollapsible(true);
        treePanel.setAnimate(true);
        treePanel.setEnableDD(false);
        treePanel.setAutoScroll(true);
        treePanel.setContainerScroll(true);
        treePanel.setRootVisible(false);
        treePanel.setBorder(false);
        //treePanel.setTopToolbar(getFilterToolbar());

        TreeNode root = new TreeNode("Explorador de Opciones");
        treePanel.setRootNode(root);

        Store store = getStore();

        Record[] records = store.getRecords();
        for (int i = 0; i < records.length; i++) {
            Record record = records[i];

            String id = record.getAsString("id");
            final String category = record.getAsString("category");
            String title = record.getAsString("title");
            final String iconCls = record.getAsString("iconCls");

            String thumbnail = record.getAsString("thumbnail");
            String qtip = record.getAsString("qtip");

            final PanelManager panel = (PanelManager) record.getAsObject("screen");

            TreeNode node = new TreeNode(title);
            node.setId(id);
            if (iconCls != null) node.setIconCls(iconCls);
            if (qtip != null) node.setTooltip(qtip);
            if (category == null || category.equals("")) {
                root.appendChild(node);
            } else {
                Node categoryNode = root.findChildBy(new NodeTraversalCallback() {
                    public boolean execute(Node node) {
                        return node.getId().equals(category);
                    }
                });

                if (categoryNode != null) {
                    categoryNode.appendChild(node);
                }
            }
           //FIXME
        }
        treeFilter = new TreeFilter(treePanel);
        return treePanel;
    }

  
    public static Store getStore() {
        if (store == null) {
            MemoryProxy proxy = new MemoryProxy(getData());

            RecordDef recordDef = new RecordDef(new FieldDef[]{
                    new StringFieldDef("id"),
                    new StringFieldDef("category"),
                    new StringFieldDef("title"),
                    new StringFieldDef("iconCls"),
                    new StringFieldDef("thumbnail"),
                    new StringFieldDef("qtip"),
                    new ObjectFieldDef("screen")
            });

            ArrayReader reader = new ArrayReader(0, recordDef);
            store = new Store(proxy, reader);
            store.load();
        }
        return store;
    }

    private static Object[][] getData() {
        return new Object[][]{

                new Object[]{"tree-category", null, "Tree", "tree-category-icon", null, null, null},
                new Object[]{"editableTree", "tree-category", "Editable Tree", null, "images/thumbnails/tree/editable.gif", null,null},
                new Object[]{"checkboxTree", "tree-category", "Checkbox Tree", null, null, null, null},
                new Object[]{"sortMultiSelectTree", "tree-category", "Sort &amp; Multi-Select", null, null, null, null},
                new Object[]{"twoTrees", "tree-category", "Tree to Tree Drag &amp; Drop", null, "images/thumbnails/tree/two-tree.gif", null, null},
                new Object[]{"gridDD", "tree-category", "Grid - Tree Drag &amp; Drop", null, "images/thumbnails/tree/grid-tree.gif", null, null},

                new Object[]{"buttons-category", null, "Buttons", "buttons-category-icon", null, null, null},
                new Object[]{"buttons", "buttons-category", "Simple Buttons", null, null, null, null},
                new Object[]{"cycleButton", "buttons-category", "Cycle Button", null, null, null, null},
                new Object[]{"menuButton", "buttons-category", "Menu Button", null, "images/thumbnails/button/button-menu.gif", null, null},
                new Object[]{"toggleButtons", "buttons-category", "Toggle Buttons", null, null, null, null},
      };
    }
}
