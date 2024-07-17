// 3-2 Compressor is like a full adder

package ImplementPkg

import chisel3._
import chisel3.util._ 

class Compr_3_2 extends Module{
    val Compr_io = IO (new Bundle{
        val a0 = Input(UInt(1.W))
        val a1 = Input(UInt(1.W))
        val a2 = Input(UInt(1.W))
        val s0 = Output(UInt(1.W))
        val c1 = Output(UInt(1.W))
    })
    
    Compr_io.s0 := Compr_io.a0 ^ Compr_io.a1 ^ Compr_io.a2
    Compr_io.c1 := (Compr_io.a0 & Compr_io.a1) | (Compr_io.a1 & Compr_io.a2) | (Compr_io.a0 & Compr_io.a2)
    
}