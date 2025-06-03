/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.items;

import br.usp.model.level.LevelData;
import br.usp.model.map.MapRegion;
import br.usp.model.map.MapRegionManager;
import br.usp.model.map.Tile;
import br.usp.model.map.TileMap;
import br.usp.model.map.TileType;
import java.util.ArrayList;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class ItemMap {
    private final ArrayList<Item> gameItems;

    public ItemMap() {
        this.gameItems = new ArrayList<>();
    }

    public void loadFromData(LevelData data, TileMap map, MapRegionManager regionManager) {
        gameItems.clear();
        
        if(data.getItemData() != null) {
            for (Map<String, Object> itemData : data.getItemData()) {
                String typeStr = (String) itemData.get("type");
                int x = (int) itemData.get("x");
                int y = (int) itemData.get("y");
                String id = (String) itemData.get("id");
                
                ItemType type = ItemType.valueOf(typeStr);
                Point2d pos = new Point2d(x, y);
                
                Tile itemTile = null;
                for(Tile t : map.getTiles()) {
                    if(t.getPosition().getX() == x && t.getPosition().getY() == y) {
                        itemTile = t;
                    }
                }
                
                if(!(itemTile.getType() == TileType.FLOOR)) {
                    System.out.println("Item inserido em Tile do Tipo " + itemTile.getType() + " em " + itemTile.getPosition());
                    System.out.println("ATENÇÃO!");
                }
                
                Item currentItem = null;
                switch (type) {
                    case KEY -> {
                        Key key = new Key(id, ItemType.KEY, pos);
                        gameItems.add(key);
                        currentItem = key;
                    }
                    // Adicione outros tipos aqui se quiser (ex: HEART)
                }
                

                MapRegion region = regionManager.getRegion(itemTile.getId());
                region.addItem(currentItem);
                
                currentItem.setVisible(region.isUnlocked());
            }
        }
    }
    
    public ArrayList<Item> getItems() {
        return gameItems;
    }
}
