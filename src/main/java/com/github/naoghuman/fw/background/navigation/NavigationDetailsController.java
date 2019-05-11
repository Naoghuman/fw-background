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
import com.github.naoghuman.lib.action.core.TransferData;
import com.github.naoghuman.lib.fxml.core.FXMLController;
import com.github.naoghuman.lib.fxml.core.FXMLRegisterable;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

/**
 *
 * @author  Naoghuman
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public class NavigationDetailsController extends FXMLController implements FXMLRegisterable, Initializable {
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public static final String ON_ACTION__UPDATE_DETAILS_FOR_BACKGROUND = "ON_ACTION__UPDATE_DETAILS_FOR_BACKGROUND"; // NO18N
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public static final String ON_ACTION__UPDATE_DETAILS_FOR_PALETTE = "ON_ACTION__UPDATE_DETAILS_FOR_PALETTE"; // NO18N
    
    @FXML private VBox navigationDetails;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        LoggerFacade.getDefault().info(this.getClass(), "NavigationDetailsController#initialize(URL, ResourceBundle)"); // NOI18N
        
        this.register();
    }

    private void onActionUpdateDetailsForBackground(final Element element) {
        LoggerFacade.getDefault().debug(this.getClass(), "NavigationDetailsController#onActionUpdateDetailsForBackground(Element)"); // NOI18N
        
        // validate != null
        
        navigationDetails.getChildren().clear();
        navigationDetails.getChildren().addAll(element.getDetailsComponents());
    }

    private void onActionUpdateDetailsForPalette(final Element element) {
        LoggerFacade.getDefault().debug(this.getClass(), "NavigationDetailsController#onActionUpdateDetails(Element)"); // NOI18N
        
        // validate != null
        
        navigationDetails.getChildren().clear();
        navigationDetails.getChildren().addAll(element.getDetailsDescription());
    }

    @Override
    public void register() {
        LoggerFacade.getDefault().info(this.getClass(), "NavigationDetailsController#register()"); // NOI18N
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__UPDATE_DETAILS_FOR_BACKGROUND,
                eventHandler -> {
                    final TransferData     transferData = (TransferData) eventHandler.getSource();
                    final Optional<Object> object       = transferData.getObject();
                    if (
                            object.isPresent()
                            && object.get() instanceof Element
                    ) {
                        final Element element = (Element) object.get();
                        this.onActionUpdateDetailsForBackground(element);
                    }
                });
        
        ActionHandlerFacade.getDefault().register(
                ON_ACTION__UPDATE_DETAILS_FOR_PALETTE,
                eventHandler -> {
                    final TransferData     transferData = (TransferData) eventHandler.getSource();
                    final Optional<Object> object       = transferData.getObject();
                    if (
                            object.isPresent()
                            && object.get() instanceof Element
                    ) {
                        final Element element = (Element) object.get();
                        this.onActionUpdateDetailsForPalette(element);
                    }
                });
    }
    
}
