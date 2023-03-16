package Main;

import Services.OrderManagement;
import Services.PetManagement;

public class PetStoreManagement {

    private PetManagement petManagement;
    private OrderManagement orderManagerment;

    public PetStoreManagement() {
        this.petManagement = petManagement.getInstance();
        this.orderManagerment = orderManagerment.getInstance();
        petManagement.loadFromFile();
        orderManagerment.loadFromFile();
    }

    private void process() throws Exception {
        Menu menu = new Menu();
        int option = Integer.MAX_VALUE;
        MenuItem userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case PET_SHOW:
                    petManagement.showPet();
                    break;
                case PET_ADD:
                    petManagement.addPet();
                    petManagement.saveToFile();
                    break;
                case PET_SEARCH:
                    petManagement.searchPet();
                    break;
                case PET_UPDATE:
                    petManagement.updatePet();
                    petManagement.saveToFile();
                    break;
                case PET_DELETE:
                    petManagement.deletePet();
                    petManagement.saveToFile();
                    break;
                case PET_SAVE_TO_FILE:
                    petManagement.saveToFile();
                    break;
                case PET_LOAD_FROM_FILE:
                    petManagement.loadFromFile();
                    break;
                case ORDER_ADD:
                    orderManagerment.addOrder();
                    orderManagerment.saveToFile();
                    break;
                case ORDER_LIST:
                    orderManagerment.listOrder();
                    break;
                case ORDER_SORT:
                    orderManagerment.sortOrder();
                    break;
                case ORDER_SAVE_TO_FILE:
                    orderManagerment.saveToFile();
                    break;
                case ORDER_LOAD_FROM_FILE:
                    orderManagerment.loadFromFile();
                    break;

                case EXIT:
                    System.out.println("Exited!");
                    break;

                default:
                    System.out.println("???");
            }
        } while (userChoice != MenuItem.EXIT);
    }

    public static void main(String[] args) throws Exception {
        new PetStoreManagement().process();
    }

}
