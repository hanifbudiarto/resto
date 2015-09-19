/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package windu.pos.ui;

import javax.swing.JOptionPane;

/**
 *
 * @author WinduPurnomo
 */
public class Hapus2 {
    public static void main (String Args[]){

//Deklarasi variable
String ANGKA;
String SATUAN;
String PULUHAN;
String RATUSAN;
int angka;
int satuan;
int puluhan;
int Puluhan;
int ratusan;

//memasukan sebuah angka dengan input dialog
ANGKA = JOptionPane.showInputDialog("MASUKAN BILANGAN [ 1...999 ] : ");

//mengkonversi angka dari string ke integer
angka = Integer.parseInt(ANGKA);

// Perhitungan memisahkan ratusan puluhan satuan
ratusan = angka / 100 ;
Puluhan = angka % 100 ;
puluhan = Puluhan / 10 ;
satuan = angka % 10 ;

//cetak nilai ratusan
if (angka < 1000 && angka > 0 ){
switch(ratusan){
case 0 : break;
case 1 : System.out.print(" seratus ");break;
case 2 : System.out.print(" dua ");break;
case 3 : System.out.print(" tiga ");break;
case 4 : System.out.print(" empat ");break;
case 5 : System.out.print(" lima ");break;
case 6 : System.out.print(" enam ");break;
case 7 : System.out.print(" tujuh ");break;
case 8 : System.out.print(" delapan ");break;
case 9 : System.out.print(" sembilan ");break;
}
            
if(angka < 1000 && angka >= 200){
System.out.print("ratus ");
}

//cetak nilai puluhan
if (Puluhan == 11){
System.out.println(" sebelas");
}else if (angka >=20) {
switch(puluhan){
case 0 : break;
case 2 : System.out.print("dua ");break;
case 3 : System.out.print("tiga ");break;
case 4 : System.out.print("empat ");break;
case 5 : System.out.print("lima ");break;
case 6 : System.out.print("enam ");break;
case 7 : System.out.print("tujuh ");break;
case 8 : System.out.print("delapan ");break;
case 9 : System.out.print("sembilan ");break;
}
}if (Puluhan == 10){
System.out.println("sepuluh ");
}

if(Puluhan < 100 && Puluhan >= 20){
System.out.print("puluh ");
}

if (Puluhan != 11){
{

//cetak nilai satuan
switch(satuan){
case 0 : break;
case 1 : System.out.print("satu ");break;
case 2 : System.out.print("dua ");break;
case 3 : System.out.print("tiga ");break;
case 4 : System.out.print("empat ");break;
case 5 : System.out.print("lima ");break;
case 6 : System.out.print("enam ");break;
case 7 : System.out.print("tujuh ");break;
case 8 : System.out.print("delapan ");break;
case 9 : System.out.print("sembilan ");break;

}

}
}
if (Puluhan > 11 && Puluhan < 20){
System.out.println("belas");
}

//menampilkan pesan kesalahan apabila kesalahan memasukan input
}else {
JOptionPane.showMessageDialog(null, "Input yang anda masukan diluar jangkauan ( Out Of Bounds )");
angka = -1;
}

}
}
