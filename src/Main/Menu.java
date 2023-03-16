package Main;

import utils.Util;
import Main.MenuItem;

public class Menu {

    private final MenuItem[] primaryOptions = {
        MenuItem.EXIT,
        MenuItem.PET,
        MenuItem.ORDER,};

    private final MenuItem[] petOptions = {
        MenuItem.BACK,
        MenuItem.PET_SHOW,
        MenuItem.PET_ADD,
        MenuItem.PET_SEARCH,
        MenuItem.PET_UPDATE,
        MenuItem.PET_DELETE,
        MenuItem.PET_SAVE_TO_FILE,
        MenuItem.PET_LOAD_FROM_FILE,};

    private final MenuItem[] OrderOptions = {
        MenuItem.BACK,
        MenuItem.ORDER_ADD,
        MenuItem.ORDER_LIST,
        MenuItem.ORDER_SORT,
        MenuItem.ORDER_SAVE_TO_FILE,
        MenuItem.ORDER_LOAD_FROM_FILE,};

    private MenuItem primaryOption = null;
    private MenuItem subOption = null;

    public Menu() {
        this.primaryOption = MenuItem.EXIT;
        this.subOption = MenuItem.BACK;
    }

    public MenuItem getUserChoice() {
        do {
            if (subOption == MenuItem.BACK) {
                primaryOption = getChoice(null);
            }
            if (primaryOption != MenuItem.EXIT && !isRepeatAction()) {
                subOption = getChoice(primaryOption);
            }
        } while (primaryOption != MenuItem.EXIT && subOption == MenuItem.BACK);
        return primaryOption.equals(MenuItem.EXIT) ? MenuItem.EXIT : subOption;
    }

    private MenuItem getChoice(MenuItem option) {
        MenuItem[] optionList = getOptionList(option);
        String menuCaption;
        if (option == null) {
            menuCaption = "Order Management:";
        } else {
            menuCaption = option.getLabel();
        }
        int numItems = showOptionMenu(menuCaption, optionList);
        int choice = Util.inputInteger("Please enter your choice", 0, numItems - 1);

        return optionList[choice];
    }

    private int showOptionMenu(String menuCaption, MenuItem[] optionList) {
        int numItems = 1;
        System.out.println("*********************************************");
        System.out.println(menuCaption);
        for (int i = 1; i < optionList.length; i++) {
            System.out.printf("(%d) -> %s\n", numItems, optionList[i].getLabel());
            numItems++;
        }
        System.out.printf("(0) -> %s\n", optionList[0].getLabel());
        System.out.println("*********************************************");
        return numItems;
    }

    private MenuItem[] getOptionList(MenuItem option) {
        MenuItem[] optionList;
        if (option == null) {
            optionList = primaryOptions;
        } else {
            optionList = switch (option) {
                case PET ->
                    petOptions;
                case ORDER ->
                    OrderOptions;
                default ->
                    primaryOptions;
            };
        }

        return optionList;
    }

    private boolean isRepeatAction() {
        switch (subOption) {
            case PET_ADD:
            case PET_SEARCH:
            case PET_UPDATE:
            case PET_DELETE:
                String confirm = Util.inputString("Repeat action (Y/N)", false);
                return confirm.trim().toLowerCase().startsWith("y");
        }
        return false;
    }
}
