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

import com.github.naoghuman.lib.fxml.core.FXMLModelable;
import java.io.Serializable;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.input.DataFormat;

/**
 * What defines a NavigationPaletteElement
 *  - for the palette I need minimal attributs.
 *     - title (will filled automatically with type.getName())
 *     - description (will be shown in the details view)
 *     - css styles for the type
 *  - when an element is in the NavigationBackground, then I need extended
 *    attributs from the element.
 *     - here I need all supported attributs.
 *     - title, description, type, css
 *     - id, layoutX, layoutY, width, height
 *     - image has url
 *     - 
 * 
 * What attributs can I get from the JavaFX object?
 *  - all other attributs must be defined into this class.
 *  - In the parent class I need to implment FXMLModelable.
 *     - The concrete subclasses will override the methods to configure 
 *       the FXMLModel with the concrete attributs.
 * 
 * 
 * @author  Naoghuman
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public abstract class Element implements FXMLModelable, Serializable {
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public static final DataFormat DATA_FORMAT = new DataFormat(Element.class.getName());
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public static final Element ROOT_ELEMENT__BACKGROUND = new ElementBackground();
    
    private double width = 0.0d;
    private long   id    = -1L;
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public Element() {
        
    }
    
    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public abstract List<Node> getDetailsComponents();
    
    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public abstract List<Node> getDetailsDescription();
    
    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public long getId() {
        return id;
    }
    
    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public abstract List<ElementType> getParentTypes();
    
    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public String getTitle() {
        return this.getType().getName();
    }
    
    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public abstract ElementType getType();
    
    /**
     * 
     * @param id 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public void setId(final long id) {
        this.id = id;
    }
    
}
