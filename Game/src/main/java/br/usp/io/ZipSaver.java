/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author Fernando
 */
public class ZipSaver {
    public static void zipJson(String jsonPath, String zipPath) {
        File inputFile = new File(jsonPath);
        File zipFile = new File(zipPath);

        try (FileOutputStream fos = new FileOutputStream(zipFile);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(inputFile)) {

            ZipEntry entry = new ZipEntry(inputFile.getName());
            zos.putNextEntry(entry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) >= 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
            System.out.println("Arquivo zipado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
