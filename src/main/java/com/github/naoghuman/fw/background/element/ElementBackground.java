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
package com.github.naoghuman.fw.background.element;

import com.github.naoghuman.lib.fxml.core.FXMLModel;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Label;

/**
 *
 * @author  Naoghuman
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public final class ElementBackground extends Element {
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public ElementBackground() {
        super();
    }

    @Override
    public List<Node> getDetailsComponents() {
        final List<Node> nodes = FXCollections.observableArrayList();
        nodes.add(new Label("Components for ElementBackground -> Background"));
        
        return nodes;
    }

    @Override
    public List<Node> getDetailsDescription() {
        final List<Node> nodes = FXCollections.observableArrayList();
        nodes.add(new Label("Description for ElementBackground -> Palette"));
        
        return nodes;
    }

    @Override
    public List<ElementType> getParentTypes() {
        return FXCollections.observableArrayList();
    }

    @Override
    public ElementType getType() {
        return ElementType.BACKGROUND;
    }

    @Override
    public void readFrom(FXMLModel model) {
        
    }

    @Override
    public FXMLModel writeTo() {
        return null;
    }
    
}
