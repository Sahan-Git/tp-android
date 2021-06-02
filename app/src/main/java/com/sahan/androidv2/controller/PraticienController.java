package com.sahan.androidv2.controller;

import android.content.Context;
import com.sahan.androidv2.model.Praticien;
import com.sahan.androidv2.model.PraticienDao;

import java.util.ArrayList;

public final class PraticienController {

    private static PraticienController praticienController = null;
    private static ArrayList<Praticien> praticiens;

    private PraticienController(){
        super();
    }

    /**
     * Accesseur
     * @return : une instance unique de PraticienController (singleton)
     */
    public static final PraticienController getInstance(Context context){
        if(PraticienController.praticienController == null) {
            PraticienController.praticienController = new PraticienController();
            praticiens = new ArrayList<>();
            PraticienDao praticienDao = new PraticienDao(context);
            praticiens = praticienDao.read();

        }
        return PraticienController.praticienController;
    }

    /**
     * Trouver un praticien par son indice dans l'arraylist
     * @param index
     * @return le praticien
     */

    public Praticien getPraticien(int index) {
        return praticiens.get(index);
    }

    /**
     * Liste des praticiens
     * @return : l'arraylist de praticiens
     */

    public ArrayList<Praticien> praticiens () {
        return praticiens;
    }
}