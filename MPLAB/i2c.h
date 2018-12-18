//com funciona el I2C? com s'envia a un registre?


#define SDA_PIN RB4	// 

#define SCL_PIN RB6	// 
#define SDA_DIR TRISB4
#define SCL_DIR TRISB6

// common i2c routines
char i2c_in_byte(void);
void i2c_out_byte(char o_byte);
void i2c_nack(void);
void i2c_ack(void);
void i2c_start(void);
void i2c_stop(void);
void i2c_high_sda(void);
void i2c_low_sda(void);
void i2c_high_scl(void);
void i2c_low_scl(void);


// Common I2C Routines

char i2c_in_byte(void)
{
   char i_byte, n;
   i2c_high_sda();
   for (n=0; n<8; n++)
   {
      i2c_high_scl();

      if (SDA_PIN)
      {
         i_byte = (i_byte << 1) | 0x01; // msbit first
      }
      else
      {
         i_byte = i_byte << 1;
      }
      i2c_low_scl();
   }
   return(i_byte);
}

void i2c_out_byte(char o_byte)
{
   char n;
   for(n=0; n<8; n++)
   {
      if(o_byte&0x80)
      {
         i2c_high_sda();
      }
      else
      {
         i2c_low_sda();
      }
      i2c_high_scl();
      i2c_low_scl();
      o_byte = o_byte << 1;

   }
   i2c_high_sda();
}

void i2c_nack(void)
{
   i2c_high_sda();	// data at one
   i2c_high_scl();	// clock pulse
   i2c_low_scl();
}

void i2c_ack(void)
{
 //  i2c_low_sda();	// bring data low and clock
  // i2c_high_sda();
  // i2c_low_scl();
   i2c_high_scl();
   i2c_low_scl();
}


void i2c_start(void)
{
 //  i2c_low_scl();
   i2c_high_sda();
   i2c_high_scl();	// bring SDA low while SCL is high
   i2c_low_sda();
   i2c_low_scl();
}

void i2c_stop(void)
{
   i2c_low_scl();
   i2c_low_sda();
   i2c_high_scl();
   i2c_high_sda();  // bring SDA high while SCL is high
   // idle is SDA high and SCL high
}

void i2c_high_sda(void)
{
   // bring SDA to high impedance
   SDA_DIR = 1;
//   __delay_us(4);
}

void i2c_low_sda(void)
{
   SDA_PIN = 0;
   SDA_DIR = 0;  // output a hard logic zero
//   __delay_us(4);
}

void i2c_high_scl(void)
{
   SCL_DIR = 1;   // high impedance
 //  __delay_us(4);
}

void i2c_low_scl(void)
{
   SCL_PIN = 0;
   SCL_DIR = 0;
 //  __delay_us(4);
}



