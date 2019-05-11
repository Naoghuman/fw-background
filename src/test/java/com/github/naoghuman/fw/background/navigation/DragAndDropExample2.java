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

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * https://stackoverflow.com/questions/30453919/drag-and-drop-from-listview-to-treeview
 *
 * @author Naoghuman
 * @since 0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public class DragAndDropExample2 extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HBox box = new HBox();

        // ListView
        ListView<String> list = new ListView<>(FXCollections.observableArrayList(
                "Single", "Double", "Suite", "Family App"));

        list.setCellFactory((ListView<String> param) -> {
            ListCell<String> listCell = new ListCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    this.setText(item);
                }
            };
            
            listCell.setOnDragDetected((MouseEvent event) -> {
                System.out.println("listcell setOnDragDetected");
                Dragboard db = listCell.startDragAndDrop(TransferMode.COPY);
                ClipboardContent content = new ClipboardContent();
                content.putString(listCell.getItem());
                db.setContent(content);
                
                event.consume();
            });
            
            return listCell;
        });

        //TreeView
        TreeItem<String> rootItem = new TreeItem<>("Inbox");
        rootItem.setExpanded(true);
        rootItem.getChildren().add(new TreeItem<>("Single Parent"));
        rootItem.getChildren().add(new TreeItem<>("Double Parent"));
        rootItem.getChildren().add(new TreeItem<>("Suite Parent"));
        rootItem.getChildren().add(new TreeItem<>("Family App Parent"));
        
        TreeView<String> treeView = new TreeView<>(rootItem);
        treeView.setEditable(true);

        treeView.setCellFactory((TreeView<String> stringTreeView) -> {
            TreeCell<String> treeCell = new TreeCell<String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    
                    if (item != null) {
                        this.setText(item);
                    }
                }
            };
            
            treeCell.setOnDragEntered((DragEvent event) -> {
                Dragboard dragboard = event.getDragboard();
                if (
                        dragboard.hasString()
                        && treeCell.getText().contains(dragboard.getString())
                ) {
                    treeCell.setStyle("-fx-background-color:LIGHTGREEN;");
                } else {
                    treeCell.setStyle("-fx-background-color:CRIMSON;");
                }
            });
            
            treeCell.setOnDragExited((DragEvent event) -> {
                treeCell.setStyle("");
            });
            
            treeCell.setOnDragOver((DragEvent event) -> {
                Dragboard db = event.getDragboard();
                if (db.hasString()) {
                    event.acceptTransferModes(TransferMode.COPY);
                }
                
                event.consume();
            });
            
            treeCell.setOnDragDropped((DragEvent event) -> {
                System.out.println("treeCell.setOnDragDropped");
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (
                        db.hasString()
                        && treeCell.getText().contains(db.getString())
                ) {
                    System.out.println("Dropped: " + db.getString());
                    treeCell.getTreeItem().getChildren().add(new TreeItem<>(db.getString()));
                    treeCell.getTreeItem().setExpanded(true);
                    success = true;
                }
                event.setDropCompleted(success);
                
                event.consume();
            });
            
            return treeCell;
        });

        box.getChildren().addAll(list, treeView);
        primaryStage.setScene(new Scene(box));
        primaryStage.show();
    }
    
}
