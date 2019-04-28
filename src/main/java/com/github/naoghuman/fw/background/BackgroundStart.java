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
package com.github.naoghuman.fw.background;

import com.github.naoghuman.lib.fxml.core.FXMLView;
import com.github.naoghuman.lib.logger.core.LoggerFacade;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Naoghuman
 * @since   0.1.0
 * @version 0.1.0
 */
public class BackgroundStart extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        super.init();
        
        LoggerFacade.getDefault().info(this.getClass(), "BackgroundStart#init()"); // NOI18N
        
    }
    
    @Override
    public void start(final Stage primaryStage) throws Exception {
        LoggerFacade.getDefault().debug(this.getClass(), "BackgroundStart#start(Stage)"); // NOI18N
    
        primaryStage.setTitle("Background-Editor: v0.1.0-PRERELEASE"); // NOI18N
        
        final FXMLView view = FXMLView.create(BackgroundController.class);
        final Scene scene = new Scene(view.getRoot().orElse(new AnchorPane()), 960, 540);
        primaryStage.setScene(scene);
        
        primaryStage.show();
    }
    
}
