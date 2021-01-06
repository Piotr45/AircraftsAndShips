package enumerates;

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
     lightRed("#A62C2A");

     public final String hexCode;

     private MyColors(String hexCode){
         this.hexCode = hexCode;
     }
}
