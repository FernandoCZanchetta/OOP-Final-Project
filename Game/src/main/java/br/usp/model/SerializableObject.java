/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.usp.model;

import java.util.Map;

/**
 *
 * @author Fernando
 */
public interface SerializableObject {
    public abstract Map<String, Object> serialize();
    
    public static SerializableObject deserialize(Map<String, Object> data) {
        System.out.println("Deserialização não implementada!");
        return null;
    }
}
