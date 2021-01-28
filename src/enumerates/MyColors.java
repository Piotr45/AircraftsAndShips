package enumerates;

/**
 * This enumerates contains colors, that I used to paint objects in gui.
 */
public enum MyColors {
     darkBlueColor("#152238"),
     keyLime("#E7F59E"),
     mintCream("#F1F7EE"),
     laurelGreen("#B0BEA9"),
     asparagus("#92AA83"),
     nyanza("#E0EDC5"),
     rubyRed("#A41623"),
     rajah("#FFB563"),
     bluePantone("#3423A6"),
     lightGrey("#D3D3D3"),
     lightBlue("#add8e6"),
     lightRed("#A62C2A"),
     darkGreen("#013220");

     public final String hexCode;

     /**
      * This method return hexCode of requested color.
      * @param hexCode hexadecimal code of color.
      */
     private MyColors(String hexCode){
         this.hexCode = hexCode;
     }
}
