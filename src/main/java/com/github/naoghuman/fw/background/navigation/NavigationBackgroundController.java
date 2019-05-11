/*
 * Copyright (C) 2019 Naoghuman's dream
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.naoghuman.fw.background.navigation;

import com.github.naoghuman.fw.background.element.Element;
import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.action.core.TransferDataBuilder;
import com.github.naoghuman.lib.fxml.core.FXMLController;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 *
 * @author  Naoghuman
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public class NavigationBackgroundController extends FXMLController implements Initializable {
    
    @FXML private TreeView<Element> navigationBackground;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "NavigationBackgroundController#initialize(URL, ResourceBundle)"); // NOI18N
        
        this.initializeTreeView();
    }

    private void initializeTreeView() {
        LoggerFacade.getDefault().info(this.getClass(), "NavigationBackgroundController#initializeTreeView()"); // NOI18N
        
        final TreeItem<Element> rootItem = new TreeItem<>(Element.ROOT_ELEMENT__BACKGROUND);
        rootItem.setExpanded(true);
        navigationBackground.setRoot(rootItem);
        
        navigationBackground.setCellFactory(treeView -> {
            final TreeCell<Element> treeCell = new TreeCell<Element>() {
                @Override
                protected void updateItem(final Element element, final boolean empty) {
                    super.updateItem(element, empty);
                    
                    if(!empty && element != null) {
//                        this.setGraphic(null);
                        this.setText(element.getTitle());
                    } else {
//                        this.setGraphic(null);
                        this.setText(null);
                    }
                }
            };
            
            treeCell.setOnDragEntered(event -> {
                if (!treeCell.isEmpty()) {
                    final Dragboard dragboard = event.getDragboard();
                    if (dragboard.hasContent(Element.DATA_FORMAT)) {
                        final Element element = (Element) dragboard.getContent(Element.DATA_FORMAT);
                        if (element.getParentTypes().contains(treeCell.getItem().getType())) {
                            treeCell.setStyle("-fx-background-color:LIGHTGREEN;"); // NOI18N
                        } else {
                            treeCell.setStyle("-fx-background-color:CRIMSON;"); // NOI18N
                        }
                    }
                }
            });
            
            treeCell.setOnDragExited(event -> {
                if (
                        !treeCell.isEmpty()
                        && !treeCell.getStyle().isEmpty()
                ) {
                    treeCell.setStyle(""); // NOI18N
                }
            });
            
            treeCell.setOnDragOver(event -> {
                if (!treeCell.isEmpty()) {
                    final Dragboard dragboard = event.getDragboard();
                    if (dragboard.hasContent(Element.DATA_FORMAT)) {
                        event.acceptTransferModes(TransferMode.COPY);
                    }
                }
                
                event.consume();
            });
            
            treeCell.setOnDragDropped(event -> {
                boolean success = false;
                if (!treeCell.isEmpty()) {
                    final Dragboard dragboard = event.getDragboard();
                    if (dragboard.hasContent(Element.DATA_FORMAT)) {
                        final Element element = (Element) dragboard.getContent(Element.DATA_FORMAT);
                        if (element.getParentTypes().contains(treeCell.getItem().getType())) {
                            treeCell.getTreeItem().getChildren().add(new TreeItem<>(element));
                            treeCell.getTreeItem().setExpanded(true);

                            success = true;
                        }
                    }

                    event.setDropCompleted(success);
                }
                
                event.consume();
            });
            treeCell.setOnMousePressed(event -> {
                if (
                        !treeCell.isEmpty()
                        && treeCell.getItem() != null
                ) {
                    ActionHandlerFacade.getDefault().handle(TransferDataBuilder.create()
                            .actionId(NavigationDetailsController.ON_ACTION__UPDATE_DETAILS_FOR_BACKGROUND)
                            .objectValue(treeCell.getItem())
                            .build()
                    );
                }
            });
            
            return treeCell;
        });
    }
    
}
