package Main;

/**
 * Menu item.
 *
 * @author hasu
 */
public enum MenuItem {
    BACK("Back"),
    EXIT("Exit"),
    PET("Pet"),
    PET_SHOW("Show all pet"),
    PET_ADD("Add new Pet"),
    PET_SEARCH("Find pet by ID"),
    PET_UPDATE("Update a Pet"),
    PET_DELETE("Delete a pet"),
    PET_SAVE_TO_FILE("Save data to file"),
    PET_LOAD_FROM_FILE("Load data from file"),
    
    //...

    ORDER("Order"),
    ORDER_ADD("Add an order"),
    ORDER_LIST("List Orders"),
    ORDER_SORT("Sort an Orders"),
    ORDER_SAVE_TO_FILE("Save data to file"),
    ORDER_LOAD_FROM_FILE("Load data from file"),
    
    
    ;

    private final String label;

    public String getLabel() {
        return label;
    }

    private MenuItem(String label) {
        this.label = label;
    }

}
