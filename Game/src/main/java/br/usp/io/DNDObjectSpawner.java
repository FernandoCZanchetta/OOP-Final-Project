/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.io;

import br.usp.core.GameEngine;
import br.usp.model.GameObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.vecmath.Point2d;

/**
 *
 * @author Fernando
 */
public class DNDObjectSpawner implements DNDSubscriber {
    private InputAPI input;

    public DNDObjectSpawner(InputAPI input) {
        this.input = input;
    }
    
    public void start() {
        input.subscribeToDND(this);
    }

    public void stop() {
        input.unsubscribeToDND(this);
    }
    
    @Override
    public void receiveDNDFile(File file) {
        System.out.printf("Received file: %s\n", file.getAbsolutePath());

        if (!file.getName().toLowerCase().endsWith(".zip")) {
            System.err.println("Only .zip files are supported for DnD.");
            return;
        }

        try (ZipInputStream zipStream = new ZipInputStream(new FileInputStream(file))) {
            ZipEntry entry;

            while ((entry = zipStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().toLowerCase().endsWith(".json")) {
                    // Criar arquivo temporário
                    File tempJson = File.createTempFile("dnd_obj", ".json");
                    tempJson.deleteOnExit();

                    try (FileOutputStream out = new FileOutputStream(tempJson)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zipStream.read(buffer)) > 0) {
                            out.write(buffer, 0, len);
                        }
                    }
                    
                    Point2d droppedPos = input.getMousePos(GameEngine.getGameEngineInstance().getMainFrame());
                    
                    // Instanciar o objeto
                    GameEngine.getGameEngineInstance().loadGameObjectFromJson(tempJson.getAbsolutePath(), droppedPos);
                    System.out.println("Spawned object at mouse position!");

                    break;
                }
            }

        } catch (IOException ex) {
            System.err.println("Error handling .zip file: " + ex.getMessage());
        }
    }
    
}
