/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.items;

import java.util.Map;

/**
 *
 * @author Fernando
 */
public class ItemFactory {
    public static Item loadItemFromSave(Map<String, Object> data) {
        String typeStr = (String) data.get("type");
        
        ItemType type = ItemType.valueOf(typeStr);

        return switch (type) {
            case KEY -> Key.deserialize(data);
            case HEART -> Heart.deserialize(data);
            //case CLOCK -> Clock.deserialize(data);
            default -> throw new IllegalArgumentException("Unknown item type: " + type);
        };
    }
}
