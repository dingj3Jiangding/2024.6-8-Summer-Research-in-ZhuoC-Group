// 3-2 Compressor is like a full adder

package ImplementPkg

import chisel3._
import chisel3.util._ 

class Compressor_3_2 extends Module{
    val Compr_io = IO (new Bundle{
        val a0 = Input(1.W)
        val a1 = Input(1.W)
        val a2 = Input(1.W)
        val c0 = Output(1.W)
        val c1 = Output(1.W)
    })
    
}