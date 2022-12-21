/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.math.BigInteger;

/**
 *
 * @author jason
 */
public class MainTest {
     public static void main(String[] args) {
        /* String s2="";
      BigInteger holandesherrante=BigInteger.valueOf(999);
      byte[] array=holandesherrante.toByteArray();
      for(int i=0;i<array.length;i++){
          String s1 = String.format("%8s", Integer.toBinaryString(array[i] & 0xFF)).replace(' ', '0');
          s2=s2+s1;
      } 
for(int i=0;s2.length()<32;i++){
    s2="0"+s2;
}
System.out.println(s2);
*/
        BigInteger holandesherrante=BigInteger.valueOf(999);
      byte[] array=holandesherrante.toByteArray();
      holandesherrante=new BigInteger(array);
      int a=holandesherrante.intValue();
         System.out.println(a);
        
        
    }
}
