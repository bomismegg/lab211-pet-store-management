package utils;

import Model.Category;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    public static final String SEPARATOR = ",";
    public static final String DATE_FORMAT = "dd/MM/yyyy"; // xem docs cua SimpleDateFormat 

    public static Date toDate(String strDate) throws ParseException {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(Util.DATE_FORMAT);
        df.setLenient(false);
        return df.parse(strDate);
    }

    public static String toString(Date date, String dateFormat) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static Date inputDate(String message) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (date == null);
        return date;
    }

    public static boolean inputBoolean(String message) {
        System.out.print(message + "(" + Boolean.TRUE.toString() + "/" + Boolean.FALSE.toString() + "): ");
        Scanner sc = new Scanner(System.in);
        return Boolean.parseBoolean(sc.nextLine());
    }

    public static boolean validateDate(Date inputDate) {
        if (!inputDate.toString().matches(DATE_FORMAT)) {
            return false;
        }
        return true;
    }

    public static String inputString(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (!allowEmpty && str.isBlank());
        return str.trim();
    }

    public static int inputInteger(String message, int minValue, int maxValue) {
        int val = minValue - 1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print(message + ": ");
            try {
                val = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (val < minValue || maxValue < val);
        return val;
    }

    public static Date inputDate(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        Date date = null;
        do {
            System.out.print(message + "(" + Util.DATE_FORMAT + "): ");
            try {
                date = toDate(sc.nextLine());
            } catch (ParseException ex) {

            }
        } while (date == null & !allowEmpty);
        return date;
    }

    public static double inputDouble(String msg) {
        double res = 0;
        boolean flag;
        Scanner sc = new Scanner(System.in);
        do {
            try {
                System.out.print(msg + ": ");
                flag = false;
                res = Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(e);
                flag = true;
            }
        } while (flag);
        return res;
    }

    public static boolean checkPetDescription(String description) {
        if (description.length() > 5 && description.length() <= 30) {
            return true;
        }
        return false;
    }

    public static boolean checkPetId(String id) {
        if (!id.isEmpty()) {
            return true;
        }
        return false;
    }

    public static YearMonth inputYearMonth(String message, String pattern, boolean allowEmpty) {
        YearMonth yearMonth = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        do {
            String inputStr = inputString(message + " (" + pattern + ")", allowEmpty);
            try {
                yearMonth = YearMonth.parse(inputStr, dateTimeFormatter);
            } catch (Exception e) {

            }
        } while (yearMonth == null);
        return yearMonth;
    }
}
