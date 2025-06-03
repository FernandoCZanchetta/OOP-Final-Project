/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model.map;

import br.usp.model.level.LevelData;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Map;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class TileMap {
    private final Dimension d;
    private final ArrayList<Tile> tiles;

    public TileMap(Dimension d) {
        this.d = d;
        this.tiles = new ArrayList<>(); //VER SE VOU USAR UM LIMITE MAXIMO COMO ESTA NAS CONST OU SE VAI SER LIVRE (LEMBRAR Q TEM LIMITE NO POS)

        //TO-DO: IMPLEMENTAR UM MAPA DECENTE!!!!!!!!!!!!!!!!!!!
        
        generateSampleMap(); //Teste

    }

    public void loadFromData(LevelData data, MapRegionManager regionManager) {
        tiles.clear();
        for (int y = 0; y < data.getHeight(); y++) {
            for (int x = 0; x < data.getWidth(); x++) {
                TileType type = switch (data.getTiles()[y][x]) {
                    case 0 -> TileType.FLOOR;
                    case 1 -> TileType.WALL;
                    case 2 -> TileType.DOOR;
                    case 3 -> TileType.HEART;
                    case 4 -> TileType.MAP;
                    default -> TileType.FLOOR;
                };
                
                Tile tile = new Tile(type, new Point2d(x, y));
                
                int regionId = data.getRegionIds()[y][x];
                
                String regionColor = switch(regionId) {
                    case 0 -> "fogless";
                    case 1 -> "yellow";
                    case 2 -> "red";
                    case 3 -> "blue";
                    case 4 -> "green";
                    default -> "none";
                };
                
                MapRegion region = regionManager.getRegion(regionColor);
                if (region == null) {
                    region = new MapRegion(regionColor);
                    regionManager.addRegion(region);
                }
                region.addTile(tile);
                
                tile.setVisible(region.isUnlocked());
                tile.changeId(regionColor);
                
                tiles.add(tile);
            }
        }
        
        if(data.getDoorMetadata() != null) {
            for (Map<String, Object> doorMeta : data.getDoorMetadata()) {
                int x = (int) doorMeta.get("x");
                int y = (int) doorMeta.get("y");
                String keyId = (String) doorMeta.get("keyId");

                // Encontra o tile correspondente
                for (Tile tile : tiles) {
                    if (tile.getPosition().getX() == x && tile.getPosition().getY() == y) {
                        if (tile.getType() == TileType.DOOR) {
                            tile.changeId(keyId); // Armazena o ID da chave nessa porta
                        } else {
                            System.out.println("Aviso: metadado de porta em tile que não é DOOR (" + x + "," + y + ")");
                        }
                        break;
                    }
                }
            }
        }
    }
    
    private void generateSampleMap() {
        for (int y = 0; y < d.height; y++) {
            for (int x = 0; x < d.width; x++) {
                if (x == 0 || y == 0 || x == d.width - 1 || y == d.height - 1 || (x == 4 && y > 1)) {
                    tiles.add(new Tile(TileType.WALL, new Point2d(x, y))); //Adiciona as paredes da borda
                } else {
                    tiles.add(new Tile(TileType.FLOOR, new Point2d(x, y))); //Adiciona o chão normal
                }
            }
        }
    }
    
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Dimension getDimension() {
        return d;
    }
    
    public int getHeight() {
        return d.height;
    }
    
    public int getWidth() {
        return d.width;
    }
}