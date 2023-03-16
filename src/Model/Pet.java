package Model;

import java.io.Serializable;
import utils.Util;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pet implements Serializable {

    private String id;
    private String description;
    private Date date;
    private int unitPrice;
    private Enum category;

    public Pet() {
    }

    public Pet(String id, String description, Date date, int unitPrice, Enum category) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.unitPrice = unitPrice;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public final void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public final void setDescription(String description) {
        if (description.length() >= 5 && description.length() <= 30) {
            this.description = description;
        }
    }

    public Date getDate() {
        return date;
    }

    public final void setDate(Date date) {
        this.date = date;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public final void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Enum getCategory() {
        return category;
    }

    public void setCategory(Enum category) {
        this.category = category;
    }

    public void input() {
        while (true) {
            try {
                setId(Util.inputString("Enter pet ID", false).toUpperCase().trim());
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setDescription(Util.inputString("Enter pet description", false).toUpperCase().trim());
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setDate(Util.inputDate("Enter pet import date", true));
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        while (true) {
            try {
                setUnitPrice(Util.inputInteger("Enter pet unit price", 0, Integer.MAX_VALUE));
                break;
            } catch (Exception ex) {
                Logger.getLogger(Pet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public Category inputCategory(boolean allowEmpty) {
        Category category = null;
        String cate="";
        do {
            try {
                cate = Util.inputString("Enter pet category", allowEmpty);
                category = Category.valueOf(cate.trim().toUpperCase());
            } catch (Exception e) {
                if (allowEmpty) {
                    return null;
                }
            }
        } while (!allowEmpty && cate.isBlank());
        return category;
    }

    public void update() {
        do {
            String id = Util.inputString("Enter the new pet ID", true);
            if (!id.isEmpty()) {
                if (Util.checkPetId(id)) {
                    setId(id);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        do {
            String description = Util.inputString("Enter the new pet description", true);
            if (!description.isEmpty()) {
                if (Util.checkPetDescription(description)) {
                    setDescription(description);
                    break;
                } else {
                    System.out.println("Error");
                }
            } else {
                break;
            }
        } while (true);

        do {
            Date date = Util.inputDate("Enter the new pet import date", true);
            if (true) {
                setDate(date);
                break;
            }
        } while (true);

        do {
            int unitprice = Util.inputInteger("Enter the new pet unit price", 0, Integer.MAX_VALUE);
            if (unitprice >= 0) {
                setUnitPrice(unitprice);
                break;
            } else {
                System.out.println("error");
            }
        } while (true);

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(",");
        sb.append(description).append(",");
        sb.append(Util.toString(date, Util.DATE_FORMAT)).append(",");
        sb.append(unitPrice).append(",");
        sb.append(category);
        return sb.toString();
    }

}
