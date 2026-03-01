package grupo;
/*Autor: Arslan Marinmov */
public class DayArslan{
    private String month;
    private int day, year, monthNumber;
    private final int currentYear = 2026;

    public DayArslan(int dd, int mm, int yyyy){
        this.day = dd;
        this.monthNumber = mm;
        this.year = yyyy;
        switch(monthNumber){
            case 1: month = "January"; break;
            case 2: month = "February"; break;
            case 3: month = "March"; break;
            case 4: month = "April"; break;
            case 5: month = "May"; break;
            case 6: month = "June"; break;
            case 7: month = "July"; break;
            case 8: month = "August"; break;
            case 9: month = "September"; break;
            case 10: month = "October"; break;
            case 11: month = "November"; break;
            case 12: month = "December"; break;
        }
    }
    public DayArslan(){}

    public boolean isValidDay(int dd, int mm, int yyyy) {
        if (yyyy < 1960 || yyyy > this.currentYear) {
            return false;
        }

        if (mm < 1 || mm > 12) {
            return false;
        }

        boolean esBisiesto = (yyyy % 4 == 0 && yyyy % 100 != 0) || (yyyy % 400 == 0);
        int diasEnMes;
        switch (mm) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                diasEnMes = 31;
                break;
            case 4: case 6: case 9: case 11:
                diasEnMes = 30;
                break;
            case 2:
                diasEnMes = esBisiesto ? 29 : 28;
                break;
            default:
                return false; // nunca debería llegar aquí
        }
        return dd >= 1 && dd <= diasEnMes;
    }


    public String getStringDate(int typeOfFormat){
        String date = "", days, months, years;
        switch(typeOfFormat){
            case 1: 
                if(this.day < 10){
                    days = "0" + this.day; 
                } else{ 
                    days = Integer.toString(this.day);
                }
                if(this.monthNumber < 10){
                    months = "0" + this.monthNumber;
                } else{ 
                    months = Integer.toString(this.monthNumber);
                }
                years = Integer.toString(this.year);
                date = days + "/" + months + "/"+ years; break;
            case 2:
                days = Integer.toString(this.day); 
                months = this.month; 
                years = Integer.toString(this.year);
                date = months + " " + days + ", " + years; break;
        }
        return date;
    }

    public int slashNumericFormat(){return 1;}
    public int textFormat(){return 2;}
}
