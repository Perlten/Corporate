/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.persistence.Persistence;

/**
 *
 * @author Jesper
 */
public class RunJPA {
    public static void main(String[] args) {
        Persistence.generateSchema("putest", null);
    }
}