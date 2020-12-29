package enumerates;

public enum MyColors {
     darkBlueColor("#152238"),
     keyLime("#E7F59E"),
     mintCream("#F1F7EE"),
     laurelGreen("#B0BEA9"),
     asparagus("#92AA83"),
     nyanza("#E0EDC5");

     public final String hexCode;

     private MyColors(String hexCode){
         this.hexCode = hexCode;
     }
}
