/* 
 Author: Carlos Álvaro García
 */

#include <pic16f690.h>
#include <xc.h>
#define _XTAL_FREQ 4000000
#include  <stdio.h>
#include  <stdbool.h>
#include "uart.h"
#include "TCS34725.h"


// PINS I2C

#define SDA_PIN RB4	// 
#define SCL_PIN RB6	// 
#define SDA_DIR TRISB4
#define SCL_DIR TRISB6


#pragma config FOSC = INTRCIO   // Oscillator Selection bits (INTOSCIO oscillator: I/O function on RA4/OSC2/CLKOUT pin, I/O function on RA5/OSC1/CLKIN)
#pragma config WDTE = OFF       // Watchdog Timer Enable bit (WDT disabled and can be enabled by SWDTEN bit of the WDTCON register)
#pragma config PWRTE = OFF      // Power-up Timer Enable bit (PWRT disabled)
#pragma config MCLRE = OFF      // MCLR Pin Function Select bit (MCLR pin function is digital input, MCLR internally tied to VDD)
#pragma config CP = OFF         // Code Protection bit (Program memory code protection is disabled)
#pragma config CPD = OFF        // Data Code Protection bit (Data memory code protection is disabled)
#pragma config BOREN = OFF      // Brown-out Reset Selection bits (BOR disabled)
#pragma config IESO = OFF       // Internal External Switchover bit (Internal External Switchover mode is disabled)
#pragma config FCMEN = OFF      // Fail-Safe Clock Monitor Enabled bit (Fail-Safe Clock Monitor is disabled)

//variables a usar més tard
char buf[16];
unsigned int cdat;            
unsigned int id;
int t;
int LECTURA;



int main(void)
{
  //tots els ports es posen a 0 inicialment
  PORTA = 0;
  PORTB = 0;
  PORTC = 0;

  TRISA = 0b00000100; //RA2 com a entrada (pendent d'interrupcions)
  TRISB = 0b00000000; //tots els pins com a sortida
  TRISC = 0b00000000; // tots els pins com a sortida

  //com que es vol treballar digitalment, ANSEL i ANSELH a 0
  ANSEL = 0X00; // digital
  ANSELH = 0X00; // digital

  INTF = 0; // Reset del flag de les interrupcions 
  INTE = 1; // Habilitació les interrupcions externes
  PEIE = 1; //Habilitació de interrupcions de perifèrics 
  GIE = 1; // Habilitació interrupcions globals 
  // Habilitació de els pull-up interns, per no tenir problemes amb 
  // els possibles punts flotants quan es connecten les interrupcions externes
  OPTION_REG = 0; 
  
 //configuració BLUETOOTH
     TRISBbits.TRISB5=1;     // Habilitem el pin 5B com a entrada (RX)
     TRISBbits.TRISB7=0;      // Habilitem el pin 5B com a entrada (RX)
     UART_Init(9600);// inicialització de la comunicació UART amb el Bluetooth
    
     

  
/*En cas d'identificar el sensor, el configurem*/
 if(initialize) {
     
      wireWriteDataByte(TCS34725_ENABLE, TCS34725_ENABLE_PON);
            __delay_ms(10);
             wireWriteDataByte(TCS34725_ENABLE,  0b00000011);  
             __delay_ms(10);   
             
//inicialització dels paràmetres del sensor
wireWriteDataByte(TCS34725_ATIME, TCS34725_WT_240MS); // es defineix el temps
//d'integració com 240ms
    __delay_ms(10);
     wireWriteDataByte(TCS34725_CONTROL, TCS34725_GAIN_4X); //s'afegeix un 
     //guany canònic al valor captat pel sensor (valor per defecte) 
    }
  
__delay_ms(100);


    




  while (1)
  {

    __delay_ms(1000);

   
  }
}


void interrupt isr()
{
   
    
int C_data_L, C_data_H;
int R_data_L, R_data_H;
int G_data_L, G_data_H;
int B_data_L, B_data_H;
  if (INTF) //si el flag de les interrupcions val 1
  {
      //es deshabiliten interrupcions momentàneament
    INTE = 0;
__delay_ms(20);
 
//es sol·liciten els valors dels registres corresponents a les components RGBC
	C_data_L = wireReadDataByte(TCS34725_CDATAL);
	C_data_H = wireReadDataByte(TCS34725_CDATAH);
	R_data_L = wireReadDataByte(TCS34725_RDATAL);
	R_data_H = wireReadDataByte(TCS34725_RDATAH);
	G_data_L = wireReadDataByte(TCS34725_GDATAL);
	G_data_H = wireReadDataByte(TCS34725_GDATAH);
	B_data_L = wireReadDataByte(TCS34725_BDATAL);
	B_data_H = wireReadDataByte(TCS34725_BDATAH);   
    //es deixa el microcontrolador en espera fins passat el temps d'intregració
    __delay_ms(240);


  //s'escriu un nombre arbitrari al BL (a l'smarphone) com a 'hand-shake'
    UART_Write(45);
    //s'obté el valor en 16 bits del registre de Clear
  cdat=C_data_L+(C_data_H << 8);
 
  //es transmet el missatge al BL, és a dir, al dispositiu Smartpone
  //el missatge s'escriu com a text, i els valors en 16 bits del colors, 
// es transformen primerament en un String (una cadena de caràcters)
// a través d'un buffer  
  
    UART_Write_Text("Color:(");
 __delay_ms(3);
 sprintf(buf,"%d",cdat);
 UART_Write_Text(buf);
 __delay_ms(3);
 UART_Write_Text(",");
sprintf(buf,"%d",(R_data_L+(R_data_H << 8)));
 __delay_ms(3);
  UART_Write_Text(buf);
   __delay_ms(3);
 UART_Write_Text(",");
  __delay_ms(3);
 sprintf(buf,"%d",(G_data_L+(G_data_H << 8)));
  UART_Write_Text(buf);
   __delay_ms(3);
  UART_Write_Text(",");
   __delay_ms(3);
sprintf(buf,"%d",(B_data_L+(B_data_H << 8)));
  UART_Write_Text(buf);
   __delay_ms(3);
   UART_Write_Text(")");


	}

// el flag de les interrupcions torna a 0
    INTF = 0;
    
    //s'habiliten les interrupcions altra vegada
    INTE = 1;




  
}