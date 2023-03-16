
package Model;

import java.io.Serializable;

public enum Category implements Serializable{
    PARROT("PARROT"),
    CAT("CAT"),
    DOG("DOG");

    private final String label;

    private Category() {
        this.label = null;
    }
    
    public String getLabel() {
        return label;
    }

    private Category(String label) {
        this.label = label;
    }

}
