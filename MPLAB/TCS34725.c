/*This library interfaces the APDS-9960 to SK40C over I2C. The library
 * relies on the I2C library. 
 * This library is only for gesture sensor.
 * the main function that are used for gesture test are:
 *              1) initialize()
 *              2) enableGestureSensor()
 *              3) isGestureAvailable()
 *              4) readGesture()
*/

#include <pic16f690.h>

// PINS I2C
#define SDA_PIN RB4	// 
#define SCL_PIN RB6	// 
#define SDA_DIR TRISB4
#define SCL_DIR TRISB6

#include "i2c.h"
#include "TCS34725.h"
#include <stdint.h>
#include <stdbool.h>
#include <xc.h>

//#include "lcd.h"
#define _XTAL_FREQ 4000000

/*Es comprova si el sensor es communica correctament: 
 Si el registre que emmagatzema la ID del sensor és diferent de 0x44 (el valor
 que el fabricant determina, torna False)
 */
bool initialize()
{

    unsigned char id=0;
       
    id= wireReadDataByte(TCS34725_ID);
    if(id!= 0x44) {
        return false;
    }
    
           
return true;
}  



/*Fuhció per escriure un byte determinat al registre determinat*/
int wireWriteDataByte(unsigned char reg, unsigned char val)
{
  
   i2c_start();
   i2c_out_byte(TCS34725_ADDRESS_W);
   i2c_ack();
   i2c_out_byte(reg | TCS34725_COMMAND_BIT);
   i2c_ack();
   i2c_out_byte(val);
   i2c_ack();
   i2c_stop();
	
    
    return 1;
}

/*Llegir un byte del registre que s'ordeni*/
 unsigned char wireReadDataByte(unsigned char reg)
{
    /* Indicate which register we want to read from */
   char val = 0;
   i2c_start();
   i2c_out_byte(TCS34725_ADDRESS_W);
   i2c_ack();
   i2c_out_byte(reg | TCS34725_COMMAND_BIT);
   i2c_ack();
   i2c_start();
   i2c_out_byte(TCS34725_ADDRESS_R);
   i2c_ack();
   val = i2c_in_byte();    
   i2c_nack();                   
   i2c_stop();
   return(val);   
 
}