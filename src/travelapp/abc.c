#include <stdio.h> 
 
void booth(int x, int y) 
{ 
    int m, q; 
    if (x > 0) 
        m = x; 
    else 
        m = ((~x) + 1) & 0xF; 
    if (y > 0) 
        q = y; 
    else 
        q = ((~y) + 1) & 0xF; 
 
    int sign = ((x & 0x8) >> 3) ^ ((y & 0x8) >> 3); 
    int a = 0; 
    int q0 = 0; 
    for (int i = 0; i < 4; i++) 
    { 
        if ((q & 1) == 0 && (q0 & 1) == 1) 
            a = (a + m) & 0xF; 
        else if ((q & 1) == 1 && (q0 & 1) == 0) 
            a = (a - m) & 0xF; 
        int s = a & 0x8; 
        q0 = q & 1; 
        q = (q >> 1) | ((a & 1) << 3); 
        a = (a >> 1) | s; 
    } 
    int fin = (a << 4) | q; 
    // if (fin & 0x80) 
    //     fin -= 256; 
    if (sign) 
        fin = -fin; 
    printf("MUL: %d\n", fin); 
} 
 
void main() 
{ 
    booth(2, 4); 
}