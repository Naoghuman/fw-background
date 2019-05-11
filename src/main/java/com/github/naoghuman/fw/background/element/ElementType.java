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

/**
 *
 * @author  Naoghuman
 * @since   0.1.0-PRERELEASE
 * @version 0.1.0-PRERELEASE
 */
public enum ElementType {
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    BACKGROUND("Background"), // NOI18N // later i18n
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    CLIP("Clip"), // NOI18N
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    COLOR("Color"), // NOI18N
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    IMAGE("Image"), // NOI18N
    
    /**
     * 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    TEXT("Text");  // NOI18N
    
    private final String name;
    
    ElementType(final String name) {
        this.name = name;
    }

    /**
     * 
     * @return 
     * @author  Naoghuman
     * @since   0.1.0-PRERELEASE
     * @version 0.1.0-PRERELEASE
     */
    public String getName() {
        return name;
    }
    
}
