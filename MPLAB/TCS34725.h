/* 
 * File:   APDS9960.h
 * Author: Rosa Rodriguez & Carlos Álvaro
 * Description: 
       
 * to be used for PIC16F690 as a color sensor.
 * This library interfaces the tcs to pic16f690 over I2C. The library
 * relies on the (I2C) library.
 */

#ifndef TCS34725_H
#define	TCS34725_H

#include <stdbool.h>
#include <stdint.h>

#define TCS34725_ADDRESS_W          0x52
#define TCS34725_ADDRESS_R          0x53       



#define TCS34725_COMMAND_BIT      0x80

#define TCS34725_ENABLE           (0x00)
#define TCS34725_ENABLE_AIEN      (0x10)    /* RGBC Interrupt Enable */
#define TCS34725_ENABLE_WEN       (0x08)    /* Wait enable - Writing 1 activates the wait timer */
#define TCS34725_ENABLE_AEN       0x02    /* RGBC Enable - Writing 1 actives the ADC, 0 disables it */
#define TCS34725_ENABLE_PON       0x01    /* Power on - Writing 1 activates the internal oscillator, 0 disables it */
#define TCS34725_ATIME            0x01    /* Integration time */
#define TCS34725_WTIME            0x03    /* Wait time (if TCS34725_ENABLE_WEN is asserted) */
#define TCS34725_WTIME_2_4MS      (0xFF)    /* WLONG0 = 2.4ms   WLONG1 = 0.029s */
#define TCS34725_WTIME_204MS      (0xAB)    /* WLONG0 = 204ms   WLONG1 = 2.45s  */
#define TCS34725_WTIME_614MS      (0x00)    /* WLONG0 = 614ms   WLONG1 = 7.4s   */

#define TCS34725_CONTROL          0x0F    /* Set the gain level for the sensor */
#define TCS34725_ID               (0x12)    /* 0x44 = TCS34721/TCS34725, 0x4D = TCS34723/TCS34727 */
#define TCS34725_STATUS           (0x13)
#define TCS34725_STATUS_AINT      (0x10)    /* RGBC Clean channel interrupt */
#define TCS34725_STATUS_AVALID    (0x01)    /* Indicates that the RGBC channels have completed an integration cycle */


#define TCS34725_CDATAL           0x14    /* Clear channel data */
#define TCS34725_CDATAH           0x15
#define TCS34725_RDATAL           0x16    /* Red channel data */
#define TCS34725_RDATAH           0x17
#define TCS34725_GDATAL           0x18    /* Green channel data */
#define TCS34725_GDATAH           0x19
#define TCS34725_BDATAL           0x1A    /* Blue channel data */
#define TCS34725_BDATAH           0x1B

#define TCS34725_GAIN_4X           0x01
#define TCS34725_WT_154MS           0xC0     
#define TCS34725_WT_240MS           0x9C  

bool initialize(); 
    /* Raw I2C Commands */
   
    int wireWriteDataByte(unsigned char , unsigned char );
    unsigned char wireReadDataByte(unsigned char);
  
#endif



