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
import com.github.naoghuman.fw.background.element.ElementClip;
import com.github.naoghuman.fw.background.element.ElementColor;
import com.github.naoghuman.fw.background.element.ElementImage;
import com.github.naoghuman.fw.background.element.ElementText;
import com.github.naoghuman.lib.action.core.ActionHandlerFacade;
import com.github.naoghuman.lib.action.core.TransferDataBuilder;
import com.github.naoghuman.lib.fxml.core.FXMLController;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

/**
 *
 * @author  Naoghuman
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public class NavigationPaletteController extends FXMLController implements Initializable {
    
    @FXML private ListView<Element> navigationPalette;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "NavigationPaletteController#initialize(URL, ResourceBundle)"); // NOI18N
        
        this.initializeNavigationPalette();
    }
    
    private void initializeNavigationPalette() {
        LoggerFacade.getDefault().info(this.getClass(), "NavigationPaletteController#initializeNavigationPalette()"); // NOI18N
        
        final ObservableList<Element> elements = FXCollections.observableArrayList();
        elements.addAll(
//                new ElementBackground(),
                new ElementClip(),
                new ElementColor(),
                new ElementImage(),
                new ElementText()
        );
        navigationPalette.setItems(elements);
        
        navigationPalette.setCellFactory(listView -> {
            final ListCell<Element> listCell = new ListCell<Element>() {
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
            
            listCell.setOnDragDetected(event -> {
                if (!listCell.isEmpty()) {
                    final Dragboard        dragboard        = listCell.startDragAndDrop(TransferMode.COPY);
                    final ClipboardContent clipboardContent = new ClipboardContent();
                    clipboardContent.put(Element.DATA_FORMAT, listCell.getItem());
                    dragboard.setContent(clipboardContent);
                }
                
                event.consume();
            });
            
            listCell.setOnMousePressed(event -> {
                if (
                        !listCell.isEmpty()
                        && listCell.getItem() != null
                ) {
                    ActionHandlerFacade.getDefault().handle(TransferDataBuilder.create()
                            .actionId(NavigationDetailsController.ON_ACTION__UPDATE_DETAILS_FOR_PALETTE)
                            .objectValue(listCell.getItem())
                            .build()
                    );
                }
            });
            
            return listCell;
        });
    }
    
}
