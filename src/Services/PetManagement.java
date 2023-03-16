package Services;

import Model.Category;
import Model.Pet;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.Util;

public class PetManagement implements Serializable{

    private static final PetManagement instance = new PetManagement();

    public static PetManagement getInstance() {
        return instance;
    }

    private Map<Enum<Category>, List<Pet>> petMap;

    private PetManagement() {
        this.petMap = new HashMap();
    }

    public Pet getPetByID(String id, Enum cate) {
        List<Pet> petList = petMap.get(cate);
        if (petList == null) {
            return null;
        } else {
            for (Pet pet : petList) {
                if (pet.getId().equals(id)) {
                    return pet;
                }
            }
        }
        return null;
    }

    public void showPet() {
        Category cate = new Pet().inputCategory(false);
        List<Pet> petList = petMap.get(cate);
        if (petList == null) {
            System.out.println(cate + " list is empty.");
        } else {
            for (Pet pet : petList) {
                System.out.println(pet);
            }
        }
    }

    public void addPet() {
        Category cate = new Pet().inputCategory(false);
        List<Pet> petList = petMap.get(cate);

        if (petList == null) {
            petList = new ArrayList();
            petMap.put(cate, petList);
        }
        Pet pet = new Pet();
        pet.input();
        pet.setCategory(cate);
        petList.add(pet);
    }

    public void searchPet() {
        Category cate = new Pet().inputCategory(false);
        String id = Util.inputString("Enter ID to search", false);
        Pet pet = getPetByID(id, cate);
        if (pet == null) {
            System.out.println("Not found");
        } else {
            System.out.println(pet);
        }
    }

    public void updatePet() {
        Category cate = new Pet().inputCategory(false);
        String id = Util.inputString("Enter ID to update", false);
        Pet pet = getPetByID(id, cate);
        if (pet == null) {
            System.out.println("Not found");
        } else {
            pet.update();
            System.out.println("Updated " + pet.getId());
        }
    }

    public void deletePet() {
        Category cate = new Pet().inputCategory(false);
        List<Pet> petList = petMap.get(cate);
        String id = Util.inputString("Enter ID to remove", false);
        Pet pet = getPetByID(id, cate);
        if (pet == null) {
            System.out.println("Not found");
        } else {
            if (false/*@TODO in order*/) {
                System.out.println(pet.getId() + " already in order, cannot delete");
            } else {
                petList.remove(pet);
                System.out.println("Removed " + pet.getId());
            }
        }
    }

     public void saveToFile() {
        if (petMap.isEmpty()) {
            System.out.println("Nothing to write");
            return;
        }

        try {
            File f = new File("pet.dat");
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(petMap);
            oos.close();
            fos.close();
        } catch (Exception e) {
//            System.out.println("Failed to save.");
            e.printStackTrace();
            return;
        }
        System.out.println("Saved data to pet.dat");
    }
    
    public void loadFromFile() {
        try {
            File f = new File("pet.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            petMap = (Map<Enum<Category>, List<Pet>>) ois.readObject();
            ois.close();
            fis.close();
        } catch (Exception e) {
            System.out.println("Failed to load");
            return;
        }
        System.out.println("Loaded data from pet.dat");
    }
}
